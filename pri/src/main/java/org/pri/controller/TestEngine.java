package org.pri.controller;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pri.constants.Constants;
import org.pri.entities.Event;
import org.pri.entities.Link;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class TestEngine {
	
	private  		WebDriver 			driver;
	
	private 		String 				pageUrl;
	
	private 		ArrayList<Link> 	links;

	private static 	org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestEngine.class);

	
	public TestEngine(String pageUrl) {
		super();
		
		this.pageUrl = pageUrl;
		
		this.links   = new ArrayList<Link>();	
		
		driverInit();
		
	}
	
	
	public TestEngine() {
		
		this.pageUrl = null;
		
		this.links   = new ArrayList<Link>();
		
		driverInit();
		
	}
	
	public void resetLinks(){
		
		this.links   = new ArrayList<Link>();
		
	}
	
	public  void driverInit(){
		
		logger.info(" WebDriver init ... ");
				
		if(driver == null){
			
			// loading the chromeDriver
			System.setProperty("webdriver.chrome.driver", Constants.getChromeDriverPath());

			// setting driver options
			ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--kiosk");

			options.addArguments("--disable-web-security");

			options.addArguments("--ignore-certificate-errors");
			
			driver = new ChromeDriver(options);
			
			logger.info("WebDriver init new Driver");
			
			
		}else{
			
			logger.info("WebDriver init, use old Driver");
			
		}
						
	}
	
	public void launch() throws Exception{
		
		driver.get(this.pageUrl);
		
		Thread.sleep(3000);

		// ADDING VisualEvent 
		
		logger.info("launch , begin VisualEvent loading");
		
		Thread.sleep(500);
		
		String projet_dir = System.getProperty("user.dir");
		
		String fileContents = Files.toString(new File(projet_dir+"/src/main/resources/seeDomEvents.js"), Charsets.UTF_8);
		
		logger.info("launch,  JS fileContents done ! ");
		     
		((JavascriptExecutor) driver).executeScript(fileContents); 
		
		logger.info("launch,  JS fileContents executeScript ! ");
		
		Thread.sleep(1000);
	
		String response = (String)  ((JavascriptExecutor) driver).executeScript("return seeDomEvents();");
		
		this.extractResults(response);
		
		logger.info("links found : " + this.links.size());
		
		Thread.sleep(500);
		
		((JavascriptExecutor) driver).executeScript("console.log('Hello from Selenium');");
		
		logger.info(" Console Out put written");
		
	}

	
	public void extractResults(String response){
		
		logger.info("BEGIN  extractResults ");
		
		JSONObject obj = new JSONObject(response);

		JSONArray array = obj.getJSONArray("nodes");
		
		logger.debug("extractResults -  nodes ARRAY size : " + array.length());
		
		for(int i = 0 ; i < array.length() ; i++){
			
			Link link = new Link();
			
			link.setPageUrl(this.pageUrl);
						
			JSONObject object = array.getJSONObject(i);
			
			link.setNode(object.getString("node"));
			
			link.setEventNb(object.getInt("listeners_nb"));

			JSONArray events = object.getJSONArray("events");
			
			for (int j = 0 ; j < events.length() ; j++){
				
				Event e = new Event();
				
				JSONObject event = events.getJSONObject(j);
				
				e.setCode(event.getString("func"));
				
				e.setSource(event.getString("source"));
				
				e.setType(event.getString("type"));
				
				link.addEvent(e);
				
			}
			
			this.links.add(link);
			
			logger.info(array.getJSONObject(i));
			
		}
		
		logger.info("END  extractResults ");
		
	}
	
	
	public void close(){
		
		driver.quit();
		
		logger.info("Close driver ");
		
		logger.info("END  TestEngine ");

	}
		
	
	public ArrayList<Link> getLinks() {
		return links;
	}


	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}


	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}	
	
	

}

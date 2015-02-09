package org.pri.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pri.constants.Constants;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class Selenium {
	
	public static WebDriver driver = null;

	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Selenium.class);

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		// Dir name (for other uses)
		logger.info(System.getProperty("user.dir"));

		// loading the chromeDriver
		System.setProperty("webdriver.chrome.driver", Constants.getChromeDriverPath());

		// setting driver options
		ChromeOptions options = new ChromeOptions();

		// full screen option
		options.addArguments("--kiosk");

//		options.addArguments("--start-maximized");
//
//		options.addArguments("--no-sandbox");

		options.addArguments("--disable-web-security");

		options.addArguments("--ignore-certificate-errors");
		
		if(Selenium.driver == null){
			
			Selenium.driver = new ChromeDriver(options);
			
		}


		// loading a URL
		Selenium.driver.get("http://www.google.com/xhtml");

		logger.info("Google Opened ! ");

		// Thread.sleep(500);

		WebElement searchBox = Selenium.driver.findElement(By.name("q"));

		searchBox.sendKeys("ChromeDriver");

		logger.info("Search typed ! ");

		searchBox.submit();

		logger.info("Query submited ! ");

		Thread.sleep(1000);

		// Testing with Ajax-mock-pages : MOCK_1
		
		logger.info("Begin Ajax-mock-pages ");

		Selenium.driver.get("http://ajaxmock-ahmedbacha.rhcloud.com");

		@SuppressWarnings("unused")
		JavascriptExecutor js = (JavascriptExecutor) Selenium.driver;
		
		Thread.sleep(3000);

		WebElement menu = Selenium.driver.findElement(By.className("dropdown-toggle"));
		
		logger.info("dropdown-toggle");

		menu.click();

		Thread.sleep(1000);

		WebElement mock_1 = Selenium.driver.findElement(By.linkText("Mock-1"));

		mock_1.click();
		
		logger.info("dropdown-toggle clicked");

		Thread.sleep(2000);

		WebElement load_link = Selenium.driver.findElement(By.linkText("_Load_"));

		load_link.click();
		
		logger.info("_Load_ clicked");
		
		// ADDING VisualEvent 
		
		logger.info("Begin VisualEvent loading");
		
		
//		((JavascriptExecutor) Selenium.driver).executeAsyncScript(arg0, arg1)
		
		Thread.sleep(500);
		
		String fileContents = Files.toString(new File(System.getProperty("user.dir")+"/src/main/resources/seeDomEvents.js"), Charsets.UTF_8);
		
		logger.info(" JS fileContents done ! ");
		     
		((JavascriptExecutor) Selenium.driver).executeScript(fileContents); 
		
		logger.info(" JS fileContents executeScript ! ");
		
		Thread.sleep(1000);
	
		String response = (String)  ((JavascriptExecutor) Selenium.driver).executeScript("return seeDomEvents();");
		
		logger.info("SHOW JS Data");
		
		logger.info("JSON String response :: " + response);
		
//		logger.info("response size = " + response.size());
		
		//------------ JSON Parse Step ------------------//
		
		JSONObject obj = new JSONObject(response);

		JSONArray array = obj.getJSONArray("nodes");
		
		logger.info("nodes ARRAY size : " + array.length());
		
		for(int i = 0 ; i < array.length() ; i++){
			
			logger.info("---> New Line " + i);
			
			JSONObject object = array.getJSONObject(i);
			
			logger.info("\t" + object.getString("node"));
			
			logger.info("\t" + object.getInt("listeners_nb"));
			
			JSONArray events = object.getJSONArray("events");
			
			for (int j = 0 ; j < events.length() ; j++){
				
				JSONObject event = events.getJSONObject(j);
				
				logger.info("\t\t Event # " + j);
				
				logger.info("\t\t" + event.getString("func"));
				
				logger.info("\t\t" + event.getString("source"));
				
				logger.info("\t\t" + event.getString("type"));
				
			}
			
			logger.info(array.getJSONObject(i));

		    
		}
		
		//------------ END JSON Parse Step ------------------//
		
//		response.forEach(element -> {	
//			
//			logger.info("---> New Line ");
//			
//			logger.info(element.toString());
//			
//		});
		
		
		logger.info("/SHOW JS Data");
		

		((JavascriptExecutor) Selenium.driver).executeScript("console.log('Hello from Selenium');");
		
		logger.info(" Console Out put written");

		// // Thread.sleep(4000);
		//
		driver.quit();

		logger.info("program ended ! ");

	}

}

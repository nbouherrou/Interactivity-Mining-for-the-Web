package org.pri.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ListLinks {
	
	private static Logger logger = LogManager.getLogger(ListLinks.class);
	
	private String url;

	public ListLinks(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private Document initDoc(){
		
		Document doc = null;
		
		try {
			
			doc = Jsoup.connect(this.url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}
	
	public Integer getNbLinks(){
		
		logger.info("Start getNbLinks !");
		
		Elements links = null;
		
		links = initDoc().select("a[href]");
		
		logger.info("NbLinks : " + links.size());
		
		return links.size();
	}
	
	public Integer getNbImports(){
		
		logger.info("Start getNbImports !");

		Elements imports = null;

		imports = initDoc().select("link[href]");
		
		logger.info("NbImports : " + imports.size());
			
		return imports.size();
	}
	
	public Integer getNbMedia(){
		
		logger.info("Start getNbMedia !");

		Elements media = null;

		media = initDoc().select("[src]");
		
		logger.info("NbMedia : " + media.size());

		return media.size();
	}
	
	
 
}
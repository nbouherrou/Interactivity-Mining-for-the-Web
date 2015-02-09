package org.pri.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pri.constants.Constants;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
	
	private static Logger logger = LogManager.getLogger(Controller.class);
	
	
    public static void main(String[] args) throws Exception {
    	
        long startTime = System.currentTimeMillis();
    	
            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(Constants.CRAWL_STORAGE_FOLDER);
            
            config.setMaxPagesToFetch(Constants.NUMBER_PAGES_TO_FECH);

            /*
             * Instantiate the controller for this crawl.
             */
            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            /*
             * For each crawl, you need to add some seed urls. These are the first
             * URLs that are fetched and then the crawler starts following links
             * which are found in these pages
             */
            
            controller.addSeed(Constants.URL_TO_VISIT);

            /*
             * Start the crawl. This is a blocking operation, meaning that your code
             * will reach the line after this only when crawling is finished.
             */
            controller.start(MyCrawler.class, Constants.NUMBER_Of_CRAWLERS);    
            
        long stopTime = System.currentTimeMillis();
        
        long elapsedTime = stopTime - startTime;
        
        logger.info("PERF_AUDIT - execution time : " + (elapsedTime/1000.0) + "s");
            
    }
}
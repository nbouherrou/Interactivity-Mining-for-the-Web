package org.java.pri;

import org.apache.logging.log4j.LogManager;
import org.pri.controller.TestEngine;

public class TestEngineTest {
	
	private static 	org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestEngineTest.class);

	
	public static void main(String[] args) {
		
		logger.info("Start TestEngineTest");
		
        TestEngine engine = new TestEngine("http://ajaxmock-ahmedbacha.rhcloud.com/mock-1.php");
        
        try {
        	
        	engine.launch();
        	
        	logger.info(engine.getLinks());
        	

		} catch (Exception e) {
			
			e.printStackTrace();
			
			engine.close();
			
		}
		
		logger.info("End TestEngineTest");
		
	}
}

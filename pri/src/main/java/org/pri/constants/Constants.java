package org.pri.constants;

public class Constants {

	public static final String 		CRAWL_STORAGE_FOLDER 			= "/Users/PRI";
	
	public static final String 		URL_TO_VISIT 					= "http://www.telecom-st-etienne.fr";
	
	public static final int 		NUMBER_Of_CRAWLERS 				= 1;
	
	public static final String 		CHROME_DRIVER_WINDOWS_PATH 		= "/Users/Chedy/Downloads/chromedriver.exe";
	
	public static final String 		CHROME_DRIVER_MAC_PATH 			= "/bin/chromedriver";
	
	public static final String 		CHROME_DRIVER_VERBOSE_MAC_PATH 	= "/bin/chromedriververbose";
	
	public static final String 		A_POUR_PAGE 					= "A_POUR_PAGE";
	
	public static final String 		A_POUR_LIEN 					= "A_POUR_LIEN";
	
	public static final String 		A_POUR_EVENT 					= "A_POUR_EVENT";
	
	public static final int			NUMBER_PAGES_TO_FECH			= 10;
	
	
	public static String getChromeDriverPath(){
		
		if(System.getProperty("os.name").equals("Mac OS X")){
			
			if(System.getProperty("user.name").equals("rbo")){
				
				return CHROME_DRIVER_VERBOSE_MAC_PATH;
				
			}else{
				
				return CHROME_DRIVER_MAC_PATH;
				
			}	
			
		}else{
			
			return CHROME_DRIVER_WINDOWS_PATH;
		}
		
	}
	
	
}

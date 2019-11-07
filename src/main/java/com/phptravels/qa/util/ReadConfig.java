package com.phptravels.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig() {
		//Read and load config file
				try {
					prop = new Properties();
					FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "/configuration/config.properties");
					prop.load(propFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public String getChromeDriverPath() {
		return prop.getProperty("chromeDriverPath");
	}
	
	public String getBaseUrl() {
		return prop.getProperty("baseUrl");
	}
	
	public String getBrowserName() {
		return prop.getProperty("browser");
	}
	
	public String getGeckoDriverPath() {
		return prop.getProperty("geckoDriverPath");
	}
	
	public String getTestDataFilePath() {
		return prop.getProperty("testDataFilePath");
	}
	
	public String getTestDataFileName() {
		return prop.getProperty("testDataFileName");
	}
	
	public String getLoginSheetName() {
		return prop.getProperty("loginDataSheetName");
	}
	
	public String getScreenShotFilePath() {
		return prop.getProperty("screenShotFilePath");
	}

}

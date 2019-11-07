package com.phptravels.qa.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.phptravels.qa.util.ReadConfig;
import com.phptravels.qa.util.TestUtil;

public class BaseTest {
	
	public static WebDriver driver;
    public static WebDriverWait wait;
    public static ReadConfig configFile;
    public static ExtentHtmlReporter reporter;
	public static ExtentReports extent ;	
    public static  ExtentTest parentTest;
    public static ExtentTest childTest;
    
   
	public void beforeMethodSetUp() {
		
    		configFile = new ReadConfig();

    		String browser = configFile.getBrowserName();
		
		switch(browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", configFile.getChromeDriverPath());
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", configFile.getGeckoDriverPath());
				driver = new FirefoxDriver();			
		}		
		//Create a wait. All test classes use this.
        wait = new WebDriverWait(driver,15);
		
		//driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITLY_WAIT_TIMEOUT,TimeUnit.SECONDS);
				
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(configFile.getBaseUrl());

		
    }
    
	@AfterMethod
	public void afterMethod() {
    
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getLoginDataUsingDataProviderMethod(){
		Object[][] loginData = {
							{"user@phptravels.com", "demouser"},
							{"invaliduser@phptravels.com", "demouser"}
						};
		
		
		return loginData;
	}
	
	@DataProvider
	public Object[][] getLoginDataFromExcelFile(){
		Object[][] data = TestUtil.getLoginDataFromExcelFile();
		
		return data;
	}
    
	
}

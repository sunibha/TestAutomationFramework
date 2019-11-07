package com.phptravels.qa.testCases;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.phptravels.qa.pages.HomePage;
import com.phptravels.qa.pages.HotelsResults;
import com.phptravels.qa.pages.LoginPage;
import com.phptravels.qa.pages.RegisterPage;
import com.phptravels.qa.util.TestUtil;

@Listeners({com.phptravels.qa.util.ExtentReportListener.class, com.phptravels.qa.util.ReporterTest.class})
public class HomePageTest extends BaseTest{
	
	public HomePage homePage;
	public LoginPage loginPage;
	public RegisterPage registerPage;
	public TestUtil testUtil;
	public HotelsResults hotelsResults;
		
	
	@BeforeMethod (description = "Before Method Setup")
	public void setUp(Method method) {
		beforeMethodSetUp();
		homePage = new HomePage(driver, wait);
		loginPage = new LoginPage(driver, wait);
		registerPage = new RegisterPage(driver, wait);
		testUtil = new TestUtil();
		hotelsResults = new HotelsResults(driver, wait);
		
	}
	
	
	//verify broken links on the home page
	@Test
	public void verifyHomePageLinks() {
		List<String> homePageLinks = homePage.getAllHyperLinks();
		
		HashMap<String, String> result = testUtil.checkBrokenLinks(homePageLinks);
		int numberOfBrokenLinks = result.size();
		
		Assert.assertEquals(numberOfBrokenLinks, 0, "Some of the links in Home Page are not working");
		if (result.size() > 0) {
			for (String key: result.keySet()) {
				System.out.println(key + " ---> " + result.get(key));
			}		
		}
	}
	
	//verify home page title - failing test to verify screenshot functionality
	@Test(priority=1, invocationCount=2)
	public void verifyHomePageTitle() {
		System.out.println(driver);
		String homePageTitle = homePage.getHomePageTitle();
		String expectedHomePageTitle = "PHPTRAVELS | Travel Technology Partner";
		
		Assert.assertEquals(homePageTitle, expectedHomePageTitle, "Home Page Title is not valid");
	}
	
	//verify home page logo is displayed or not
	@Test(priority = 2)
	public void verifyHomePageLogoDisplayed() {
		//Assert.assertTrue(homePage.isHomePageLogoDisplayed(), "Home Page Logo - not displayed");
		Assert.assertTrue(false);
	}
	
	//verify login link is working and routing to login page
	@Test(priority = 3)
	public void verifyLoginLink() {
		homePage.clickMyAccountLink();
		//loginPage = homePage.clickLoginLink();
		Assert.assertTrue((homePage.clickLoginLink() instanceof LoginPage));
	}
	
	//verify Sign Up link is working and routing to Sign Up page
	@Test(priority = 4)
	public void verifySignUpLink() {
		homePage.clickMyAccountLink();
		//registerPage = homePage.clickSignUpLink();
		Assert.assertTrue(homePage.clickSignUpLink() instanceof RegisterPage);
	}
	
	//verify search with destination, checkin date, checkout date and adults is working fine
	@Test(priority=5)
	public void verifySearchHotelTest() {
		hotelsResults = homePage.searchHotel( "india/new-delhi", "22/3/2020", "22/4/2020", 4);
	}
	
	
}

package com.phptravels.qa.testCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.phptravels.qa.pages.AccountsPage;
import com.phptravels.qa.pages.HomePage;
import com.phptravels.qa.pages.LoginPage;

@Listeners({com.phptravels.qa.util.ExtentReportListener.class, com.phptravels.qa.util.ReporterTest.class})


public class LoginPageTest extends BaseTest{
	
	public LoginPage loginPage;
	public HomePage homePage;
	public AccountsPage accountPage;

	
	@BeforeMethod
	public void setUp(Method method) {
		beforeMethodSetUp();
		homePage = new HomePage(driver, wait);
		loginPage = new LoginPage(driver, wait);
		Reporter.log("Clicking on my account link");
		homePage.clickMyAccountLink();
		
		Reporter.log("clicking on login link under my account");
		loginPage = homePage.clickLoginLink();
		
	}
	
	
	@Test(priority=1, invocationCount=2, groups="regression")
	public void verifyLoginPageTitle() {
		String loginPageTitle = loginPage.getLoginPageTitle();
		String expectedLoginPageTitle = "Login";
		Assert.assertEquals(loginPageTitle, expectedLoginPageTitle);
		//TestUtil.captureScreenShot();
	}
		
	@Test(priority=2, dataProvider="getLoginDataUsingDataProviderMethod")
	public void verifyLoginWithoutRemeberMe(String username, String password) {
		//accountPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(loginPage.login(username, password) instanceof AccountsPage);
	}
	
	@Test(priority=3, dataProvider="getLoginDataFromExcelFile")
	public void verifyLoginWithRemeberMe(String username, String password) {
		Reporter.log("click on Remeber Me checbox before signing-in");
		loginPage.clickRemeberMeCheckbox();
		Assert.assertTrue(loginPage.login(username, password) instanceof AccountsPage);
		
	}
	
	
	
}

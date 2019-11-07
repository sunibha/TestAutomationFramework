package com.phptravels.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
//calling parent class constructor	
	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(name="username")
	WebElement emailText;
	
	@FindBy(name="password")
	WebElement passwordText;
	
	//@FindBy(id="remember-me")
	@FindBy(xpath="//*[@id='toggleStyle04-collapseOne']/div/div/div[3]/div/div[3]/label")
	WebElement rememberMeCheckbox;
	
	@FindBy(linkText="Login")
	WebElement loginButton;
	
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public void setEmailText(String username) {
		emailText.sendKeys("userName");
	}
	
	public void setPasswordText(String password) {
		passwordText.sendKeys(password);
	}
	
	public void clickRemeberMeCheckbox() {
		rememberMeCheckbox.click();
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public AccountsPage login(String username, String pwd) {
		setEmailText(username);
		setPasswordText(pwd);
		clickLoginButton();
		return new AccountsPage(driver, wait);
	}
	
	
}

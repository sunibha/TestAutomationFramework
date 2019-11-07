package com.phptravels.qa.pages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	//initialize page factory on creating an instance of Homepage class
	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);			
		PageFactory.initElements(driver, this);
	}
	
	//xpath for home page logo
	@FindBy(xpath="//img[@alt='PHPTRAVELS | Travel Technology Partner']")
	WebElement homePageLogo;
	
	//xpath for My Account link 
	@FindBy(xpath="//div[@class='dropdown dropdown-login dropdown-tab']/descendant::a[1]")
	WebElement myAccountLink;
	
	//xpath for Login link under My Account dropdown
	@FindBy(xpath="//a[text()='Login']")
	WebElement loginLink;
	
	//xpath for Sign Up link under My Account dropdown
	@FindBy(xpath="//a[text()='Sign Up']")
	WebElement signUpLink;
	
	//all the hyperlinks(a tags) in Home Page
	@FindBy(tagName="a")
	List<WebElement> allHomePageLinks;
	
	//Hotels tab
	@FindBy(xpath="//a[contains(text(),'Hotels')]")
	WebElement hotelsLink;
	
	//destination input text
	@FindBy(name="dest")
	//@FindBy(xpath="//*[@id=\"select2-drop\"]/div/input")
	WebElement destination;
	
	//check-in date 
	@FindBy(id="checkin")
	WebElement checkInDate;
	
	//check-out date
	@FindBy(id="checkout")
	WebElement checkOutDate;
	
	//adults field
	@FindBy(name="adults")
	WebElement adults;
	
	//Search button
	@FindBy(xpath="//*[@id=\"hotels\"]/div/div/form/div/div/div[4]/button")
	WebElement searchButton;
	
	//returns Home page title
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	//returns if home page logo is displayed
	public Boolean isHomePageLogoDisplayed() {
		return homePageLogo.isDisplayed();	
	}
	
	//click on my account link
	public void clickMyAccountLink() {
		myAccountLink.click();
	}
	
	//click on Hotel tab 
	public void clickHotelsLink() {
		hotelsLink.click();
	}
	//set destination field
	public void setDestination(String inputDestination) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//destination.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + inputDestination + "')", destination);
		System.out.println(destination.getAttribute("value"));
	}
	
	public void setCheckInDate(String inputCheckInDate) {
		
		checkInDate.click();
		
		//input date format DD/MM/YYYY --> 01/01/2020
		String inputDate[] = inputCheckInDate.split("/");
		int inputDay = Integer.parseInt(inputDate[0]);
		int inputMonth = Integer.parseInt(inputDate[1]);
		int inputYear = Integer.parseInt(inputDate[2]);
		
		WebElement midLink = driver.findElement(By.xpath("//*[@id='datepickers-container']/div[1]/nav/div[2]"));
				
		Calendar now = Calendar.getInstance();
		int currYear = now.get(Calendar.YEAR);
		
		int yearDiff =  inputYear - currYear;
		
		if (yearDiff!=0) {
			midLink.click();
			if(yearDiff>0) {
				for(int i=0; i<yearDiff; i++) {
					WebElement rightLink = driver.findElement(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/nav/div[@data-action='next']"));
					rightLink.click();
				}
			} else {
				for (int i=0; i<(yearDiff*(-1)); i++){
					WebElement leftLink = driver.findElement(By.xpath("//*[@id='datepickers-container']/div[1]/nav/div[1]"));
					leftLink.click();
				}
			}		
		}
		
		List<WebElement> allMonthsInDatePicker = driver.findElements(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div[2]/div/div"));
		allMonthsInDatePicker.get(inputMonth-1).click();
		
		List<WebElement> allDaysInDatePicker = driver.findElements(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div[1]/div[2]/div[@class='datepicker--cell datepicker--cell-day' or @class='datepicker--cell datepicker--cell-day -weekend-']"));
		allDaysInDatePicker.get(inputDay-1).click();
		
	}
	
	public void setCheckOutDate(String inputCheckOutDate) {
		
		checkOutDate.click();
		
		//input date format DD/MM/YYYY --> 01/01/2020
		String inputDate[] = inputCheckOutDate.split("/");
		int inputDay = Integer.parseInt(inputDate[0]);
		int inputMonth = Integer.parseInt(inputDate[1]);
		int inputYear = Integer.parseInt(inputDate[2]);
		
		WebElement imidLink = driver.findElement(By.xpath("//*[@id='datepickers-container']/div[2]/nav/div[2]"));
				
		Calendar now = Calendar.getInstance();
		int currYear = now.get(Calendar.YEAR);
		
		int yearDiff =  inputYear - currYear;
		
		if (yearDiff!=0) {
			imidLink.click();
			if(yearDiff>0) {
				for(int i=0; i<yearDiff; i++) {
					WebElement rightLink = driver.findElement(By.xpath("//*[@id='datepickers-container']/div[2]/nav/div[@data-action='next']"));
					rightLink.click();
				}
			} else {
				for (int i=0; i<(yearDiff*(-1)); i++){
					WebElement leftLink = driver.findElement(By.xpath("//*[@id='datepickers-container']/div[2]/nav/div[@data-action='prev']"));
					leftLink.click();
				}
			}		
		}
		
		List<WebElement> allMonthsInCheckOutDatePicker = driver.findElements(By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div[2]/div[@class='datepicker--cells datepicker--cells-months']/div"));
		allMonthsInCheckOutDatePicker.get(inputMonth-1).click();
		
		List<WebElement> allDaysInCheckOutDatePicker = driver.findElements(By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div[1]/div[@class='datepicker--cells datepicker--cells-days']/div[@class='datepicker--cell datepicker--cell-day' or @class='datepicker--cell datepicker--cell-day -weekend-']"));
		allDaysInCheckOutDatePicker.get(inputDay-1).click();
		
	}

	//set number of adults
	public void setAdults(int inputAdults) {
		
		int currAdults = Integer.parseInt(adults.getAttribute("value"));
		
		int adultsDiff = inputAdults - currAdults;
		
		WebElement upArrowLink = driver.findElement(By.xpath("//*[@id='hotels']/div/div/form/div/div/div[3]/div/div/div/div/div/div/div[1]/div/div[2]/div/span[3]/button[1]"));
		
		WebElement downArrowLink = driver.findElement(By.xpath("//*[@id='hotels']/div/div/form/div/div/div[3]/div/div/div/div/div/div/div[1]/div/div[2]/div/span[3]/button[2]"));
		
		Actions builder = new Actions(driver);
		
		if (adultsDiff != 0) {
			if (adultsDiff>0) {
				for(int i=0; i<adultsDiff; i++ ) {	
					//need to move the mouse out of up arrow link after click, otherwise counter keeps going on if mouse stays on up arrow link
					builder.moveToElement(upArrowLink).click().moveToElement(adults).build().perform();	
				}			
			} else {
				for(int i=0; i<(adultsDiff * (-1)); i++ ) {
					//need to move the mouse out of down arrow link after click, otherwise counter keeps going down if mouse stays on down arrow link
					builder.moveToElement(downArrowLink).click().moveToElement(adults).build().perform();
					System.out.println(adults.getAttribute("value"));
				}				
			}
		}
	}
	
	//click on search button
	public void clickSearchButton() {
		searchButton.click();
	}
	
	//search hotel using destination, checkin date, checkout date, adults
	public HotelsResults searchHotel(String destInput, String checkInDateInput, String checkOutDateInput, int adultsInput) {
		//clickHotelsLink();
		setDestination(destInput);
		setCheckInDate(checkInDateInput);
		setCheckOutDate(checkOutDateInput);
		setAdults(adultsInput);
		clickSearchButton();
		
		return new HotelsResults(driver, wait);
	}
	
	//click on Login link
	public LoginPage clickLoginLink() {
		loginLink.click();
		return new LoginPage(driver, wait);
	}
	
	//click on SignUp link
	public RegisterPage clickSignUpLink() {
		signUpLink.click();
		return new RegisterPage(driver, wait);
	}
	
	//return all the hyperlinks in HomePage
	public List<String> getAllHyperLinks(){
		
		List<String> allLinks = new ArrayList<String>();
		
		for(WebElement aTag: allHomePageLinks) {	
			String hrefLink = aTag.getAttribute("href");
			allLinks.add(hrefLink);
		}
		System.out.println("Number of HyperLinks in Home Page:  " + allLinks.size());
		return allLinks;
	}
	
	//

}

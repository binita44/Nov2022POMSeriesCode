package com.qa.ecomapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.utils.ElementUtilities;

public class LoginPage {
	private WebDriver driver;
	private ElementUtilities eleUtil;
	
	// we have to use private by locator in class level so that it can be used in any method
	private By Customertype = By.tagName("h2");
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotpasswordLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By registerLink = By.linkText("Register");
	
	//we will create public constructor coz, 
	//when ever you create an object of login class this constructor should be called and this will pass you the driver
	
	public LoginPage(WebDriver driver ) {
		this.driver = driver;
		eleUtil = new ElementUtilities(driver);
	}
	//creating page methods or action: i.e behaviour of the page
	//it functionality: check title check url check logo, check login field etc
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(5, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println(title);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(10,AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println(url);
		return url;
	}
	
	public boolean forgetPwdLinkExistance () {
		return eleUtil.waitForElementVisible(forgotpasswordLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public boolean customerTypeHeadingExist () {
		return eleUtil.waitForElementVisible(Customertype, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("app credential : "+un+" , "+pwd);
		eleUtil.waitForElementVisible(emailid, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
		
		//should return next landing page object
	//this approach where we get option like create accounts page : i.e create landing page suggestion : i.e test driven development approach
		//we will make class as needed by the test
		//tdd approach: to fulfill the requirement what ever classes or methods i need to make i will make it and model is page chaining model
		
		
	}
	//we use page action and locators in same page using encapsulation concept so that it will not be confusion
	//and its private so no one can assess it

	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
		
	}



}


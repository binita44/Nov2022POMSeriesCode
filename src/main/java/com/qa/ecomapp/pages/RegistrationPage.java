package com.qa.ecomapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.utils.ElementUtilities;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtilities eleutil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//input[@name='newsletter'])[position()=1]");
	private By subscribeNo = By.xpath("(//input[@name='newsletter'])[position()=2]");
	private By agreePolicy = By.xpath("//input[@name='agree']");
	private By contBtn = By.xpath("//input[@type='submit']");
	
	private By successMsg = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	//constructor
	public RegistrationPage (WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtilities(driver);
	}
	
	public boolean registerUser(String firstname, String lastname, String email, String telephone, String password, String subscribe) {
		
		eleutil.waitForElementVisible(this.firstname, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstname);
		eleutil.doSendKeys(this.lastname, lastname);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmPwd, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(subscribeYes);
		}
		else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreePolicy);
		eleutil.doClick(contBtn);
		
		String success = eleutil.waitForElementVisible(successMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("User Registraion Success Message : "+ success);
		
		if(success.contains(AppConstants.USER_REG_SUCCESS_MESSAGE)) {
			
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			
			return true;
		}
		return false;
	}
	//inorder to create second user you have to log out 1st 
	//so maintain logoot link
	
	
	

}

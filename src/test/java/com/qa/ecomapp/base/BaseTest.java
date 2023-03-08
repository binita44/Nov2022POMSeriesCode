package com.qa.ecomapp.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.ecomapp.mydriver.DriverFactory;
import com.qa.ecomapp.pages.AccountsPage;
import com.qa.ecomapp.pages.LoginPage;
import com.qa.ecomapp.pages.ProductInfoPage;
import com.qa.ecomapp.pages.RegistrationPage;
import com.qa.ecomapp.pages.SearchPage;

public class BaseTest {
	//basetest will not exdent driverfactory class coz there is noooo relationship between them
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginpage;//using protected so only child class can excess
	protected AccountsPage accpage;
	protected SearchPage searchpage;
	protected ProductInfoPage productinfo;
	protected SoftAssert softAssert;
	protected RegistrationPage registerpage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);// using prop reference will return all the properties file and we have stored all desired browser inside config.properties file
		loginpage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
		//while creating login page object constructor will be called and you have tp pass the driver
		
		// we removed this to avoid hard coded value i.e chrome//driver = df.initDriver("chrome");//this will return us webdriver
		//we are holding the initdriver method inside webdriver driver, so it will be used in other method and same driver will be used everywhere
	}
	
	
	
	@AfterTest
	public void teardown() {
		driver.quit();
		
	}
	
	
	

}

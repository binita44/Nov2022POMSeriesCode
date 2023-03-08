package com.qa.ecomapp.uitest;

//import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.base.BaseTest;
import com.qa.ecomapp.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void registrationPageSetup() {
		registerpage = loginpage.navigateToRegistrationPage();
	}
	public String getRandomEmail() {
		//Random random = new Random();//its class available in java
		//String email = "automation"+random.nextInt(1000)+"@gmail.com";
		
		String email = "automation"+System.currentTimeMillis()+"@gmail.com";
		//using automation at 1st so, we can understand its used by automation engineer
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData () {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider="getRegTestData")
	public void userRegisterTest(String firstname, String lastname, String telephone, String password, String subscribe) {
		
		Assert.assertTrue(registerpage.registerUser(firstname, lastname, getRandomEmail(), telephone, password, subscribe));
		
	}
	

}

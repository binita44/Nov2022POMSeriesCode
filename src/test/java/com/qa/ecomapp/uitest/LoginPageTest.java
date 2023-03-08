package com.qa.ecomapp.uitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.base.BaseTest;

public class LoginPageTest extends BaseTest {
	//now to test we have to use methods created in login page .java class so import the,
	//we will create login page object in base test so it can be used by other class too
	//and the basetest will be exdended in login page test
	//coz it has the before and after method
	
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		//we can inherit the LoginPage page reference so we will use loginpage and it should be protected
	String actualtitle = loginpage.getLoginPageTitle();
	Assert.assertEquals(actualtitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actualurl = loginpage.getLoginPageUrl();
		Assert.assertTrue(actualurl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	@Test (priority = 3)
	public void loginPageForgotPwdLinkTest() {
		boolean loginlink = loginpage.forgetPwdLinkExistance();
		Assert.assertTrue(loginlink);
	}
	@Test (priority = 4)
	public void loginPageCustomerTypeHeadingTest() {
		boolean customertype = loginpage.customerTypeHeadingExist();
		Assert.assertTrue(customertype);
	}
	@Test(priority = 5)
	public void loginTest() {
		//##new time you see null, check if it is storing anything or not
		accpage = loginpage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accpage.accountsPageLogoutLinkExist());
		//if login is successful it will go to accounts page, if there is logout link then its accounts page
		
	}

}

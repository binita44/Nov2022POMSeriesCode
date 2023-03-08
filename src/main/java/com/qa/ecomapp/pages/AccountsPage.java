package com.qa.ecomapp.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.utils.ElementUtilities;

public class AccountsPage {
	 //this is the landing page after we log in: so login page should return us the account page
	//it is the page responsibility to return landing page method object
	private WebDriver driver;
	private ElementUtilities eleutil;
	
	//By Locators
	private By logoutLink = By.xpath("//div[@class='list-group']/a[text()='Logout']");
	private By accountsHeader = By.cssSelector("div#content h2");
	private By searchtextbar = By.name("search");
	private By logo = By.xpath("//img[@class='img-responsive']");
	private By searchIcon =By.cssSelector("#search button");
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtilities(driver);
	}
	public String getAccountsPageTitle() {
		String actualtitle = eleutil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println(actualtitle);
		return actualtitle;
	}
	public String getAccountsPageUrl() {
		String actualtitle = eleutil.waitForURLContainsAndFetch(AppConstants.DEFAULT_LONG_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println(actualtitle);
		return actualtitle;
	}
	
	public boolean accountsPageLogoutLinkExist() {
		return eleutil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_LONG_TIME_OUT).isDisplayed();
	}
	
	public boolean accountsPageSearchBarExist() { 
		
		return eleutil.waitForElementVisible(searchtextbar, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	public boolean accountsPageLogoExist() {
		return eleutil.doElementIsDisplayed(logo);
	}
	public List<String> getAccountsPageHeadersList() {
		List<WebElement> accountheaderlist = eleutil.waitForElementsVisible(accountsHeader, AppConstants.DEFAULT_SHORT_TIME_OUT);
		List<String> accheadervaluelist = new ArrayList<String>();
		
		for (WebElement e : accountheaderlist) {
			String text = e.getText();
			accheadervaluelist.add(text);
			
		}
		return accheadervaluelist;
	}
	public SearchPage performsearch(String searchKey) {
		if (accountsPageSearchBarExist()) {
			eleutil.doSendKeys(searchtextbar, searchKey);
			eleutil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search Bar not present on the page");
			return null;
		}
		
	}
	

}

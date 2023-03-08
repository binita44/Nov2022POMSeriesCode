package com.qa.ecomapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.utils.ElementUtilities;

public class SearchPage {
	private WebDriver driver;
	private ElementUtilities eleutil;
	
	private By searchproductresultcount = By.cssSelector("div#content div.product-layout");
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtilities(driver);
	}
	
	public int searchProductsCount() {
		int productcount = eleutil.waitForElementsVisible(searchproductresultcount, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product count is : "+productcount);
		return productcount;
	}
//	public void searchPageTitle() {
//		eleutil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, );
//		
//		
//	}
	//we will not maintain the product using By locator coz it will be changing 
	//and many more product will be added 
	
	public ProductInfoPage selectProduct (String productName) {
		//creating dynamic locator
		By productlocator = By.linkText(productName);
		eleutil.waitForElementVisible(productlocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}

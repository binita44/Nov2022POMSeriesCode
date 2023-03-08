package com.qa.ecomapp.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.utils.ElementUtilities;



public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtilities eleutil;
	private Map<String, String> productInfoMap;
	
	
	//Locators
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addtocartbtn = By.id("button-cart");
	private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtilities(driver);
	}
	public String getProductHeaderValue() {
		String productHeaderVal = eleutil.doElementGetText(productHeader);
		System.out.println(productHeaderVal);
		return productHeaderVal;
	}
	
	public int getProductImageCount() {
		int imgcount = eleutil.waitForElementsVisible(productImages, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
		System.out.println("Images Count : "+imgcount);
		return imgcount;
	}
	public void enterQuantity (int qty) {
		System.out.println("Product Quantiti : "+qty);
		eleutil.doSendKeys(quantity, String.valueOf(qty));//we cannot store integer in do send keys method so convert interger to string: using String.valueOf method 

	}
	public String addProductToCart() {//avoid using actions i.e clickonaddcart etc
		eleutil.doClick(addtocartbtn);
		//we will get success message  and we have to validate the success message is coming or not
		String successmessage = eleutil.waitForElementVisible(cartSuccessMessage, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		StringBuilder sb = new StringBuilder(successmessage);
		String msg = sb.substring(0, successmessage.length()-1).replace("\n", "").toString();
		System.out.println("Cart Sucess Message : "+msg);
		return msg;
		
	}
	
	public Map<String, String> getProductInfo(){
		//hashmap object...// productInfoMap reference is created at class level  
		productInfoMap = new LinkedHashMap<String, String>();//using linked hasmap coz its 
		//heading
		productInfoMap.put("productName", getProductHeaderValue());//we made custome key coz header value is not in key and value format
		//meta data part
		getProductMetaData();
		//pricepart
		getProductPriceData();
		//will return the hasmap
		return productInfoMap;
	 
	}
	
	
	private void getProductMetaData() {
		//Map<String,String> productInfoMap = new HashMap<String,String> ();
		
		//productInfoMap.put("productname", getProductHeaderValue());
		
		List<WebElement> metalist = eleutil.getElements(productMetaData);
		for(WebElement e : metalist) {
			String meta = e.getText();
			String metaInfo[]= meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}	
		}
	private void getProductPriceData() {
		List<WebElement> pricelist = eleutil.getElements(productPricingData);
		String price = pricelist.get(0).getText();//here will get $2000.00
		String extax = pricelist.get(1).getText();
		String estaxVal = extax.split(":")[1].trim(); //directly using [1], we will just give value at 1th index so, we do not need to create array variable
		//here we have no foe loop
		productInfoMap.put("productPrice", price);//productprice is our custome key, and price is store in price variable
		productInfoMap.put("extax", estaxVal);
	}

}

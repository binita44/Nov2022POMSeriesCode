package com.qa.ecomapp.uitest;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ecomapp.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@DataProvider
	public Object[][] getProductImageTestData(){//here we are 1st searching for product and then clicking in respective product
		return new Object[][] {
			{"MacBook","MacBook Pro", 4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1}
		};
	}
	
	@Test(dataProvider="getProductImageTestData")
	public void productImageCountTest(String searchkey, String productname, int imgcount) {
		searchpage = accpage.performsearch(searchkey);
		productinfo = searchpage.selectProduct(productname);
		int actImagesCount = productinfo.getProductImageCount();
		Assert.assertEquals(actImagesCount, imgcount);
	}
	//metadata and pricing test
	@Test
	public void productInfoTest() {
		searchpage = accpage.performsearch("MacBook");
		productinfo = searchpage.selectProduct("MacBook Pro");
		productinfo.getProductInfo();
		Map<String, String> actProdInfoMap = productinfo.getProductInfo();
		System.out.println(actProdInfoMap);
		softAssert.assertEquals(actProdInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProdInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProdInfoMap.get("productPrice"), "$2,000.00"); 
		softAssert.assertEquals(actProdInfoMap.get("productName"), "MacBook Pro"); 
		
		softAssert.assertAll();
		//Soft assert method is not satic in nature so we have to create new object in BaseTest Class
	}
	//assert vs verify: here assert is about hard assert i.e AssertClass and verify is about soft assert: and it is part of testng not part of WebDriver
	
	@Test
	public void addtoCartMessageTest() {
		searchpage = accpage.performsearch("MacBook");
		productinfo = searchpage.selectProduct("MacBook Pro");
		productinfo.enterQuantity(3);
		String actCartMessage = productinfo.addProductToCart();
		softAssert.assertTrue(actCartMessage.contains("Success"));
		softAssert.assertTrue(actCartMessage.contains("MacBook Pro"));
		
		softAssert.assertEquals(actCartMessage, "Success: You have added MacBook Pro to your shopping cart!");
		softAssert.assertAll();
		
		// resolving the x i.e extra in actual message captured
		//i.e button in same div
		//excluding 1 child inside the div tag
		//sub string method: substring (substring(0, successmessage.length()-1)
		
	}
	
}

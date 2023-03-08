package com.qa.ecomapp.uitest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ecomapp.appconstants.AppConstants;
import com.qa.ecomapp.base.BaseTest;

public class AccountsPageTest extends BaseTest {
	//without login we cannot come to this page so
	//we will create a precondition @beforetest method 
	@BeforeClass
	public void accPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	@Test
	public void accPageTitleTest() {
		String actualtitle = accpage.getAccountsPageTitle();
		Assert.assertEquals(actualtitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	@Test
	public void accPageUrlTest() {
		String actualurl = accpage.getAccountsPageUrl();
		Assert.assertTrue(actualurl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	@Test
	public void accPageSearchBarExistTest() {
		Assert.assertTrue(accpage.accountsPageSearchBarExist());
	}
	@Test
	public void accPageLogoExistTest() {
		Assert.assertTrue(accpage.accountsPageLogoExist());
	}
	@Test
	public void accPageLogOutLinkExistTest() {
		Assert.assertTrue(accpage.accountsPageLogoutLinkExist());
	}
	@Test
	public void accPgaeHeadersCountTest() {
		List<String> actualheaderlist = accpage.getAccountsPageHeadersList();
		//we are asserting with the size, extected siz of list and actual size of list
		Assert.assertEquals(actualheaderlist.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
		
	}
	@Test
	public void accPgaeHeadersValueListTest() {
		List<String> actualheaderlist = accpage.getAccountsPageHeadersList();
		System.out.println("Actual page header list : " + actualheaderlist);
		System.out.println("Expected page header list : " + AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualheaderlist, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
			
		};
		
	}
	
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchpage = accpage.performsearch(searchKey);
		Assert.assertTrue(searchpage.searchProductsCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData(){//here we are 1st searching for product and then clicking in respective product
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"}
			//will we keep these in excel file ==> no, coz we are not gonna test each and every product,
			//we will do delta technique or say selective testing some set of positive data will be maintained and test is done based on that set of data
		
		};
	}
	@Test(dataProvider = "getProductTestData")
	public void searchProductSelectTest(String searchkey, String productname) {
		searchpage = accpage.performsearch(searchkey);//searching macbook in searchbar
		if(searchpage.searchProductsCount()>0) {
		
		productinfo = searchpage.selectProduct(productname);//clicking on mackbook pro once products are seen in page
		String acutalProductHeaderVal = productinfo.getProductHeaderValue();
		Assert.assertEquals(acutalProductHeaderVal, productname);
		}
		
		
	}
	

}

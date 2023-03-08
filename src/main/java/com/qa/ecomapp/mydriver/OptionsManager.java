package com.qa.ecomapp.mydriver;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
		public ChromeOptions getChromeOtions() {
			co= new ChromeOptions();
			//true statement here coming from config file is a string coz its in key and value formt
			//inorder to convert string to beelean we are using parseboolean wrapper class concept
			if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
				System.out.println("=======Running in Headless====");
				co.addArguments("--headless");
			//
			}
			if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
				co.addArguments("--incognito");//
				System.out.println("======Running in incognito====");
			}
			return co;
			
		}

		public EdgeOptions getEdgeOtions() {
			eo= new EdgeOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless").trim())) eo.addArguments("--headless");
			
			if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) eo.addArguments("--incognito");
			return eo;
			
		}
		public FirefoxOptions getFirefoxOtions() {
			fo= new FirefoxOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
			
			if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("--incognito");
			return fo;
			
		}
		//now we have to pass this co to chromedriver
	

}

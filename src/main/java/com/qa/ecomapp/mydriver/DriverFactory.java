package com.qa.ecomapp.mydriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.ecomapp.exception.FrameworkException;

public class DriverFactory {
	//Initializing and running driver
	
	public WebDriver driver;
	public Properties prop;//
	public OptionsManager optionManager;
	
	public static String highlight;
	public static ThreadLocal <WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * this method is initializing driver on basis of giver browsername
	 * @param browserName
	 * @return this method returns the driver
	 */
	
	public WebDriver initDriver (Properties prop) {
		optionManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim(); 
		String browserName = prop.getProperty("browser").trim().toLowerCase();
		System.out.println("The Browser name is : " + browserName);
		
		if (browserName.equalsIgnoreCase("chrome")){
			//driver = new ChromeDriver(optionManager.getChromeOtions());	
			tlDriver.set(new ChromeDriver(optionManager.getChromeOtions()));
		}
		else if (browserName.equalsIgnoreCase("firefox")){
			//driver = new FirefoxDriver(optionManager.getFirefoxOtions());	
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOtions()));
		}
		else if (browserName.equalsIgnoreCase("safari")){
			
			tlDriver.set(new SafariDriver());	
		}
		else if (browserName.equalsIgnoreCase("edge")){
			//driver = new EdgeDriver(optionManager.getEdgeOtions());	
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOtions()));
		}
		else {
			System.out.println("Please pass write browser : " + browserName);
			throw new FrameworkException("NO BROWSER FOUND EXCEPTION");
			}
			
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().get(prop.getProperty("url"));
			
			
	return getDriver();
	
	}
	
	/**
	 * get the local thread copy of the driver
	 * @return WebDriver
	 */
	
	public synchronized static WebDriver getDriver() {//using synchronized to avoid deadlock, every thread will get individual copy
		return tlDriver.get();
		
	}
	
	
	
	/**
	 * this method is reading properties from the .properties file
	 * @return
	 */
	public Properties initProp () {
		//maven clean instal -Denv = "qa" -D is used for env variable
		//how to read env variable from java
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");//for getting env variable name
		System.out.println("Running Test Case on : "+ envName);
		
		try {
		if(envName==null) {
			System.out.println("Running in QA Environment");
		 ip = new FileInputStream("./src/test/resources/configure/qa.config.properties");
			
			
		}
		else {
			switch (envName.trim().toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/configure/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/configure/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/configure/stage.config.properties");
				break;
			case "production":
				ip = new FileInputStream("./src/test/resources/configure/production.config.properties");
				break;

			default:
			System.out.println("====Wrong Environment Passed==No need to run Test case=== ");
			throw new FrameworkException("WRONG ENVIRONMENT PASSED...");

				//break; After using throw exception it will not come to break
			}
		}
		}catch(FileNotFoundException e) {
			
		}
	try {
		prop.load(ip);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	
	return prop; 
	
	}
	/**
	 * to take screenshot
	 */
	public static String getScreenshot() {
	File srcFile =	((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";//this is just a path
		//with help of path we will create destination 
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}//file handler is a class that will move or copy source file to destination file
		//Now we use FileUtil instead of File Handler
		return path;
		
	}
}

package com.ecart.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ecart.pages.CartPage;
import com.ecart.pages.CheckoutPage;
import com.ecart.pages.Global;
import com.ecart.pages.LoginPage;
import com.ecart.pages.ProductsPage;
import com.ecart.utils.DataUtils;


public class BaseTest 
{
	
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	LoginPage loginPage;
	Global globalPage;
	ProductsPage productsPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	
	private Properties properties;
	Map<String, String> testData = new HashMap<>();
	String propertyFilePath;
	
	
	@BeforeSuite(alwaysRun = true)
	public void setUpData() throws Exception 
	{
		properties= new Properties();
		String propertyFilePath=System.getProperty("user.dir") + File.separator + "EcartConfig.properties";
		FileInputStream fis = new FileInputStream(propertyFilePath);
		properties.load(fis);
				
		if(properties.getProperty("environment").equals("Test")) 
		{
			testData=DataUtils.getDataFromFile("TestEnvironment");
		}
		else 
		{
			testData=DataUtils.getDataFromFile("StageEnvironment");
		}
		
	}

	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception 
	{
		properties= new Properties();
		String propertyFilePath=System.getProperty("user.dir") + File.separator +"EcartConfig.properties";
		FileInputStream fis = new FileInputStream(propertyFilePath);
		properties.load(fis);
		
		String browserName= properties.getProperty("browser");
		
		switch(browserName) 
		{
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver.set(new ChromeDriver(options));
			driver.get().get(testData.get("BaseUrl"));
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
			loginPage = new LoginPage(driver);
			globalPage= new Global(driver);
			productsPage= new ProductsPage(driver);
			cartPage = new CartPage(driver);
			checkoutPage = new CheckoutPage(driver);
			break;
			
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--incognito");
			driver.set(new EdgeDriver(edgeOptions));
			driver.get().get(testData.get("BaseUrl"));
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
			loginPage = new LoginPage(driver);
			globalPage= new Global(driver);
			productsPage= new ProductsPage(driver);
			cartPage = new CartPage(driver);
			checkoutPage = new CheckoutPage(driver);
			break;
			
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("---private");
			driver.set(new FirefoxDriver(firefoxOptions));
			driver.get().get(testData.get("BaseUrl"));
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
			loginPage = new LoginPage(driver);
			globalPage= new Global(driver);
			productsPage= new ProductsPage(driver);
			cartPage = new CartPage(driver);
			checkoutPage = new CheckoutPage(driver);
			break;
			
			
		
		
		}
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() 
	{
		driver.get().quit();
	}
	

}

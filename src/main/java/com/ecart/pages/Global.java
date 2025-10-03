package com.ecart.pages;

import org.openqa.selenium.WebDriver;

public class Global extends BasePage
{

	public Global(ThreadLocal<WebDriver> driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String getCurrentUrl() 
	{
		return driver.get().getCurrentUrl();
	}
	

}

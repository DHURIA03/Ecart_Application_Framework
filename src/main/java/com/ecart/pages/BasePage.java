package com.ecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage 

{
	ThreadLocal<WebDriver> driver = new  ThreadLocal<WebDriver>();
	
	public BasePage (ThreadLocal<WebDriver> driver) 
	{
		this.driver = driver;
	}
	
	public void clickElement(By element)
	{
		driver.get().findElement(element).click();
	}

	public void setText(By element, String text) 
	{
		driver.get().findElement(element).sendKeys(text);
	}

	public String getCurrentWindowUrl() {
		return driver.get().getCurrentUrl();
	}
	
	public String getTextFromDom(By element) 
	{
		return driver.get().findElement(element).getText();
	}
	
	public void clickDropDownElement(WebElement element , String value)
	{
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

}

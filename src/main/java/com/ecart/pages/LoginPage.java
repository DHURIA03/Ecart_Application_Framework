package com.ecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage
{
	
	public LoginPage(ThreadLocal<WebDriver> driver) 
	{
		super(driver);
		
	}

	
	private By usernameTextBox = By.xpath("//input[@id='user-name']");
	private By passwordTextBox = By.xpath("//input[@id='password']");
	private By loginBtn =        By.xpath("//input[@id='login-button']");
	private By errorMessageLogin = By.xpath("//h3[@data-test='error']");
	
	public void login(String username,String password) 
	{
		setText(usernameTextBox, username);
		setText(passwordTextBox, password);
		clickElement(loginBtn);
		
	}
	
	public String getLoginErrorMessage() 
	{
		return getTextFromDom(errorMessageLogin);
	}

}

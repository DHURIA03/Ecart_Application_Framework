package com.ecart.tests;


import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest
{
	
	
	@Test(priority=1)
	public void validateLogin() 
	{
		
		loginPage.login(testData.get("Username"), testData.get("Password"));
		Assert.assertEquals(globalPage.getCurrentUrl(), testData.get("HomePageUrl"));
		
	}
	
	@Test(priority=2)
	public void invalidPasswordLogin() 
	{
		loginPage.login(testData.get("Username"), testData.get("InvalidPassword"));
		String ActualError= loginPage.getLoginErrorMessage();
		String ExpectedError= testData.get("LoginErrorMessage");
		Assert.assertEquals(ActualError, ExpectedError, "Login Error Message Missmatch");
	}
	
	@Test(priority=3)
	public void invalidUsernameLogin() 
	{
		loginPage.login(testData.get("InvalidUsername"), testData.get("Password"));
		String ActualError= loginPage.getLoginErrorMessage();
		String ExpectedError= testData.get("LoginErrorMessage");
		Assert.assertEquals(ActualError, ExpectedError, "Login Error Message Missmatch");
	}
	
	@Test(priority=4)
	public void invalidUsernameAndPasswordLogin()  
	{
		loginPage.login(testData.get("InvalidUsername"), testData.get("InvalidPassword")); 
		String ActualError= loginPage.getLoginErrorMessage();
		String ExpectedError= testData.get("LoginErrorMessage");
		Assert.assertEquals(ActualError, ExpectedError, "Login Error Message Missmatch");
	}
	


}

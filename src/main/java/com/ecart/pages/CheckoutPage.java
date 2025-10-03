package com.ecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage
{

	public CheckoutPage(ThreadLocal<WebDriver> driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//WebElements
	private By firstname=  By.id("first-name");
	private By lastname=   By.id("last-name");
	private By zipcode=    By.id("postal-code");
	private By continueBtn=By.xpath("//input[@id='continue']");
	private By finishBtn=  By.id("finish");
	private By subTotalAmt=By.xpath("//div[@class='summary_subtotal_label']");
	private By taxAmt=     By.xpath("//div[@class='summary_tax_label']");
	private By FinalAmt=   By.xpath("//div[@class='summary_total_label']");
	private By confirmationMessage=   By.xpath("//h2[@class='complete-header']");
	
	
	
	//To fill persoal button and click continue
	public void fillPersonalDetails(String firstName,String lastName,String postalcode) 
	{
		setText(firstname,firstName);
		setText(lastname,lastName);
		setText(zipcode,postalcode);
		
	}
	
	//To click continue Button
		public void clickContinueBtn() 
		{
			clickElement(continueBtn);
		}
	
	//To get extract subtotal amount from string
	public double getSubtotalAmt() 
	{
		String amt = driver.get().findElement(subTotalAmt).getText();
		double amount =0.0;
		for (int i =0;i<amt.length();i++) 
		{
			if(amt.charAt(i)=='$') 
			{
				
				amount=Double.parseDouble(amt.substring(i+1));
			}
		}
		return amount;
		
	}
	
	//To get extract Tax amount from string
	public double getTaxlAmt() 
	{
		String amt = driver.get().findElement(taxAmt).getText();
		double amount =0.0;
		for (int i =0;i<amt.length();i++) 
		{
			if(amt.charAt(i)=='$') 
			{
				
				amount=Double.parseDouble(amt.substring(i+1));
			}
		}
		return amount;
		
	}
	
	//To get extract final amount from string
	public double getFinalTotallAmt() 
	{
		String amt = driver.get().findElement(FinalAmt).getText();
		double amount =0.0;
		for (int i =0;i<amt.length();i++) 
		{
			if(amt.charAt(i)=='$') 
			{
				
				amount=Double.parseDouble(amt.substring(i+1));
			}
		}
		return amount;
		
	}
	
	//To click Finish button
	public void clickFinishBtn() 
	{
		clickElement(finishBtn);
	}
	
	//Get order placed message
	public String getConfirmationMessage() 
	{
		return getTextFromDom(confirmationMessage);
	}
	
	

}

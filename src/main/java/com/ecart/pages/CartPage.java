package com.ecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage 
{

	public CartPage(ThreadLocal<WebDriver> driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//WebElements
	private By continueShoppingBtn = By.xpath("//button[@id='continue-shopping']");
	private By  checkoutBtn        = By.id("checkout");
	
	 
	//To remove Item from cart
	public void removeProductFromCart(String productName)
	{
		String xpathCartBtn = "((//div[@class='inventory_item_name' and text()='"+productName+"']//parent::a//parent::div//following-sibling::div)[2]"
				+ "//div)//following-sibling::button[text()='Remove']";
		clickElement(By.xpath(xpathCartBtn));
	}
	
	//To click continue shopping
	public void clickContinueShopping() 
	{
		clickElement(continueShoppingBtn);
	}
	
	//To click Checkout
	public void clickCheckout() 
	{
		clickElement(checkoutBtn);
	}

}

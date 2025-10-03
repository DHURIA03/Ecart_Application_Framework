package com.ecart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage
{

	public ProductsPage(ThreadLocal<WebDriver> driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Webelements
	private By productList =By.xpath("//div[normalize-space(@class)='inventory_item_name']");
	private By priceList =By.xpath("//div[@class='inventory_item_price']");
	private By shoppingCartBtn = By.xpath("//a[@class='shopping_cart_link']");
	private By sortBtn           = By.xpath("//select[@class='product_sort_container']");
	
	
	//Methods
	//To fetch product price from product name
	public double getPrice(String productName) 
	{
		String strPrice=driver.get().findElement(By.xpath("(//div[normalize-space(@class)='inventory_item_name' and text()='"+productName+"']//parent::a//parent::div//following-sibling::div)[2]//div")).getText();
		double price =Double.parseDouble(strPrice.substring(1));
		return price;
	}
	
	//To get product list
	public List<String> getProductList()
	{
		List<WebElement> listOfElements = driver.get().findElements(productList);
		List<String> productNameList = new ArrayList<>();
		for(WebElement element:listOfElements) 
		{
			productNameList.add(element.getText());
		}
		return productNameList;
	}
	
	//Get price list
	public List<Double> getPriceList()
	{
		List<WebElement> listOfElements = driver.get().findElements(priceList);
		List<Double> productPriceList = new ArrayList<>();
		for(WebElement element:listOfElements) 
		{
			String price=element.getText();
			
			productPriceList.add(Double.parseDouble(price.substring(1)));
		}
		return productPriceList;
	}
	

	
	//To get available product list with respective price 
	public Map<String, Double> getAvailableProductListWithPrice() 
	{
		Map<String,Double> priceValue = new LinkedHashMap<>();
		
		for(WebElement element :driver.get().findElements(productList)) 
		{
			priceValue.put(element.getText(), getPrice(element.getText()));
		}
		return priceValue; 
	}
	
	//To add specific product into cart
	public void addToCart(String productName)
	{
		String xpathCartBtn = "((//div[normalize-space(@class)='inventory_item_name' and text()='"+productName+"']//parent::a//parent::div//following-sibling::div)[2]"
				+ "//div)//following-sibling::button[text()='Add to cart']";
		clickElement(By.xpath(xpathCartBtn));
	}
	
	//To Select dropdown for sort element
	public void  sortProduct(String sortBy) 
	{
		clickDropDownElement(driver.get().findElement(sortBtn),sortBy);
	}
	
  //To click on shopping cart button
	public void clickShoppingCartBtn() 
	{
		clickElement(shoppingCartBtn);
	}
	

}

package com.ecart.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ProductTest extends BaseTest
{
	
	
	@BeforeMethod
	public void loginTest() 
	{
		loginPage.login(testData.get("Username"), testData.get("Password"));
	}
	
	
	@Test(priority=1)
	public void validateProductsList() throws Exception 
	{
		
		Map<String, Double> listActual = productsPage.getAvailableProductListWithPrice();
		String ecpectedList =testData.get("ProductNames");
		Assert.assertEquals(listActual.toString(), ecpectedList,"Products are mismatched");
	}
	
	@Test(priority=2)
	public void validateSortFunctionalitySortByNameAscending() 
	{
		
		productsPage.sortProduct(testData.get("SortNameAscending"));
		Map<String, Double> map = productsPage.getAvailableProductListWithPrice();
		List<String> Expected = new ArrayList<>(map.keySet());
		Collections.sort(Expected);
		List<String> Actual = productsPage.getProductList();
		
		Assert.assertEquals(Actual, Expected, "Sort by Name (A to Z) is not working");
	}
	
	@Test(priority=3)
	public void validateSortFunctionalitySortByNameDescending() 
	{
		
		productsPage.sortProduct(testData.get("SortNameDescending"));
		Map<String, Double> map = productsPage.getAvailableProductListWithPrice();
		List<String> Expected = new ArrayList<>(map.keySet());
		Collections.sort(Expected,Collections.reverseOrder());
		List<String> Actual = productsPage.getProductList();
		
		Assert.assertEquals(Actual, Expected, "Sort by Name (Z to A) is not working");
	}
	
	@Test(priority=4)
	public void validateSortFunctionalitySortByValueAscending() 
	{
		
		productsPage.sortProduct(testData.get("SortPriceAscending"));
		Map<String, Double> map = productsPage.getAvailableProductListWithPrice();
		List<Double> Expected = new ArrayList<>(map.values());
		Collections.sort(Expected);
		List<Double> Actual = productsPage.getPriceList();
		
		Assert.assertEquals(Actual, Expected,"Sort by Price Low to High is not working");
	}
	
	@Test(priority=5)
	public void validateSortFunctionalitySortByValueDecending()  
	{
		
		productsPage.sortProduct(testData.get("SortPriceDescending"));
		Map<String, Double> map = productsPage.getAvailableProductListWithPrice();
		List<Double> Expected = new ArrayList<>(map.values());
		Collections.sort(Expected,Collections.reverseOrder());
		List<Double> Actual = productsPage.getPriceList();
		
		Assert.assertEquals(Actual, Expected,"Sort by Price High to Low is not working");
	}
	
	@Test(priority=6)
	public void addProductsInCart() throws InterruptedException 
	{
		productsPage.addToCart(testData.get("ProductSauceLabsBackpack")); 
		productsPage.addToCart(testData.get("ProductSauceLabsOnesie"));
		productsPage.clickShoppingCartBtn();
		List<String> Actual = productsPage.getProductList();
		List<String> Expected = new ArrayList<>();
		Expected.add(testData.get("ProductSauceLabsBackpack"));
		Expected.add(testData.get("ProductSauceLabsOnesie"));
		
		Assert.assertEquals(Actual, Expected,"Products that added in cart is mismatching");
	}

}

package com.ecart.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest
{
	@BeforeMethod
	public void loginTest() 
	{
		loginPage.login(testData.get("Username"), testData.get("Password"));
	}
	
	@Test(priority=1)
	public void removeProductsFromCart() throws InterruptedException 
	{
		//Added three producr
		productsPage.addToCart(testData.get("ProductSauceLabsBackpack")); 
		productsPage.addToCart(testData.get("ProductSauceLabsOnesie"));
		productsPage.addToCart(testData.get("ProductSauceLabsBoltT-Shirt"));
		productsPage.clickShoppingCartBtn();
		List<String> Expected = new ArrayList<>(); 
		Expected.add(testData.get("ProductSauceLabsBackpack"));
		Expected.add(testData.get("ProductSauceLabsOnesie"));
		//remove third product from cart
		cartPage.removeProductFromCart(testData.get("ProductSauceLabsBoltT-Shirt"));
		List<String> Actual = productsPage.getProductList();
		
		Assert.assertEquals(Actual, Expected,"Products that added in cart is mismatching");
	}
	
	
	@Test(priority=2)
	public void validateCalculation() throws Exception 
	{
		double expectedSubTotal =0.0;
		double actualSubTotal =0.0;
		double expectedfinalTotal= 0.0;
		double actualfinalTotal= 0.0;
		double TaxValue=0.0;
		
		productsPage.addToCart(testData.get("ProductSauceLabsBackpack")); 
		productsPage.addToCart(testData.get("ProductSauceLabsOnesie"));
		productsPage.clickShoppingCartBtn();
		Map<String, Double> listOfProductToBuy = productsPage.getAvailableProductListWithPrice();
		
		for(double i: listOfProductToBuy.values()) 
		{
			expectedSubTotal=expectedSubTotal+i;
		}
		
		cartPage.clickCheckout();
		checkoutPage.fillPersonalDetails(testData.get("UserFirstName"), testData.get("UserLastName"), testData.get("UserZipCode"));
		checkoutPage.clickContinueBtn();
		
		actualSubTotal= checkoutPage.getSubtotalAmt();
		TaxValue= checkoutPage.getTaxlAmt();
		//*100 /100 did for rondingup
		expectedfinalTotal =((expectedSubTotal+TaxValue)*100)/100.0;
		actualfinalTotal =checkoutPage.getFinalTotallAmt();
		
		Assert.assertEquals(actualSubTotal, expectedSubTotal, "Actual subtotal value missmatched");
		Assert.assertEquals(actualfinalTotal, expectedfinalTotal, "Final Total value missmatched");
	}

}

package QAAcedemy.EcommerceDemo.Tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import QAAcedemy.EcommerceDemo.PageObjects.CartPage;
import QAAcedemy.EcommerceDemo.PageObjects.HomePage;
import QAAcedemy.EcommerceDemo.PageObjects.MyOrdersPage;
import QAAcedemy.EcommerceDemo.PageObjects.OrderPlacedPage;
import QAAcedemy.EcommerceDemo.PageObjects.PaymentPage;
import QAAcedemy.EcommerceDemo.TestComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OrderTests extends BaseTest {
	public  String productName2 = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData")
	public void eCommerceOrder(HashMap<String,String> input) {
		String confirmText = "Thankyou for the order.";
		String productName = input.get("product");
		HomePage homePage = loginPage.login(input.get("email"), input.get("password"));
		List<WebElement> productCards = homePage.getVisibleProductCards();
		WebElement prodcutCard = homePage.getProductCard(productName);
		homePage.addProductToCart(prodcutCard, productName);

		CartPage cartPage = homePage.goToCart();
		List<WebElement> cartCards = cartPage.getVisibleCartCards();
		WebElement productInCart = cartPage.getProductInCart(productName);

		PaymentPage paymentPage = cartPage.goToCheckOut();
		paymentPage.verifyShippingInfoLabelVisible();
		paymentPage.selectCountry("ind");

		OrderPlacedPage orderPlacedPage = paymentPage.submitOrder();
		orderPlacedPage.verifyOrderPlaced(confirmText);
	}
	
	@Test(dependsOnMethods = {"eCommerceOrder"})
	public void validateOrder() throws InterruptedException {
		HomePage homePage = loginPage.login("verma.siddharth25@gmail.com", "Test@123");
		MyOrdersPage myOrdersPage = homePage.goToMyOrdersPage();
		Assert.assertTrue(myOrdersPage.verifyOrderPlaced(productName2));
	}
		
	/*@DataProvider
	public Object[][] getData(){
		Object obj[][] = {{"verma.siddharth25@gmail.com","Test@123","ADIDAS ORIGINAL"},{"vermarishi10@gmail.com","Test@1234","ZARA COAT 3"}};
	 return obj;	
	}*/
	/*@DataProvider
	public Object[][] getData(){
		HashMap <String,String>input1 = new HashMap<String,String>(); 
		input1.put("email", "verma.siddharth25@gmail.com");
		input1.put("password", "Test@123");
		input1.put("product", "ADIDAS ORIGINAL");
		HashMap <String,String>input2 = new HashMap<String,String>(); 
		input2.put("email", "vermarishi10@gmail.com");
		input2.put("password", "Test@1234");
		input2.put("product", "ZARA COAT 3");
		Object input[][] = {{input1},{input2}}; 
		return input;
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>> list = readJsonData(".\\src\\test\\java\\QAAcedemy\\Data\\input.json");
		Object input[][] = {{list.get(0)},{list.get(1)}}; 
		return input;
	}
	
	}


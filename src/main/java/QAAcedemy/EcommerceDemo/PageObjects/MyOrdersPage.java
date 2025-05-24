package QAAcedemy.EcommerceDemo.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QAAcedemy.EcommerceDemo.Utilities.CommonUtilities;

public class MyOrdersPage extends CommonUtilities {
	WebDriver driver;
	public MyOrdersPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> productsInMyOrders;
	
	By productsInMyOrdersLocator = By.xpath("//tr/td[2]");
	
	public boolean verifyOrderPlaced(String productName) throws InterruptedException {
		waitForElementVisibilityByLocator(productsInMyOrdersLocator);
		return(productsInMyOrders.stream().anyMatch(p->p.getText().equals(productName)));
		
	}

}

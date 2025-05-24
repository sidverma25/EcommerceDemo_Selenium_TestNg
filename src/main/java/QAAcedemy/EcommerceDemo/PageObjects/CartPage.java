package QAAcedemy.EcommerceDemo.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QAAcedemy.EcommerceDemo.Utilities.CommonUtilities;

public class CartPage extends CommonUtilities {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartWrap")
	List<WebElement> cartCards;
	
	By cartCardElements = By.cssSelector(".cartWrap");
	
	public List<WebElement> getVisibleCartCards() {
		waitForElementVisibilityByLocator(cartCardElements);
		return cartCards;
	}

	public WebElement getProductInCart(String productName) {
		WebElement productInCart = cartCards.stream().filter(p->p.findElement(By.cssSelector("h3")).getText().equals(productName)).findFirst().orElse(null);
		Assert.assertNotEquals(productInCart, null);
		return productInCart;
	}
}

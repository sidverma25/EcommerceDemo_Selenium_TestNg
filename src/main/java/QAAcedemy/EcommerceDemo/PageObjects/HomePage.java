package QAAcedemy.EcommerceDemo.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import QAAcedemy.EcommerceDemo.Utilities.CommonUtilities;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends CommonUtilities {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> productCards;
	
	@FindBy(xpath = "//*[contains(@routerlink,'cart')]")
	WebElement goToCartBtn;

	
	By addToCart= By.cssSelector(".fa-shopping-cart");
	By productCardLocator = By.cssSelector(".mb-3");
	By addedToCartPopup = By.id("toast-container");
	
	public List<WebElement> getVisibleProductCards() {
		waitForElementVisibilityByLocator(productCardLocator);
		return productCards;
	}
	
	public WebElement getProductCard(String productName) {
		WebElement productCard = productCards.stream().filter(p->p.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		Assert.assertNotEquals(productCard, null);
		return productCard;
	}
	
	public void addProductToCart(WebElement productCard, String productName) {
		productCard.findElement(addToCart).click();
		waitForElementVisibilityByLocator(addedToCartPopup);
		waitForElementInvisibilityByLocator(addedToCartPopup);
	}
	public CartPage goToCart() {
		goToCartBtn.click();
		return new CartPage(driver);
	}
}

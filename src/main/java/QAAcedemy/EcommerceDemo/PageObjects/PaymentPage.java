package QAAcedemy.EcommerceDemo.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import QAAcedemy.EcommerceDemo.Utilities.CommonUtilities;

public class PaymentPage extends CommonUtilities {
	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryDropdown;
	
	@FindBy(css="button[class*='ta-item']:nth-child(3)")
	WebElement countryInDropdown;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	
	By shippingInformationLabelLocator = By.xpath("//div[text() = ' Shipping Information ']");
	By dropDownElement = By.cssSelector("button[class*='ta-item']");
	
	public void verifyShippingInfoLabelVisible() {
		this.waitForElementVisibilityByLocator(shippingInformationLabelLocator);	
	}

	public void selectCountry(String string) {
		Actions a = new Actions(driver);
		a.sendKeys(countryDropdown, "ind").build().perform();
		this.waitForElementVisibilityByLocator(dropDownElement);
		countryInDropdown.click();
	}

	public OrderPlacedPage submitOrder() {
		placeOrder.click();
		return new OrderPlacedPage(driver);
	}
	
	
}

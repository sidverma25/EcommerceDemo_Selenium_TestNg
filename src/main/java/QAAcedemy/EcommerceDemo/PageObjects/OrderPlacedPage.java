package QAAcedemy.EcommerceDemo.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QAAcedemy.EcommerceDemo.Utilities.CommonUtilities;

public class OrderPlacedPage extends CommonUtilities {
	WebDriver driver;
	public OrderPlacedPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement orderConfirmationLabel;
	By orderConfirmationLabelLocator = By.cssSelector(".hero-primary");
	
	public void verifyOrderPlaced(String confirmationText) {
		this.waitForElementVisibilityByLocator(orderConfirmationLabelLocator);
		String labelText = orderConfirmationLabel.getText();
		Assert.assertTrue(confirmationText.equalsIgnoreCase(labelText));
	}


	
	
	
}

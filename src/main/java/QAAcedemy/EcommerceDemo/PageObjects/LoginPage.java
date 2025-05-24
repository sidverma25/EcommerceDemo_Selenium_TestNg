package QAAcedemy.EcommerceDemo.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QAAcedemy.EcommerceDemo.Utilities.CommonUtilities;

public class LoginPage extends CommonUtilities  {
	WebDriver driver;
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submitBtn;
	
	By popup = By.id("toast-container");
	By errorLabel = By.cssSelector(".invalid-feedback div");

	public HomePage login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitBtn.click();
		return(new HomePage(driver));
	}
	
	public String getInvalidLoginMessage() {
		this.waitForElementVisibilityByLocator(popup);
		String popupMessage = driver.findElement(popup).getText();
		this.waitForElementInvisibilityByLocator(popup);
		return popupMessage;
	}

	public String getInvalidEmailMessage() {
		this.waitForElementVisibilityByLocator(errorLabel);
		String errorLabelMessage = driver.findElement(errorLabel).getText();
		return errorLabelMessage;
	}
}

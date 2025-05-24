package QAAcedemy.EcommerceDemo.Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import QAAcedemy.EcommerceDemo.PageObjects.MyOrdersPage;
import QAAcedemy.EcommerceDemo.PageObjects.PaymentPage;

public class CommonUtilities {
	WebDriver driver;
	public CommonUtilities(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement myOrdersBtn;
	
	public void waitForElementVisibilityByLocator(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementVisibility(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public void	waitForElementInvisibilityByLocator(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	public PaymentPage goToCheckOut() {
		checkOutBtn.click();
		return new PaymentPage(driver);
	}
	public MyOrdersPage goToMyOrdersPage() {
		waitForElementVisibility(myOrdersBtn);
		myOrdersBtn.click();
		return new MyOrdersPage(driver);
	}
}

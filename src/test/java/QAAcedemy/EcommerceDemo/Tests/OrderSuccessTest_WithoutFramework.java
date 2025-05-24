package QAAcedemy.EcommerceDemo.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrderSuccessTest_WithoutFramework {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Browser\\chromedriver.exe");
		String productText = "ADIDAS ORIGINAL";
		String orderConfirmationText = "Thankyou for the order.";
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(5000));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("verma.siddharth25@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> productCards = driver.findElements(By.cssSelector(".mb-3"));
		WebElement productCard = productCards.stream().filter(p->p.findElement(By.tagName("b")).getText().equals(productText)).findFirst().orElse(null);
		Assert.assertNotEquals(productCard, null);
		productCard.findElement(By.cssSelector(".fa-shopping-cart")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		driver.findElement(By.xpath("//*[contains(@routerlink,'cart')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartWrap")));
		List<WebElement> cartCards = driver.findElements(By.cssSelector(".cartWrap"));
		Boolean itemFoundInCart = cartCards.stream().anyMatch(p->p.findElement(By.cssSelector("h3")).getText().equals(productText));
		Assert.assertTrue(itemFoundInCart);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement paymentMethodLabel = driver.findElement(By.xpath("//div[contains(text(),'Payment Method')]"));
		wait.until(ExpectedConditions.visibilityOf(paymentMethodLabel));
		WebElement selectCountry = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry, "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='ta-item']")));
		driver.findElement(By.cssSelector("button[class*='ta-item']:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		WebElement oderConfirmationLabel = driver.findElement(By.cssSelector(".hero-primary"));
		Assert.assertTrue(orderConfirmationText.equalsIgnoreCase(oderConfirmationLabel.getText()));
		driver.quit();
	}
}

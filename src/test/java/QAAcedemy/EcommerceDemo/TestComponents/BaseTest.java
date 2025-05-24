package QAAcedemy.EcommerceDemo.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import QAAcedemy.EcommerceDemo.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;
	public LoginPage loginPage;

	public WebDriver init() throws IOException {
		Properties properties = new Properties();
		FileInputStream globalData = new FileInputStream(
				".\\src\\main\\java\\QAAcedemy\\EcommerceDemo\\Resources\\GlobalData.properties");
		properties.load(globalData);
		String browser = properties.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			// code to invoke edge browser
		} else if (browser.equalsIgnoreCase("gecko")) {
			// code to invoke firefox browser
		}
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod
	public void launchApplication() throws IOException {
		WebDriver driver = init();
		driver.get("https://rahulshettyacademy.com/client");
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void closeApplication() {
		driver.quit();
	}
	
	public List<HashMap<String,String>> readJsonData(String filePath) throws IOException {
		//Read Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//Convert String to Hasmap using Jackson Data Bind.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}

}

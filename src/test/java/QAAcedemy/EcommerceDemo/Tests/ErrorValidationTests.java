package QAAcedemy.EcommerceDemo.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import QAAcedemy.EcommerceDemo.PageObjects.HomePage;
import QAAcedemy.EcommerceDemo.TestComponents.BaseTest;

public class ErrorValidationTests extends BaseTest {

	@Test
	public void invalidLogin() {
		loginPage.login("verma.siddharth25@gmail.com", "Test@1234");
		String invalidLoginMessage = loginPage.getInvalidLoginMessage();
		Assert.assertEquals(invalidLoginMessage, "Incorrect email or password.");
	}

	@Test
	public void incorrectEmail() {
		loginPage.login("verma.siddharth25", "Test@1234");
		String errorLabelMessage = loginPage.getInvalidEmailMessage();
		Assert.assertEquals(errorLabelMessage, "*Enter Valid Email");
	}

}

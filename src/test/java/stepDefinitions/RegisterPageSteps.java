package stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOptions.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.RegisterPageObj;


public class RegisterPageSteps {
	WebDriver driver;
	RegisterPageObj registerPage;

	public RegisterPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
	}

	@Given("the user is on the registration page")
	public void theUserIsOnTheRegistrationPage() {
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.openPageUrl("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}


	@Then("they should see a registration success message")
	public void theyShouldSeeARegistrationSuccessMessage() {
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}


}

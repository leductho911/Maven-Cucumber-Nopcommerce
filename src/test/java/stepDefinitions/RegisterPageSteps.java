package stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOptions.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.RegisterPageObj;

import java.util.List;
import java.util.Map;

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

	@When("they fill in their registration details")
	public void theyFillInTheirRegistrationDetails(DataTable dataTable) {
		List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);

		String firstName = details.get(0).get("First Name");
		String lastName = details.get(0).get("Last Name");
		String email = details.get(0).get("Email");
		String password = details.get(0).get("Password");

		registerPage.inputToTextboxByLabel("First name", firstName);
		registerPage.inputToTextboxByLabel("Last name", lastName);
		registerPage.inputToTextboxByLabel("Email", email);
		registerPage.inputToTextboxByLabel("Password", password);
		registerPage.inputToTextboxByLabel("Confirm password", password);

	}

	@And("they click the Register button")
	public void theyClickTheRegisterButton() {
		registerPage.clickToButton("Register");
	}

	@Then("they should see a registration success message")
	public void theyShouldSeeARegistrationSuccessMessage() {
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

}

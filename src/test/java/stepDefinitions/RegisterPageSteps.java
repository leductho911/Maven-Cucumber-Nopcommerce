package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;
import utils.Driver;
import static org.junit.Assert.assertEquals;


public class RegisterPageSteps {
	RegisterPage registerPage = new RegisterPage();

	@Given("the user is on the registration page")
	public void theUserIsOnTheRegistrationPage() {
//		Driver.get().get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		registerPage.openPageUrl("https://demo.nopcommerce.com/register?returnUrl=%2F");
		String expectedUrl = "https://demo.nopcommerce.com/register?returnUrl=%2F";
		assertEquals(Driver.get().getCurrentUrl(), expectedUrl);
	}

	@When("they click the {string} button")
	public void theyClickTheButton(String buttonName) {
		registerPage.clickToButton(buttonName);
	}

	@Then("they should see First name error message appear")
	public void theyShouldSeeAnErrorMessageAppear() {
		assertEquals("First name is required.", registerPage.getErrorMessage());
	}

	@When("the user input to {string} textbox with value {string}")
	public void theUserInputToTextboxWithValue(String textboxLabel, String value) {
		registerPage.inputToTextboxByLabel(textboxLabel, value);
	}

	@Then("they should see Registration completed message appeared")
	public void theyShouldSeeRegistrationCompletedMessageAppeared() {
		assertEquals("Your registration completed", registerPage.getRegistrationCompletedMessage());
	}
}




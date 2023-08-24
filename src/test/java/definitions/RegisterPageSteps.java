package definitions;

import commons.GlobalConstants;
import hooks.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;
import utils.DataFaker;
import static org.junit.Assert.assertEquals;


public class RegisterPageSteps {
	RegisterPage registerPage = new RegisterPage();
	DataFaker dataFaker;
	public static String validEmail, validPassword, validFirstName, validLastname;
	private ScenarioContext scenarioContext;

	public RegisterPageSteps(ScenarioContext context) {
		scenarioContext = context;
		dataFaker = DataFaker.getDataFaker();
		validFirstName = dataFaker.getFirstName();
		validLastname = dataFaker.getLastName();
		validEmail = dataFaker.getEmail();
		validPassword = dataFaker.getPassword();
	}

	@Given("the user is on the registration page")
	public void theUserIsOnTheRegistrationPage() {
		registerPage.openPageUrl(GlobalConstants.REGISTER_URL);
		assertEquals(GlobalConstants.REGISTER_URL, registerPage.getPageUrl());
	}

	@Then("they should see First name error message appear")
	public void theyShouldSeeAnErrorMessageAppear() {
		assertEquals("First name is required.", registerPage.getFirstNameErrorMessage());
	}

	@Then("they should see Registration completed message appeared")
	public void theyShouldSeeRegistrationCompletedMessageAppeared() {
		assertEquals("Your registration completed", registerPage.getRegistrationCompletedMessage());
	}

	@When("the user enter valid registration details")
	public void theUserEnterValidRegistrationDetails() {
		registerPage.inputToTextboxByLabel("First name", validFirstName);
		registerPage.inputToTextboxByLabel("Last name", validLastname);
		registerPage.inputToTextboxByLabel("Email", validEmail);
		scenarioContext.setContext("validEmail", validEmail);
		registerPage.inputToTextboxByLabel("Password", validPassword);
		scenarioContext.setContext("validPassword", validPassword);
		registerPage.inputToTextboxByLabel("Confirm password", validPassword);
	}
}




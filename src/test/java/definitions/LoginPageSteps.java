package definitions;

import commons.GlobalConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;

import static org.junit.Assert.assertEquals;

public class LoginPageSteps {
	LoginPage loginPage = new LoginPage();

	@Given("the user is on the login page")
	public void theUserIsOnTheLoginPage() {
		loginPage.openPageUrl(GlobalConstants.LOGIN_URL);
		assertEquals(GlobalConstants.LOGIN_URL, loginPage.getPageUrl());
	}

	@When("the user enters their valid username and password")
	public void theUserEntersTheirValidUsernameAndPassword() {
		loginPage.inputToTextboxByLabel("Email", RegisterPageSteps.validEmail);
		loginPage.inputToTextboxByLabel("Password", RegisterPageSteps.validPassword);
	}
}

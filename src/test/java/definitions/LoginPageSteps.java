package definitions;

import commons.GlobalConstants;
import hooks.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;

import static org.junit.Assert.assertEquals;

public class LoginPageSteps {
	LoginPage loginPage = new LoginPage();
	private ScenarioContext scenarioContext;

	public LoginPageSteps(ScenarioContext context) {
		scenarioContext = context;
	}

	@Given("the user is on the login page")
	public void theUserIsOnTheLoginPage() {
		loginPage.openPageUrl(GlobalConstants.LOGIN_URL);
		assertEquals(GlobalConstants.LOGIN_URL, loginPage.getPageUrl());
	}

	@When("the user enters their valid username and password")
	public void theUserEntersTheirValidUsernameAndPassword() {
		loginPage.inputToTextboxByLabel("Email", scenarioContext.getContext("validEmail").toString());
		loginPage.inputToTextboxByLabel("Password", scenarioContext.getContext("validPassword").toString());

	}
}

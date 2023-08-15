package stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOptions.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LoginPageSteps {
	WebDriver driver;
	LoginPageObj loginPage;
	HomePageObj homePage;

	public LoginPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
	}

	@Given("the user is on the login page")
	public void theUserIsOnTheLoginPage() {
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.openPageUrl("https://demo.nopcommerce.com/login?returnUrl=%2F");
	}

	@When("they enter their login credentials")
	public void theyEnterTheirLoginCredentials(DataTable dataTable) {
		List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);

		loginPage.inputToTextboxByLabel("Email", details.get(0).get("Email"));
		loginPage.inputToTextboxByLabel("Password", details.get(0).get("Password"));
	}

	@And("they click the login button")
	public void theyClickTheLoginButton() {
		loginPage.clickToButton("Log in");
	}

	@Then("they should be logged in successfully")
	public void theyShouldBeLoggedInSuccessfully() {
		homePage = PageGeneratorManager.getHomePage(driver);
		assertTrue(homePage.isHomepageDisplayed());
	}
}

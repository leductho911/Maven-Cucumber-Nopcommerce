package stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOptions.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;

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

	@Then("they should be logged in successfully")
	public void theyShouldBeLoggedInSuccessfully() {
		homePage = PageGeneratorManager.getHomePage(driver);
		assertTrue(homePage.isHomepageDisplayed());
	}
}

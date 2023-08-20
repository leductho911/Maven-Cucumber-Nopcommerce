package stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOptions.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.CommonPageObj;

public class CommonPageSteps {
	WebDriver driver;
	CommonPageObj commonPage;

	public CommonPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		commonPage = PageGeneratorManager.getCommonPage(driver);
	}

	@When("the user input to {string} textbox with value {string}")
	public void theUserInputToTextboxWithValue(String textboxLabel, String value) {
		commonPage.inputToTextboxByLabel(textboxLabel, value);
	}

	@When("they click the {string} button")
	public void theyClickTheButton(String buttonName) {
		commonPage.clickToButton(buttonName);
	}
}

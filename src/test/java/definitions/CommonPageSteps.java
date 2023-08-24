package definitions;

import io.cucumber.java.en.When;
import pages.CommonPage;


public class CommonPageSteps {
	CommonPage commonPage = new CommonPage();

	@When("the user input to {string} textbox with value {string}")
	public void theUserInputToTextboxWithValue(String textboxLabel, String value) {
		commonPage.inputToTextboxByLabel(textboxLabel, value);
	}

	@When("they click the {string} button")
	public void theyClickTheButton(String buttonName) {
		commonPage.clickToButton(buttonName);
	}


}




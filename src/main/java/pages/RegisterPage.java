package pages;

import pageUIs.RegisterPageUI;

public class RegisterPage extends BasePage {


	public String getErrorMessage() {
		waitForElementVisible(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getRegistrationCompletedMessage() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}
}

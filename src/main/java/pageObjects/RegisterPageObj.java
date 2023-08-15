package pageObjects;

//import io.qameta.allure.Step;
//import org.testng.Assert;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObj extends BasePage {
	private WebDriver driver;

	public RegisterPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}



	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtFirstnameTextbox() {
		waitForElementVisible(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}
	public String getErrorMessageAtLastnameTextbox() {
		waitForElementVisible(RegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}


	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}
	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE);
	}
	public String getRegisterSuccessMessage() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}
	public String getRegisterFailMessage() {
		waitForElementVisible(RegisterPageUI.REGISTER_FAIL_MESSAGE);
		return getElementText(RegisterPageUI.REGISTER_FAIL_MESSAGE);
	}
}

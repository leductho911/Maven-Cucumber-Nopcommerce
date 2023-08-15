package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObj extends BasePage {
	private WebDriver driver;

	public LoginPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(LoginPageUI.EMAIL_ERROR_MESSAGE);
	}


	public String getErrorMessageAtLoginPage() {
		waitForElementVisible(LoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(LoginPageUI.LOGIN_ERROR_MESSAGE);
	}
}

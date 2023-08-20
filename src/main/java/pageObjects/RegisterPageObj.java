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

	public String getRegisterSuccessMessage() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}


}

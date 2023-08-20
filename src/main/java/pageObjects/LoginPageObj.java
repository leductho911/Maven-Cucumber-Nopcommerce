package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class LoginPageObj extends BasePage {
	private WebDriver driver;

	public LoginPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}

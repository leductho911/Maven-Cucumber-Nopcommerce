package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObj extends BasePage {
	private WebDriver driver;

	public HomePageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isHomepageDisplayed() {
		waitForElementVisible(HomePageUI.WELCOME_TEXT);
		return isElementDisplayed(HomePageUI.WELCOME_TEXT);
	}

}

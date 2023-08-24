package pages;

import pageUIs.HomePageUI;

public class HomePage extends BasePage {

	public boolean isHomePageDisplayed() {
		waitForElementVisible(HomePageUI.WELCOME_TEXT);
		return isElementDisplayed(HomePageUI.WELCOME_TEXT);
	}
}

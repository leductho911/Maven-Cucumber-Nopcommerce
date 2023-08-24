package pages;

import pageUIs.CommonPageUI;


public class CommonPage extends BasePage {


	public void clickToButton(String button) {
		waitForElementClickable(CommonPageUI.BUTTON, button);
		clickToElement(CommonPageUI.BUTTON, button);
	}

}

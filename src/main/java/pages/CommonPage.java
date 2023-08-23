package pages;

import org.openqa.selenium.WebElement;
import pageUIs.CommonPageUI;

import java.util.List;
import java.util.stream.Collectors;

public class CommonPage extends BasePage {




	public void clickToLinkAtHeader(String linkText) {
		waitForElementClickable(CommonPageUI.HEADER_LINKS, linkText);
		clickToElement(CommonPageUI.HEADER_LINKS, linkText);
	}


	public void clickToLinkAtFooter(String linkText) {
		waitForElementClickable(CommonPageUI.FOOTER_LINKS, linkText);
		clickToElement(CommonPageUI.FOOTER_LINKS, linkText);
	}

	public void inputToTextareaByLabel(String label, String value) {
		waitForElementClickable(CommonPageUI.LABEL_TEXTAREA, label);
		sendKeysToElement(CommonPageUI.LABEL_TEXTAREA, value, label);
	}

	public void checkToCheckboxByLabel(String label) {
		waitForElementClickable(CommonPageUI.LABEL_CHECKBOX, label);
		checkToDefaultCheckboxOrRadio(CommonPageUI.LABEL_CHECKBOX, label);
	}

	public void uncheckToCheckboxByLabel(String label) {
		waitForElementClickable(CommonPageUI.LABEL_CHECKBOX, label);
		uncheckToDefaultCheckbox(CommonPageUI.LABEL_CHECKBOX, label);
	}

	public void selectItemInDropdownByLabel(String label, String value) {
		waitForElementClickable(CommonPageUI.LABEL_DROPDOWN, label);
		selectItemInDefaultDropdown(CommonPageUI.LABEL_DROPDOWN, value, label);
	}

	public void openPageByPageName(String pageName) {
		waitForElementClickable(CommonPageUI.LEFT_SIDEBAR_PAGE_LINKS, pageName);
		clickToElement(CommonPageUI.LEFT_SIDEBAR_PAGE_LINKS, pageName);
	}

	public String getMessageAppearAtNotificationBar() {
		waitForElementVisible(CommonPageUI.NOTICICATION_BAR_MESSAGE);
		return getElementText(CommonPageUI.NOTICICATION_BAR_MESSAGE);
	}

	public void clickToCloseButtonAtNotificationBar() {
		waitForElementClickable(CommonPageUI.NOTICICATION_BAR_CLOSE_BUTTON);
		clickToElement(CommonPageUI.NOTICICATION_BAR_CLOSE_BUTTON);
		sleepInSecond(1);
	}

	public void clickToLinkAtNotificationBar(String linkText) {
		waitForElementClickable(CommonPageUI.NOTICICATION_BAR_LINK, linkText);
		clickToElement(CommonPageUI.NOTICICATION_BAR_LINK, linkText);
	}

	public void hoverMouseToMenu(String label) {
		waitForElementVisible(CommonPageUI.MENU_LINK_DYNAMIC, label);
		hoverMouseToElement(CommonPageUI.MENU_LINK_DYNAMIC, label);
	}

	public void clickToLinkAtMenu(String label) {
		waitForElementClickable(CommonPageUI.MENU_LINK_DYNAMIC, label);
		clickToElement(CommonPageUI.MENU_LINK_DYNAMIC, label);
	}




	public void checkToRadioButtonByLabel(String label) {
		waitForElementClickable(CommonPageUI.LABEL_RADIO, label);
		checkToDefaultCheckboxOrRadio(CommonPageUI.LABEL_RADIO, label);
	}

	public void clickToLink(String linkText) {
		waitForElementClickable(CommonPageUI.TEXTVALUE_LINK_DYNAMIC, linkText);
		clickToElement(CommonPageUI.TEXTVALUE_LINK_DYNAMIC, linkText);
	}

	public String getPageBody() {
		waitForElementVisible(CommonPageUI.PAGE_BODY);
		return getElementText(CommonPageUI.PAGE_BODY);
	}

	public List<String> getProductList() {
		return getListWebElement(CommonPageUI.PRODUCT_NAME)
				.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	public void clickToButtonOfAProduct(String productName, String buttonName) {
		waitForElementClickable(CommonPageUI.PRODUCT_BUTTONS_DYNAMIC, productName, buttonName);
		clickToElement(CommonPageUI.PRODUCT_BUTTONS_DYNAMIC, productName, buttonName);
		sleepInSecond(1);
	}

	public void inputToTextboxByPlaceholder(String placeHolder, String value) {
		waitForElementVisible(CommonPageUI.PLACEHOLDER_TEXTBOX_DYNAMIC, placeHolder);
		sendKeysToElement(CommonPageUI.PLACEHOLDER_TEXTBOX_DYNAMIC, value, placeHolder);
	}

	public int getOrderNumber() {
		waitForElementVisible(CommonPageUI.ORDER_NUMBER);
		String orderNumber = getElementText(CommonPageUI.ORDER_NUMBER);
		return extractOrderNumber(orderNumber);
	}

	public boolean isPageDisplayed(String pageName) {
		return isElementDisplayed(CommonPageUI.PAGE_TITLE, pageName);
	}



}

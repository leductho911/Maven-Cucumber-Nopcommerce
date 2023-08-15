package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ProductReviewPageUI;

public class ProductReviewPageObj extends BasePage {
	private WebDriver driver;

	public ProductReviewPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getResultMessage() {
		waitForElementVisible(ProductReviewPageUI.RESULT_MESSAGE);
		return getElementText(ProductReviewPageUI.RESULT_MESSAGE);
	}
	public void inputToReviewTitle(String title) {
		waitForElementVisible(ProductReviewPageUI.REVIEW_TITLE);
		sendKeysToElement(ProductReviewPageUI.REVIEW_TITLE, title);
	}

	public void inputToReviewText(String text) {
		waitForElementVisible(ProductReviewPageUI.REVIEW_TEXT);
		sendKeysToElement(ProductReviewPageUI.REVIEW_TEXT, text);
	}


}

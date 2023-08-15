package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.ProductDetailPageUI;

public class ProductDetailPageObj extends BasePage {
	private WebDriver driver;

	public ProductDetailPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public ProductReviewPageObj clickToProductReviewLink() {
		waitForElementClickable(ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
		return PageGeneratorManager.getProductReviewPage(driver);
	}


	public void selectProcessor(String processor) {
		waitForElementClickable(ProductDetailPageUI.PROCESSOR_SELECT);
		selectItemInDefaultDropdown(ProductDetailPageUI.PROCESSOR_SELECT, processor);
	}

	public void selectRam(String ram) {
		waitForElementClickable(ProductDetailPageUI.RAM_SELECT);
		selectItemInDefaultDropdown(ProductDetailPageUI.RAM_SELECT, ram);
	}

	public float getProductPrice() {
		sleepInSecond(1);
		waitForElementVisible(ProductDetailPageUI.PRODUCT_PRICE);
		return convertStringToFloat(getElementText(ProductDetailPageUI.PRODUCT_PRICE));
	}
}

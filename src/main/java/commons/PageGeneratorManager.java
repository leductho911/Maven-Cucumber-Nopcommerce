package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.*;


public class PageGeneratorManager {
	public static HomePageObj getHomePage(WebDriver driver) {
		return new HomePageObj(driver);
	}


	public static RegisterPageObj getRegisterPage(WebDriver driver) {
		return new RegisterPageObj(driver);
	}


	public static LoginPageObj getLoginPage(WebDriver driver) {
		return new LoginPageObj(driver);
	}


	public static ChangePasswordPageObj getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPageObj(driver);
	}

	public static ProductDetailPageObj getProductDetailPage(WebDriver driver) {
		return new ProductDetailPageObj(driver);
	}

	public static ProductReviewPageObj getProductReviewPage(WebDriver driver) {
		return new ProductReviewPageObj(driver);
	}

	public static DesktopsPageObj getDesktopsPage(WebDriver driver) {
		return new DesktopsPageObj(driver);
	}

	public static CompareProductsPageObj getCompareProductsPage(WebDriver driver) {
		return new CompareProductsPageObj(driver);
	}

	public static RecentlyViewedProductsPageObj getRecentlyViewedProductsPage(WebDriver driver) {
		return new RecentlyViewedProductsPageObj(driver);
	}

	public static OrdersPageObj getOrdersPage(WebDriver driver) {
		return new OrdersPageObj(driver);
	}

	public static OrderDetailsPageObj getOrderDetailsPage(WebDriver driver) {
		return new OrderDetailsPageObj(driver);
	}
}

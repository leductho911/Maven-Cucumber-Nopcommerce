package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.*;


public class PageGeneratorManager {
	public static HomePageObj getHomePage(WebDriver driver) {
		return new HomePageObj(driver);
	}
	public static CommonPageObj getCommonPage(WebDriver driver) {
		return new CommonPageObj(driver);
	}

	public static RegisterPageObj getRegisterPage(WebDriver driver) {
		return new RegisterPageObj(driver);
	}
	public static LoginPageObj getLoginPage(WebDriver driver) {
		return new LoginPageObj(driver);
	}

}

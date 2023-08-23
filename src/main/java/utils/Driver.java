package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Driver {

	private Driver(){}

	private static WebDriver driver;

	public static WebDriver get(){
		String browser = System.getProperty("browser");
		if (driver == null) {
			try {
				if (browser == null) {
					browser = "chrome";
				}

				if ("chrome".equalsIgnoreCase(browser)) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				} else if ("firefox".equalsIgnoreCase(browser)) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				} else {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return driver;

	}

	public static void closeDriver(){
		if(driver != null){
			driver.quit();
			driver = null;
		}
	}
}


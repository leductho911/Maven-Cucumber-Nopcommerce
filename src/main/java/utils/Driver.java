package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


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
				switch (browser.toLowerCase()) {
					case "firefox":
						WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver();
						break;
					case "edge":
						WebDriverManager.edgedriver().setup();
						driver = new EdgeDriver();
						break;
					case "chrome-headless":
						WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
						break;
					case "firefox-headless":
						WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver(new FirefoxOptions().addArguments("--headless=new"));
						break;
					default:
						WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver();
						break;
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


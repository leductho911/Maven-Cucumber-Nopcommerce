package cucumberOptions;
import java.time.Duration;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	private static WebDriver driver;

	@Before
	public synchronized static WebDriver openAndQuitBrowser() {
		String browser = System.getProperty("BROWSER");
		System.out.println("Browser name run by command line = " + browser);

		if (driver == null) {
			try {
				if (browser == null) {
					browser = System.getenv("BROWSER");
					if (browser == null) {
						browser = "chrome";
					}
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

			}
			finally {
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		}
		return driver;
	}

	private static class BrowserCleanup implements Runnable {
		@Override
		public void run() {
			close();
		}
	}

	public static void close() {
		try {
			if (driver != null) {
				openAndQuitBrowser().quit();
			}
		} catch (UnreachableBrowserException e) {
			System.out.println("Can not close the browser");
		}
	}

}
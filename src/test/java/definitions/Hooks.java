package definitions;
import java.time.Duration;

import io.cucumber.java.*;


import commons.GlobalConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Driver;


public class Hooks {

	@Before(order = 0)
	public void beforeScenario() {
		Driver.get().manage().window().maximize();
		Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
	}

	@After(order = 0)
	public void afterScenario(Scenario scenario) {
		try {
			String screenshotName = scenario.getName().replaceAll("", "_");
			if (scenario.isFailed()) {
				TakesScreenshot ts = (TakesScreenshot) Driver.get();
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "img/png", screenshotName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Driver.closeDriver();
	}

}
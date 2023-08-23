package definitions;
import java.time.Duration;

import io.cucumber.java.*;


import commons.GlobalConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Driver;
import utils.Log;


public class Hooks {

	@Before(order = 0)
	public void beforeScenario(Scenario scenario) {
		Driver.get().manage().window().maximize();
		Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		String scenarioName = scenario.getName().replaceAll(" ", "_");
		Log.info("Scenario started: " + scenarioName);
	}

	@After(order = 0)
	public void afterScenario(Scenario scenario) {
		String screenshotName = null;
		try {
			screenshotName = scenario.getName().replaceAll(" ", "_");
			if (scenario.isFailed()) {
				Log.error("Scenario failed: " + screenshotName);
				TakesScreenshot ts = (TakesScreenshot) Driver.get();
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "img/png", screenshotName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.info("Scenario ended: " + screenshotName);
		Driver.closeDriver();
	}

}
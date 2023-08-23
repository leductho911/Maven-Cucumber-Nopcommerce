package stepDefinitions;
import java.time.Duration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import commons.GlobalConstants;
import utils.Driver;

public class Hooks {

	@Before(order = 0)
	public void setUp() {
		Driver.get().manage().window().maximize();
		Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
	}

	@After(order = 0)
	public void tearDown(Scenario scenario) {
		Driver.closeDriver();
	}



}
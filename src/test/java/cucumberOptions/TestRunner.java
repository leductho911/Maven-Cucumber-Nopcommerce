package cucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepDefinitions",
		monochrome = true,
		plugin = {"pretty",
				"html:target/cucumber-reports/TestRunner.html",
				"json:target/cucumber-reports/TestRunner.json" },
		snippets = CucumberOptions.SnippetType.CAMELCASE,
		tags = "@register_login")

public class TestRunner {
}



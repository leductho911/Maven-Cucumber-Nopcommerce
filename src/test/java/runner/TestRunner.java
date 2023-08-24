package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"definitions", "hooks"},
		monochrome = true,
		plugin = {"pretty",
				"html:target/cucumber-reports/TestRunner.html",
				"json:target/cucumber-reports/TestRunner.json",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" },
		snippets = CucumberOptions.SnippetType.CAMELCASE,
		tags = "@register_and_login")

public class TestRunner {
}



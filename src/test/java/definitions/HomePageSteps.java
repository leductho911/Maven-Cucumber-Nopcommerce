package definitions;

import io.cucumber.java.en.Then;
import pages.HomePage;
import static org.junit.Assert.assertTrue;

public class HomePageSteps {
	HomePage homePage = new HomePage();
	@Then("the user should be redirected to the Homepage")
	public void theUserShouldBeRedirectedToTheHomepage() {
		assertTrue(homePage.isHomePageDisplayed());
	}
}

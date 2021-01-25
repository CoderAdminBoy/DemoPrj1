package stepDef;

import utilities.FunctionUtilities;

import org.openqa.selenium.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.Compose;
import pageObjects.LaunchBrowser;
import pageObjects.LoginScreen;

public class StepDefinitons extends FunctionUtilities{
	
	WebDriver driver = FunctionUtilities.getDriver();
	LoginScreen loginScreen = new LoginScreen(driver);
	Compose compose = new Compose(driver);
	
	@Given("^User Launched Browser and Navigated to URL$")
	public void userLaunchedBrowser() {
		LaunchBrowser.launchBrowser();
	}
	
	@Then("^User Login with UserName and Password$")
	public void userLogin() throws Exception {
		loginScreen.Login("testuser9711@gmail.com","test@12345");
	}
	
	@Then("^User Compose Email and Attach Excel file$")
	public void userComposeEmail() throws Exception {
		compose.mailCompose();
	}
}

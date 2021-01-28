package testRunner;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"com.cucumber.listener.ExtentCucumberFormatter:Reports/report.html"},features= {"Features"},
monochrome = true, tags= {"@Compose"},glue= {"stepDef"})

public class TestRunner {
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;
	
	public static WebDriver driver;
	private TestNGCucumberRunner testRunner;
	public ExtentTest log = null;
	
	@BeforeClass
	public void setUP() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		testRunner = new TestNGCucumberRunner(TestRunner.class);
	}
	
	@Test(description = "login", dataProvider = "features")
	public void login(CucumberFeatureWrapper cFeature) {
		testRunner.runCucumber(cFeature.getCucumberFeature());
	}
	
	@DataProvider(name = "features")
	public Object[][] getFeatures() {
		return testRunner.provideFeatures();
	}
	
	@AfterClass
	public void tearDown() {
		testRunner.finish();
		Reporter.loadXMLConfig("./extent-config.xml");
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	}
}

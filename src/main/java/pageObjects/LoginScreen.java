package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;
import com.pageObjects.GmailObjects;
import com.relevantcodes.extentreports.LogStatus;

import utilities.FunctionUtilities;

public class LoginScreen extends FunctionUtilities {
	static String image;
	GmailObjects login = PageFactory.initElements(driver, GmailObjects.class);
	
	public void verifyLogin(String userID, String pass) throws Exception {
		
		login.typeUserName(userID);
		Reporter.addStepLog("Entered Email ID as : "+userID);
		login.clickonUserNextBtn();
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		Thread.sleep(2000);
		login.typePassword(pass);
		Reporter.addStepLog("Entered Password as : "+pass);
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		login.clickonPassNextBtn();
	}
}

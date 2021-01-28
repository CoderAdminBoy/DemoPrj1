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
import com.relevantcodes.extentreports.LogStatus;

import utilities.FunctionUtilities;

public class LoginScreen extends FunctionUtilities {
	WebDriver driver;
	static String image;
	public LoginScreen(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void Login(String userName, String password) throws Exception {
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(userName);
		Reporter.addStepLog("Entered Email ID as : "+userName);
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		driver.findElement(By.xpath("//div[@id='identifierNext']")).click();
		WebElement passwordButton = driver.findElement(By.xpath("//input[@name='password']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(passwordButton));
		passwordButton.sendKeys(password);
		Reporter.addStepLog("Entered Password as : "+password);
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
	}
}

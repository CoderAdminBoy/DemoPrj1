package pageObjects;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.pageObjects.GmailObjects;

import utilities.ConfigProperties;
import utilities.FunctionUtilities;

public class Compose extends FunctionUtilities {
	public static ConfigProperties configProperties = new ConfigProperties();
	static String image;
	GmailObjects login = PageFactory.initElements(driver, GmailObjects.class);
	
	public void mailCompose() throws Exception {

		String toEmailID = "testuser9711@gmail.com";
		String subjectLine = "To Automate Compose functionality";
		String body = "Hello, Please find attached excel sheet for your reference.";
		Reporter.addStepLog("Subject : "+subjectLine);

		Thread.sleep(10000);
		login.clickonCompose();
		Thread.sleep(2000);
		login.clickonPopOutButton();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		login.typeToEmail(toEmailID);
		Reporter.addStepLog("Receiptent Email : "+toEmailID);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		login.subjectAdd(subjectLine);
		Reporter.addStepLog("Subject : "+subjectLine);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		login.textbox(body);
		Reporter.addStepLog("Mail Body : "+body);
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		
		login.attachButton();
		
		String browser = configProperties.getDriver();
		if(browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Opera") || browser.equalsIgnoreCase("edge")) {
			Thread.sleep(2000);
			Reporter.addStepLog("Clicked on Attach File button and upload file : Gmail_Test_Cases.xlsx");
			File file = new File(System.getProperty("user.dir")+"/Doc/"+"/Gmail_Test_Cases.xlsx");
			String command = System.getProperty("user.dir")+"/Doc/AutoIt/"+"\\Attach.exe "+file;
			Thread.sleep(2000);
			Runtime.getRuntime().exec(command);
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait = new WebDriverWait(driver,200);
			wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[text()='Gmail_Test_Cases.xlsx']")));
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(2000);
			Reporter.addStepLog("Clicked on Attach File button and upload file : Gmail_Test_Cases.xlsx");
			File file = new File(System.getProperty("user.dir")+"/Doc/"+"/Gmail_Test_Cases.xlsx");
			String command = System.getProperty("user.dir")+"/Doc/AutoIt/"+"\\Attach_Firefox.exe "+file;
			Thread.sleep(2000);
			Runtime.getRuntime().exec(command);
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait = new WebDriverWait(driver,200);
			wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[text()='Gmail_Test_Cases.xlsx']")));
		}
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		login.sendButton();
		Reporter.addStepLog("Clicked on Send button");
		Thread.sleep(4000);
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
	}


}

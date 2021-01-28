package pageObjects;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import utilities.ConfigProperties;
import utilities.FunctionUtilities;

public class Compose {
	WebDriver driver;
	public static ConfigProperties configProperties = new ConfigProperties();
	static String image;
	public Compose(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void mailCompose() throws Exception {
		
		String toEmailID = "testuser9711@gmail.com";
		
		String subjectLine = "To Automate Compose functionality";
		Reporter.addStepLog("Subject : "+subjectLine);

		Thread.sleep(10000);
		driver.findElement(By.xpath("//div[text()='Compose']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@aria-label='Full screen (Shift for pop-out)']")).click();
		WebElement toEmail = driver.findElement(By.xpath("(//span[text()='To']/following::textarea)[1]"));
		
		WebElement subject = driver.findElement(By.xpath("//input[@placeholder='Subject']"));
	
		WebElement body = driver.findElement(By.xpath("//div[@role='textbox']"));
		WebElement btnSend = driver.findElement(By.xpath("//div[text()='Send']"));
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(toEmail));
		toEmail.sendKeys(toEmailID);
		Reporter.addStepLog("Receiptent Email : "+toEmailID);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(subject));
		subject.sendKeys(subjectLine);
		Reporter.addStepLog("Subject : "+subjectLine);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(body));
		body.sendKeys("Hello, Please find attached excel sheet for your reference.");
		Reporter.addStepLog("Mail Body : Hello, Please find attached excel sheet for your reference.");
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		
		driver.findElement(By.xpath("//*[@aria-label='Attach files']")).click();
		
		String browser = configProperties.getDriver();
		if(browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Opera") || browser.equalsIgnoreCase("edge")) {
			Thread.sleep(2000);
			Reporter.addStepLog("Clicked on Attach File button and upload file : Gmail_Test_Cases.xlsx");
			File file = new File(System.getProperty("user.dir")+"/Doc/"+"/Gmail_Test_Cases.xlsx");
			String command = System.getProperty("user.dir")+"/Doc/AutoIt/"+"\\Attach.exe "+file;
			Thread.sleep(2000);
			Runtime.getRuntime().exec(command);
			Thread.sleep(4000);
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
			wait = new WebDriverWait(driver,200);
			wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[text()='Gmail_Test_Cases.xlsx']")));
		}
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
		btnSend.click();
		Reporter.addStepLog("Clicked on Send button");
		Thread.sleep(4000);
		image=FunctionUtilities.Screenshot(driver,System.currentTimeMillis());
		Reporter.addScreenCaptureFromPath(image);
	}


}

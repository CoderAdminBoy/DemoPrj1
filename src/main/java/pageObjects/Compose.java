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

public class Compose {
	WebDriver driver;
	public static ConfigProperties configProperties = new ConfigProperties();
	
	public Compose(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void mailCompose() throws Exception {
		
		String toEmailID = "testuser9711@gmail.com";
		String subjectLine = "To Automate Compose functionality";

		Thread.sleep(10000);
		driver.findElement(By.xpath("//div[text()='Compose']")).click();
		Thread.sleep(2000);
		WebElement toEmail = driver.findElement(By.xpath("(//span[text()='To']/following::textarea)[1]"));
		WebElement subject = driver.findElement(By.xpath("//input[@placeholder='Subject']"));
		WebElement body = driver.findElement(By.xpath("//div[@role='textbox']"));
		WebElement btnSend = driver.findElement(By.xpath("//div[text()='Send']"));
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(toEmail));
		toEmail.sendKeys(toEmailID);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(subject));
		subject.sendKeys(subjectLine);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(body));
		body.sendKeys("Hello, Please find attached excel sheet for your reference.");
		
		driver.findElement(By.xpath("//*[@aria-label='Attach files']")).click();
		
		String browser = configProperties.getDriver();
		if(browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Opera") || browser.equalsIgnoreCase("edge")) {
			Thread.sleep(2000);
			Reporter.addStepLog("Clicked on Attach File Icon");
			File file = new File(System.getProperty("user.dir")+"/Doc/"+"/Gmail_Test_Cases.xlsx");
			String command = System.getProperty("user.dir")+"/Doc/AutoIt/"+"\\Attach.exe "+file;
			Thread.sleep(2000);
			Runtime.getRuntime().exec(command);
			wait = new WebDriverWait(driver,3);
			wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[text()='Gmail_Test_Cases.xlsx']")));
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(2000);
			Reporter.addStepLog("Clicked on Attach File Icon");
			File file = new File(System.getProperty("user.dir")+"/Doc/"+"/Gmail_Test_Cases.xlsx");
			String command = System.getProperty("user.dir")+"/Doc/AutoIt/"+"\\Attach_Firefox.exe "+file;
			Thread.sleep(2000);
			Runtime.getRuntime().exec(command);
			wait = new WebDriverWait(driver,3);
			wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[text()='Gmail_Test_Cases.xlsx']")));
		}
		
		btnSend.click();
		
		Thread.sleep(2000);
	}


}

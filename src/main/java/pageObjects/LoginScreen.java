package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import utilities.FunctionUtilities;

public class LoginScreen extends FunctionUtilities {
	WebDriver driver;

	public LoginScreen(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Login(String userName, String password) throws Exception {
		
		//Send email address
		 driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(userName);
		 Reporter.addStepLog("Entered Email ID as : "+userName);
		 driver.findElement(By.xpath("//div[@id='identifierNext']")).click();
		 
		 //send password
		 WebElement passwordButton = driver.findElement(By.xpath("//input[@name='password']"));
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.elementToBeClickable(passwordButton));
		 passwordButton.sendKeys(password);
		 Reporter.addStepLog("Entered Password as : "+password);
		 driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
		 
	}
}

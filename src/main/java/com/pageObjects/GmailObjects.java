package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GmailObjects {
	WebDriver driver;
	
	public GmailObjects(WebDriver driver) {
		this.driver=driver;
	}
	
	//Login Page
	@FindBy(how=How.XPATH, using ="//input[@id='identifierId']") WebElement userName;
	@FindBy(how=How.XPATH, using ="//input[@name='password']") WebElement password;
	@FindBy(how=How.XPATH, using ="//div[@id='identifierNext']") WebElement userBtnNext;	
	@FindBy(how=How.XPATH, using = "//div[@id='passwordNext']") WebElement passBtnNext;
	
	//Compose
	@FindBy(how=How.XPATH, using ="//div[text()='Compose']") WebElement compose;
	@FindBy(how=How.XPATH, using ="//*[@aria-label='Full screen (Shift for pop-out)']") WebElement popOut;
	@FindBy(how=How.XPATH, using ="(//span[text()='To']/following::textarea)[1]") WebElement toEmail;
	@FindBy(how=How.XPATH, using ="//input[@placeholder='Subject']") WebElement subject;
	@FindBy(how=How.XPATH, using ="//div[@role='textbox']") WebElement textBox;
	@FindBy(how=How.XPATH, using ="//div[text()='Send']") WebElement sendBtn;
	@FindBy(how=How.XPATH, using ="//*[@aria-label='Attach files']") WebElement attachFile;
	
	public void typeUserName(String user)
	{
		userName.sendKeys(user);
	}
	
	public void typePassword(String pass)
	{
		password.sendKeys(pass);
	}
	
	public void clickonUserNextBtn()
	{
		userBtnNext.click();
	}
	
	public void clickonPassNextBtn()
	{
		passBtnNext.click();
	}
	
	public void clickonCompose() 
	{
		compose.click();
	}
	
	public void clickonPopOutButton() 
	{
		popOut.click();
	}
	
	public void typeToEmail(String toMail) {
		toEmail.sendKeys(toMail);
	}
	
	public void subjectAdd(String sub)
	{
		subject.sendKeys(sub);
	}
	
	public void textbox(String txt)
	{
		textBox.sendKeys(txt);
	}
	
	public void sendButton()
	{
		sendBtn.click();
	}
	
	public void attachButton()
	{
		attachFile.click();
	}
	
}

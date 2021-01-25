package managers;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {


	public static WebDriver getDriver(WebDriver driver) {
		
		return driver;
	}


	public static void closeDriver(WebDriver driver) {
		driver.close();
		driver.quit();
	}

}
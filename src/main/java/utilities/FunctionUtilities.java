package utilities;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cucumber.listener.Reporter;

public class FunctionUtilities {
	public static ConfigProperties configProperties = new ConfigProperties();
	public static WebDriver driver;
	public static JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public static String navigateToURL() {
		String url = configProperties.getApplicationUrl();
		String browser = configProperties.getDriver();
		String testingType = configProperties.getTestingType();

		try {
			if (testingType.equalsIgnoreCase("mobile")) {
				if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("firefox")
						|| browser.equalsIgnoreCase("Opera") || browser.equalsIgnoreCase("edge")) {
					driver.manage().window().setPosition(new Point(0, 0));
					driver.manage().window().setSize(new Dimension(Integer.parseInt(configProperties.getMobileWidth()),
							Integer.parseInt(configProperties.getMobileHeight())));
					driver.manage().deleteAllCookies();
					driver.manage().timeouts().pageLoadTimeout(350, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				}
			} else {
				System.out.println("Testing type as per configuration: " + testingType);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(350, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			Reporter.addStepLog("Device  " + configProperties.getDeviceName() + " launched successfully");

			driver.get(url);
			
			Reporter.addStepLog("Application URL " + url + " launched successfully");
			return "false";
		} catch (Exception e) {
			System.out.println("Not able to navigate to URL");
			e.printStackTrace();
		}
		return url;
	}
	

	@SuppressWarnings("deprecation")
	public static WebDriver launchDriver() {
		WebDriver getDriver = null;
		String browser = configProperties.getDriver();
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver.exe");
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("default");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setAcceptInsecureCerts(true);
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				@SuppressWarnings("unused")
				GeckoDriverService service = new GeckoDriverService.Builder(firefoxBinary)
						.usingDriverExecutable(new File(System.getProperty("user.dir") + "/geckodriver.exe"))
						.usingAnyFreePort().usingAnyFreePort().build();
				FirefoxOptions options = new FirefoxOptions().setBinary(firefoxBinary).setProfile(myprofile);
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				driver = new FirefoxDriver(capabilities);
				js = (JavascriptExecutor) driver;
				Reporter.addStepLog("Able to launch Firefox browser");
				getDriver = driver;
			} else if (browser.equalsIgnoreCase("Chrome")) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("plugins.always_open_pdf_externally", true);
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory",
						System.getProperty("user.dir") + "\\Downloads\\PDFDownloads");
				capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--test-type");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(options);
				js = (JavascriptExecutor) driver;
				driver.manage().deleteAllCookies();
				Reporter.addStepLog("Able to launch Google Chrome browser");
				getDriver = driver;
			} else if (browser.equalsIgnoreCase("Opera")) {
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Users\\dhananjay\\AppData\\Local\\Programs\\Opera\\68.0.3618.125\\opera.exe");
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "/operadriver.exe");
				driver = new OperaDriver(capabilities);
				js = (JavascriptExecutor) driver;
				Reporter.addStepLog("Able to launch Opera browser");
				getDriver = driver;
			} else if (browser.equalsIgnoreCase("Edge")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				MutableCapabilities sauceOptions = new MutableCapabilities();
				@SuppressWarnings("unused")
				EdgeOptions browserOptions = new EdgeOptions();
				capabilities.setCapability("platformName", "Windows 10");
				capabilities.setCapability("browserVersion", "latest");
				capabilities.setCapability("sauce:options", sauceOptions);
				// driver = new RemoteWebDriver(new URL(sauceURL), browserOptions);
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/msedgedriver.exe");
				driver = new EdgeDriver();
				js = (JavascriptExecutor) driver;
				driver.manage().deleteAllCookies();
				Reporter.addStepLog("Able to launch Edge browser");
				getDriver = driver;
			}
			else {
				getDriver = driver;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not able to Launch Browser");
			return getDriver;
		}
		return getDriver;
	}
	
	public static WebDriver getDriver() {
		WebDriver driver = FunctionUtilities.launchDriver();
		return driver;
	}
}

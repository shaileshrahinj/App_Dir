package pkg;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.channels.ReadPendingException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.util.Properties;
import java.io.InputStream;

public class RootClass {
	private static String driverPathChrome;
	private static String driverPathIE;
	private static String AppDirURL;
	private static WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(RootClass.class);
	
/*	private static String VegaURL;
	static {
		driverPath = getBasePath() + File.separator + "src" + File.separator + "main" + File.separator + "resources"
				+ File.separator + "drivers" + File.separator;
	}
*/

@BeforeSuite(alwaysRun = true)
@Parameters("browser")

//read data from properties file

	public WebDriver setup(String browser) throws Exception {

		Properties prop=new Properties();
		InputStream input =RootClass.class.getClassLoader().getResourceAsStream("data/app.properties");
	    prop.load(input);
		if (browser.equalsIgnoreCase("FF")) {
			LOGGER.info(" USING FF browser --------- ");
			setDriver(new FirefoxDriver());
			// changing as firefox updated to 47
			// System.setProperty("webdriver.gecko.driver", driverPath +
			// "geckodriver.exe");
			// DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			// setDriver(new MarionetteDriver(capabilities));

		} else if (browser.equalsIgnoreCase("Chrome")) {
			LOGGER.info(" USING Chrome browser --------- ");

			System.setProperty("webdriver.chrome.driver",prop.getProperty(driverPathChrome) + "chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();

			options.addArguments("--disable-web-security");

			capabilities.setCapability("chrome.binary", prop.getProperty(driverPathChrome) + "chromedriver.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			setDriver(new ChromeDriver(capabilities));
		}
		if (browser.equalsIgnoreCase("IE")) {

			LOGGER.info(" USING IE browser --------- ");

			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", prop.getProperty(driverPathIE) + "IEDriverServer.exe");
			setDriver(new InternetExplorerDriver(capabilities));
		}

		driver.manage().window().maximize();
		AppDirURL = prop.getProperty("AppDirectURL");
		driver.get(AppDirURL);
		return driver;

//System.out.println(prop.getProperty("AppDirectURL"));
//return null;	    
}

	/*@AfterSuite(alwaysRun = true)
	public void closeServer() {
		LOGGER.info("Stopping Selenium web driver.....");
		driver.close();
	*/

	public static void setDriver(WebDriver _driver) {
		driver = _driver;
	}

	
}

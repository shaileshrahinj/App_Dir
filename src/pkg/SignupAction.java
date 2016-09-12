package pkg;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pkg.PropertiesReader;
import data.Constant;
import data.ExcelReader;

public class SignupAction {
	WebElement container=null;
	WebElement SignupbuttonEle=null;
	WebElement container2=null;
	private static String driverPath;
	private static String AppDirURL;
	private static WebDriver driver;
	public static String browser;
	private static final Logger LOGGER = Logger.getLogger(SignupAction.class);
	public static Properties testProperties=new Properties();
	public static void loadProperties() throws IOException{
		InputStream fis =PropertiesReader.class.getClassLoader().getResourceAsStream("pkg/app.properties");
		testProperties.load(fis);
	}
	@BeforeTest(alwaysRun = true)
	@Parameters("browser")
	public void setup(String browser) throws IOException {
		if (browser.equalsIgnoreCase("FF")) {
			LOGGER.info(" USING FF browser --------- ");
			driver = new FirefoxDriver();
	
		} else if (browser.equalsIgnoreCase("Chrome")) {
			LOGGER.info(" USING Chrome browser --------- ");
			SignupAction.loadProperties();
			System.setProperty("webdriver.chrome.driver",testProperties.getProperty("driverPath")+ "\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("IE")) {

			LOGGER.info(" USING IE browser --------- ");

			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			SignupAction.loadProperties();
			System.setProperty("webdriver.ie.driver",testProperties.getProperty("driverPath")+ "\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		SignupAction.loadProperties();
		driver.get(testProperties.getProperty("AppDirectURL"));

	}
	
	//****************TC's*****************
	//TC#1 Verify that website "https://www.appdirect.com" is launched successfully.
	@Test(groups={"smoke","regression"},priority=0)

	public void WebsiteOpenedTest() throws IOException{
        LOGGER.info("TC#1 WebsiteOpenedTest");
		SignupAction.loadProperties();
		String pageTitle= driver.findElement(By.xpath(testProperties.getProperty("txtLgUserName"))).getText();
       try
		{
			Assert.assertEquals(pageTitle, "The leading commerce platform for selling cloud services.");
			System.out.println("TC #1 passed: Assertion Passed");

		}
		catch(AssertionError e)
		{
			System.out.println("TC#1 WebsiteOpenedTest is failed");
		}

	}
	
	//TC#2 Verify user is navigated to login page after clicking on 'Login' button.
    @Test(groups={"smoke","regression"},priority=1)
	public void LoginPageopenedTest() throws IOException{ 
		LOGGER.info("TC#2 LoginPageopenedTest");

		boolean existsElement;
		SignupAction.loadProperties();
		WebElement un=driver.findElement(By.xpath(testProperties.getProperty("login_clk")));
		un.click();
		try {/*
			String el =driver.findElement(By.xpath(".//*[@id='login-header']/nav/ul/li/a/span")).getText();
			el.equalsIgnoreCase("AppDirect");
		 */
			WebElement el = (new WebDriverWait(driver,20))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(testProperties.getProperty("heading"))));
			el.getText().equalsIgnoreCase("AppDirect");
            System.out.println("TC#2 Passed: Login page exists");
			existsElement=true;
		} catch (NoSuchElementException e) {
			existsElement=false;
		}
	}

   //TC#3 Verify user is navigated to Signup page after clicking on 'Signup' button from the login page.

	@Test(groups={"smoke","regression"},priority=2)
	public void SignupPageOpenedTest() throws IOException{ 
		LOGGER.info("TC#3 SignupPageOpenedTest");
		boolean existsElement;
		SignupAction.loadProperties();
		WebElement un=driver.findElement(By.xpath(testProperties.getProperty("login_Button")));
		un.click();
		SignupbuttonEle=un;
		try {
			String el =driver.findElement(By.xpath(testProperties.getProperty("SignUP_Button"))).getText();
			el.equalsIgnoreCase("Sign Up");
			LOGGER.info("Signup page opened");
			System.out.println("TC#3 Passed: Signup page opened");
			existsElement=true;
		} catch (NoSuchElementException e) {
			existsElement=false;
		}
	}

	//TC#4 Verify that place holder "email@address.com" is displayed in email field.
	@Test(groups={"regression"},priority=3)
	public void PlaceholderTest() throws Exception{ 

		LOGGER.info("TC#4 PlaceholderTest");
		String pla;
		/*WebElement placeholder1 =driver.findElement(By.xpath(".//*[@id='ida']/fieldset/div[2]/div/input"));
		placeholder1.getAttribute(placeholder);
		 */
		SignupAction.loadProperties();
		WebElement placeholder1= (new WebDriverWait(driver,20))
				.until(ExpectedConditions.presenceOfElementLocated(By.className(testProperties.getProperty("placeholder"))));
		pla=placeholder1.getAttribute("placeholder");

		try
		{	
			Assert.assertEquals(pla,"email@address.com");
			System.out.println("TC#4 PlaceholderTest is Passed: Assertion Passed");

		}
		catch(AssertionError e)
		{
			System.out.println("TC#4 PlaceholderTest is failed");
		}
		container=placeholder1;
	}


	//TC#5 Verify that message for activation link is displayed once user enters correct email ID.

	@Test(groups={"smoke","regression"},priority=4)
	public void ActivationLinkMessageTest() throws Exception { 

		LOGGER.info("TC#5 ActivationLinkMessageTest");
		//*****************Read the data from Excel************************
		ExcelReader.setExcelFile(Constant.Path_TestData,"Sheet1");
		String emailId = ExcelReader.getCellData(1,0);
		System.out.println(emailId);
		container.sendKeys(emailId);
		SignupAction.loadProperties();
		driver.findElement(By.xpath(testProperties.getProperty("signup"))).click();

		//******Activation message verification**********

		WebElement placeholder1= (new WebDriverWait(driver,20))
				.until(ExpectedConditions.presenceOfElementLocated(By.className(testProperties.getProperty("activationmsg"))));

		Thread.sleep(2000);
		/*			
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			String activationmessage= (String)js.executeScript(driver.findElement(By.xpath(".//*[@id='id25']/div/section/div/p[1]")).getText());
		 */			
		activationmessage();
		System.out.println("TC#5 Passed");

	}

	//TC#6 Verify that user should not be able to proceed ahead if  wrongly formatted email ID is entered.

	@Test(groups={"regression"},priority=5)
	public void InvalidEmailidTest() throws Exception { 
		LOGGER.info("TC#6 InvalidEmailidTest");
		//Read the email ID's from excel sheet
		driver.navigate().back();
		SignupAction.loadProperties();
		WebElement un=driver.findElement(By.xpath(testProperties.getProperty("nav_back")));
		un.click();
		ExcelReader.setExcelFile(Constant.Path_TestData,"Sheet2");
		String emailId = ExcelReader.getCellData(1,0);
		System.out.println(emailId);

		try{
			container.sendKeys(emailId);

		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			driver.navigate().refresh();
			WebElement placeholder2= (new WebDriverWait(driver,20))
					.until(ExpectedConditions.presenceOfElementLocated(By.className(testProperties.getProperty("activationmsg"))));
			placeholder2.sendKeys(emailId);
			container2=	placeholder2;   
			//container.sendKeys(emailId);
		}

		driver.findElement(By.name("userSignupButton")).click();
		if(container2.isDisplayed())
		{
			LOGGER.info("InvalidEmailid entered");
			System.out.println("TC#6 Passed:Please enter correct email ID the email id you have entered is not correct");
		}
		else
		{
			activationmessage();
		}
		
		driver.quit();
	}

     public  void activationmessage() {
		try
		{
			String activationmessage=driver.findElement(By.xpath(".//*[@id='id10']/div/section/div/p[2]")).getText();
			System.out.println(activationmessage);
			Assert.assertEquals(activationmessage,"Please check your inbox and click the link to activate your account.");

		}
		catch(Exception e)
		{
			System.out.println("This email address has already been registered in our system. Please register with a new email address.");
		}


	}
}

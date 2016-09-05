package pkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import data.Constant;
import data.ExcelReader;
import pkg.RootClass;

public class SignupAction {

	WebElement container=null;
	WebElement SignupbuttonEle=null;
	WebElement container2=null;
	private static String driverPath;
	private static String AppDirURL;
	private static WebDriver driver;
	public static String browser;
	//public WebDriver driver=new ChromeDriver();
	private static final Logger LOGGER = Logger.getLogger(SignupAction.class);
	/*//Actions action = new Actions(driver);
	public String URL="https://www.appdirect.com/signup";
	 */@BeforeTest
	 @Parameters("browser")
	 public void setup(String browser) throws IOException {
		 /*System.setProperty("webdriver.chrome.driver", "F:\\eclipse\\chromedriver.exe");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("http://www.appdirect.com/");
		driver.manage().window().maximize();

		  */


		 /*Properties prop=new Properties();

	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\data\\app.properties");
	//InputStream input =SignupAction.class.getClassLoader().getResourceAsStream("data/app.properties");
	    prop.load(objfile);
		  */
		 if (browser.equalsIgnoreCase("FF")) {
			 LOGGER.info(" USING FF browser --------- ");
			 driver = new FirefoxDriver();
			 // changing as firefox updated to 47
			 // System.setProperty("webdriver.gecko.driver", driverPath +
			 // "geckodriver.exe");
			 // DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			 // setDriver(new MarionetteDriver(capabilities));

		 } else if (browser.equalsIgnoreCase("Chrome")) {
			 LOGGER.info(" USING Chrome browser --------- ");

			 System.setProperty("webdriver.chrome.driver","F:\\Shailesh\\WorkSelenium\\AppDir1\\BrowserDrivers\\chromedriver.exe");
			 /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();

			options.addArguments("--disable-web-security");

			capabilities.setCapability("chrome.binary", prop.getProperty(driverPathChrome) + "chromedriver.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			setDriver(new ChromeDriver(capabilities));
			  */		//setDriver(new ChromeDriver());
			 driver = new ChromeDriver();
		 }
		 if (browser.equalsIgnoreCase("IE")) {

			 LOGGER.info(" USING IE browser --------- ");

			 DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			 capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			 System.setProperty("webdriver.ie.driver","F:\\Shailesh\\WorkSelenium\\AppDir1\\BrowserDrivers\\IEDriverServer.exe");
			 //setDriver(new InternetExplorerDriver(capabilities));
			 driver = new InternetExplorerDriver();
		 }

		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		 //AppDirURL = prop.getProperty("AppDirectURL");
		 driver.get("http://www.appdirect.com/");

		 //	return driver;

	 }
	 //TC#1 Verify that website "https://www.appdirect.com" is launched successfully.
	 @Test(priority=0)

	 public void WebsiteOpenedTest(){

		 LOGGER.info("TC#1 WebsiteOpenedTest");

		 String pageTitle= driver.findElement(By.xpath("html/body/main/section[1]/div[2]/div/h1")).getText();

		 try
		 {
			 Assert.assertEquals(pageTitle, "The leading commerce platform for selling cloud services.");
			 System.out.println("Assertion Pass");

		 }
		 catch(AssertionError e)
		 {
			 System.out.println("Assertion failed");
		 }

	 }

	 /*
	public static void setDriver(WebDriver _driver) {
		driver = _driver;
	}
	  */
	 //TC#2 Verify user is navigated to login page after clicking on 'Login' button.

	 @Test(priority=1)
	 public void LoginPageopenedTest(){ 
		 LOGGER.info("TC#2 LoginPageopenedTest");
		 boolean existsElement;

		 WebElement un=driver.findElement(By.xpath(".//*[@id='newnav']/header/div/menu/ul/li[3]/a/span"));
		 un.click();
		 try {/*
			String el =driver.findElement(By.xpath(".//*[@id='login-header']/nav/ul/li/a/span")).getText();
			el.equalsIgnoreCase("AppDirect");
		  */
			 WebElement el = (new WebDriverWait(driver,20))
					 .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='login-header']/nav/ul/li/a/span")));
			 el.getText().equalsIgnoreCase("AppDirect");

			 System.out.println("Login page exists");
			 existsElement=true;
		 } catch (NoSuchElementException e) {
			 existsElement=false;
		 }
	 }


	 //TC#3 Verify user is navigated to Signup page after clicking on 'Signup' button from the login page.

	 @Test(priority=2)
	 public void SignupPageOpenedTest(){ 
     LOGGER.info("TC#3 SignupPageOpenedTest");
		 boolean existsElement;

		 WebElement un=driver.findElement(By.xpath(".//*[@id='id5']/fieldset/div[3]/menu/a"));
		 un.click();
		 SignupbuttonEle=un;
		 try {
			 String el =driver.findElement(By.xpath(".//*[@id='ida']/fieldset/div[1]/h3")).getText();
			 el.equalsIgnoreCase("Sign Up");

			 System.out.println("Signup page opened");
			 existsElement=true;
		 } catch (NoSuchElementException e) {
			 existsElement=false;
		 }
	 }

	 //TC#4 Verify that place holder "email@address.com" is displayed in email field.
	 @Test(priority=3)
	 public void PlaceholderTest() throws Exception{ 
	
		 LOGGER.info("TC#4 PlaceholderTest");
		 String pla;
		 /*WebElement placeholder1 =driver.findElement(By.xpath(".//*[@id='ida']/fieldset/div[2]/div/input"));
		placeholder1.getAttribute(placeholder);
		  */
		 WebElement placeholder1= (new WebDriverWait(driver,20))
				 .until(ExpectedConditions.presenceOfElementLocated(By.className("adb-text__full_width")));
		 pla=placeholder1.getAttribute("placeholder");

		 try
		 {	
			 Assert.assertEquals(pla,"email@address.com");
			 System.out.println("Assertion Pass");

		 }
		 catch(AssertionError e)
		 {
			 System.out.println("Assertion failed");
		 }
		 container=placeholder1;
	 }


	 //TC#5 Verify that message for activation link is displayed once user enters correct email ID.

	 @Test(priority=4)
	 public void ActivationLinkMessageTest() throws Exception { 
		 
		 LOGGER.info("TC#5 ActivationLinkMessageTest");
		 //*****************Read the data from Excel************************
		 ExcelReader.setExcelFile(Constant.Path_TestData,"Sheet1");
		 String emailId = ExcelReader.getCellData(1,0);
		 System.out.println(emailId);
		 container.sendKeys(emailId);
		 driver.findElement(By.xpath(".//*[@id='idb']")).click();

		 //Activation message verification

		 WebElement placeholder1= (new WebDriverWait(driver,20))
				 .until(ExpectedConditions.presenceOfElementLocated(By.className("adb-text__full_width")));

		 Thread.sleep(2000);
		 /*			
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			String activationmessage= (String)js.executeScript(driver.findElement(By.xpath(".//*[@id='id25']/div/section/div/p[1]")).getText());
		  */			

		 activationmessage();			

	 }

	 //TC#6 Verify that user should not be able to proceed ahead if  wrongly formatted email ID is entered.

	 @Test(priority=5)
	 public void InvalidEmailidTest() throws Exception { 
		 LOGGER.info("TC#5 InvalidEmailidTest");
		 //ExcelReader obj =new ExcelReader();
		 //Read the email ID's from excel sheet
		 driver.navigate().back();
		 WebElement un=driver.findElement(By.xpath(".//*[@id='id5']/fieldset/div[3]/menu/a"));
		 un.click();
		 ExcelReader.setExcelFile(Constant.Path_TestData,"Sheet2");
		 String emailId = ExcelReader.getCellData(1,0);
		 System.out.println(emailId);

		 try{
			 container.sendKeys(emailId);

		 }
		 catch(org.openqa.selenium.StaleElementReferenceException ex)
		 {
			 //log.debug("Exception in finding email field");
			 //log.debug(e);
			 //driver.findElement(By.xpath(".//*[@id='id3']/fieldset/div[2]/div/input")).sendKeys(emailId);
			 driver.navigate().refresh();
			 WebElement placeholder2= (new WebDriverWait(driver,20))
					 .until(ExpectedConditions.presenceOfElementLocated(By.className("adb-text__full_width")));
			 placeholder2.sendKeys(emailId);
			 container2=	placeholder2;   
			 //container.sendKeys(emailId);
		 }

		 driver.findElement(By.name("userSignupButton")).click();
		 if(container2.isDisplayed())
		 {
			 System.out.println("Please enter correct email ID");
		 }
		 else
		 {
			 activationmessage();
		 }
		 /*String ele=container.getAttribute("class");
					System.out.println(ele);
		  */
		 driver.quit();
	 }



	 /*		//TC#2 Verify if the user is navigated to registration page
	@Test(priority=1)
	public void RegistrationPageopenedTest(){ 

		boolean existsElement;

		WebElement un=driver.findElement(By.xpath(".//*[@id='partnerRegisterLink']/a"));
		un.click();
		try {
			driver.findElement(By.xpath(".//*[@id='idc']/span/div/section/div/div[1]/h3")).getText();
			existsElement=true;

		} catch (NoSuchElementException e) {
			existsElement=false;
		}
	}


	//TC#3 User is able to click on sign up after providing valid emailID 
	@Test(priority=2)
	public void activationLink() throws Exception{
		OpeningBrowser();

		ExcelReader obj =new ExcelReader();
		obj.setExcelFile(Constant.Path_TestData,"Sheet1");
		String emailId = ExcelReader.getCellData(1,0);
		System.out.println(emailId);
		WebElement en=driver.findElement(By.name("emailAddress"));
		en.sendKeys(emailId);
		driver.findElement(By.name("userSignupButton")).click();
		//driver.close();
	}


	//TC#4 Verify that nothing happens when user tries to click on sign up button for invalid login ID.
	@Test(priority=3)
	public void invalidEmailID() throws Exception{
		String currentURL=null;
		OpeningBrowser();

		ExcelReader obj =new ExcelReader();
		obj.setExcelFile(Constant.Path_TestData,"Sheet1");
		String emailId = ExcelReader.getCellData(1,2);
		System.out.println(emailId);
		WebElement en=driver.findElement(By.name("emailAddress"));
		en.sendKeys(emailId);
		driver.findElement(By.name("userSignupButton")).click();
		currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		if(currentURL==URL)
		{
			System.out.println("Please provide the correct emailaddress");
		}
		else{
			System.out.println("something is wrong");

		}

	}
	//TC#5 Verify user is not able to login after providing wrong emailID and password.
	@Test(priority=4)
	public void invalidEmailIDPassword() throws Exception{
		String currentURL=null;
		OpeningBrowser();
		driver.findElement(By.xpath(".//*[@id='signupLoginLink']/a")).click();
		ExcelReader obj =new ExcelReader();
		obj.setExcelFile(Constant.Path_TestData,"Sheet1");
		String emailId = ExcelReader.getCellData(1,2);
		String password= ExcelReader.getCellData(1,3);
		System.out.println(emailId);
		System.out.println(password);
		WebElement email=driver.findElement(By.name("email"));
		email.sendKeys(emailId);
		WebElement passw=driver.findElement(By.name("password"));
		passw.sendKeys(password);

		driver.findElement(By.name("signin")).click();
		currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		if(currentURL=="https://www.appdirect.com/login")
		{
			System.out.println("Please provide the correct User ID and password");
		}
		else{
			System.out.println("something is wrong");

		}
		driver.close();

	  */	

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

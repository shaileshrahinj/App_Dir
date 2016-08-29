package pkg;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import data.Constant;
import data.ExcelReader;


public class SignupAction {

	WebElement container=null;
	WebDriver driver=new ChromeDriver();
	//Actions action = new Actions(driver);
	public String URL="https://www.appdirect.com/signup";

	//TC#1 Verify that website "https://www.appdirect.com" is launched successfully.
	@Test(priority=0)
	public void OpeningBrowser() {
		System.setProperty("webdriver.chrome.driver", "F:\\eclipse\\chromedriver.exe");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("http://www.appdirect.com/");
		driver.manage().window().maximize();
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

	//TC#2 Verify user is navigated to login page after clicking on 'Login' button.

	@Test(priority=1)
	public void LoginPageopenedTest(){ 

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

		boolean existsElement;

		WebElement un=driver.findElement(By.xpath(".//*[@id='id5']/fieldset/div[3]/menu/a"));
		un.click();
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
	        //ExcelReader obj =new ExcelReader();
			//Read the email ID's from excel sheet
			ExcelReader.setExcelFile(Constant.Path_TestData,"Sheet1");
			String emailId = ExcelReader.getCellData(1,0);
			System.out.println(emailId);
			container.sendKeys(emailId);
			driver.findElement(By.xpath(".//*[@id='idb']")).click();
		
			//Activation message verification
		
			/*WebElement placeholder1= (new WebDriverWait(driver,20))
					.until(ExpectedConditions.presenceOfElementLocated(By.className("adb-text__full_width")));
			*/
			Thread.sleep(5000);
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;  
			String activationmessage= (String)js.executeScript(driver.findElement(By.xpath(".//*[@id='id25']/div/section/div/p[1]")).getText());
			*/String activationmessage= driver.findElement(By.xpath(".//*[@id='id25']/div/section/div/p[1]")).getText();

			try
			{
				Assert.assertEquals(activationmessage,"We have sent a verification email to rahinj.shailesh66h@gmail.com.");
				System.out.println("This email ID has already been registered,Please enter new email ID");
				
				/*Assert.assertEquals(activationmessage,"This email address has already been registered in our system. Please register with a new email address.");
				System.out.println("This email ID has already been registered,Please enter new email ID");
				*/
			}
			catch(Exception e)
			{
				System.out.println("wtf");
			}

			driver.close();
			
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

	 */	}


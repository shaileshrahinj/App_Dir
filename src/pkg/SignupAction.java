package pkg;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import data.Constant;
import data.ExcelReader;




public class SignupAction {
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
			 Assert.assertEquals(pageTitle, "The leading commerce platform for selling cloud services.h");
			 System.out.println("Assertion Pass");
			 
		 }
		 catch(AssertionError e)
		 {
			 System.out.println("Assertion failed");
		 }
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


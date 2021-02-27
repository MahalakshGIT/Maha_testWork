package base;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends AbstractTestNGCucumberTests{
	
	//Common Methods and Variables 
	//==============================
	
	public static RemoteWebDriver driver;
	//public  RemoteWebDriver driver; //Removed static to support Parallel execution.
	//Static :1.The @Test passes the session id created in @BeforeMethod to d Constructor of Pages class.
	//        2.for Parallel execution, the SAME session id CANNOT be used.So remove STATIC and create each Page-Constuctor(which gets Driver as a Argument from Testcase class)
	
	
	public static WebDriverWait wait;//Null PointerException occurs if its notSTATIC.Static is good for Sequential exectin
	public static JavascriptExecutor js;
	public static Actions a;
	public int int_GrandTotals;

	public String txt_Creation_DateTime; //Report Created Date and Time
	
	@BeforeMethod
	@Parameters({"url","username","password","browser"})
	public void mtdBrowserLaunchAndLogin(String url, String username, String password,String browser)
	{
		
		System.out.println(url+username+password+browser);
		switch (browser.toUpperCase())
		{
			case "FIREFOX":		
				System.out.println("inside Firefox");
			
				WebDriverManager.firefoxdriver().setup();		
				FirefoxOptions ffo = new FirefoxOptions();
				ffo.addArguments("--disable-notifications");
				driver = new FirefoxDriver(ffo);
				break;
				
			case "INTERNETEXPLORER":
				System.out.println("inside IE");
				WebDriverManager.iedriver().setup();
				InternetExplorerOptions ieo = new InternetExplorerOptions();
	
				driver = new InternetExplorerDriver(ieo);
				break;

			default: 
			WebDriverManager.chromedriver().setup();	
			System.out.println("inside chrome");
			ChromeOptions ch = new ChromeOptions();
			ch.addArguments("--disable-notifications");
			driver = new ChromeDriver(ch);
			
			
			break;
		}
	
		driver.get(url);
		try { Thread.sleep(1000);}
		catch (InterruptedException e) {e.printStackTrace();}		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);//implictWait-Is for ur DRIVER, the entire time of execution
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	
		js = (JavascriptExecutor)driver;
		a = new Actions(driver);
		
	}		
	
	
	@AfterMethod
	public void closeApp() {
		//driver.close();

	}

	
	
}

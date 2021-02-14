package base;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ReadExcel;

public class ProjectSpecificMethods {

	//Common Methods and Variables 
//	public static RemoteWebDriver driver;
	public  RemoteWebDriver driver; //Removed static to support Parallel execution.
	//Static :1.The @Test passes the session id created in @BeforeMethod to d Constructor of Pages class.
	//        2.for Parallel execution, the SAME session id CANNOT be used.So remove STATIC and create each Page-Constuctor(which gets Driver as a Argument from Testcase class)
	
	public static String excelFileName;
	public static WebDriverWait wait;//Null PointerException occurs if its notSTATIC.Static is good for Sequential exectin
	public static JavascriptExecutor js;
	public static Actions a;
	public static boolean edit_case_found = false;//Global Variable-STATIC as to hold the boolean value across TCEdit case-B4 Edit operation begin, Check whether Case no found in search.
	//public String contact_name="Naveen Elumalai";//CreateCase_without_MandatoryFields
	public static boolean flag_DeleteCase_found=false; ////Global Variable-STATIC as to hold the boolean value across TC_delecte_case
	public static boolean flag_DeleteAction_Clicked = false;
	
	@BeforeMethod
	public void startApp() {
		WebDriverManager.chromedriver().setup();
	
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		driver = new ChromeDriver(op);		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();		
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		js =(JavascriptExecutor) driver;
		a = new Actions(driver);
		
	}
	
	@AfterMethod
	public void closeApp() {
		driver.close();

	}
	
	@DataProvider(name="fetchData")
	public String[][] sendData() throws IOException {
		
		return ReadExcel.excelData(excelFileName);

	}
	
	 //Reusable Utilities
	  //===================
	  //All selenium Actions-click,sendKeys,getText  //Get arguments-webElement 
	   //Reusable Utilities->written in seperate class as Utilities
	  //methods needs to b called in PageClasses.//so no need to type dri.findelement....
	  //1.have appropriate Wait(WebDriver Wait) for each selenium action.
	  //2.Action To be Performed->Click/sendKeys/Clear/get/getText/select......ADD MORE ACCORDINGLY IN FUTURE 
	  //3.Use try-catch for these Actions
	  //4.Catch-All possible exceptions
	  //5.In Catch-Handle the Exception.whatever Selenium DoesNot do, Handle that via Actions class/JS Exec/ROBOT
	
	//To do-->Action class, VERIFICATION METHODS

	//driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
	
	  public void elementClick(WebElement element) throws InterruptedException
	  {
		  
		  
		try {
			  Thread.sleep(1000); //Is this appropriate???
			  wait.until(ExpectedConditions.elementToBeClickable(element));//Take appropriate method for ur Selenium-Action
			  //Clickable is fine only if the Element present in DOM
			  element.click();	
			  
			  System.out.println("Element Clicked Successfully");
			  //LOG or sysout
		
		   //catch(JavascriptException: javascript error: Cannot read property 'defaultView' of undefined)
		
		}
		catch (NoSuchElementException e) {		
			e.printStackTrace();//Can i use Js click here?
			System.out.println(element + "Element Not Found!");		
		}
		catch (InvalidSelectorException e) {
			e.printStackTrace();
			System.out.println(element+" Invalid Selector: Check locator");
		}
		catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println(element+" :Element Not Visible");
		} 
		catch(StaleElementReferenceException e)
		{
			//1.INCREASE TIME BEFORE LOCATING THE ELEMENT.
			Thread.sleep(8000);
			e.printStackTrace();
			System.out.println(element +" :StaleElementReferenceException found");
		}
		catch(JavascriptException e) {
			e.printStackTrace();
		//	public static JavascriptExecutor js;
			System.out.println("Handle->JavaScript Exception on "+ element);
			js.executeScript("arguments[0].click();", element);
			System.out.println("Element Clicked successfully by JS(reusable mtd)");
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
			System.out.println(element+" IllegalArgumentException found");
		}
		catch (ElementClickInterceptedException e) {
			e.printStackTrace(); 
			//JavascriptExecutor js;
			//js.executeScript("arguments[0].scrollIntoView();", element);
			//js.executeScript("arguments[0].click();", element);		
			a.moveToElement(element).click().build().perform();
			System.out.println(element+" ElementClickInterceptedException found -> Click done by Actions class");
			
			
		} 
		catch(WebDriverException e) //Super class Exception. So Give in Bottom
		{
			e.printStackTrace();
			System.out.println("WebDriver Exception, Cannot Proceed Further");
		}
		catch(Exception e) //Super Most class. So Give in Bottom
		{
			e.printStackTrace();
			System.out.println(element+" : Some Other Exception occured");
		}
		 
	  }
	   
	  public void enterData(WebElement element, String data) {
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(data);
			System.out.println("data entered Successfully");			
		}
		catch (NoSuchElementException e) {		
			e.printStackTrace();
			System.out.println(element +" Element Not Found!");		
		}	
		catch (InvalidSelectorException e) {
			e.printStackTrace();
			System.out.println(element +" Invalid Selector, Check locator");
		}
		catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println(element +" Element Not Visible");
	    }
		catch (StaleElementReferenceException e) {
			e.printStackTrace();
			System.out.println(element +" Stale Element Reference Exception found");
		}
		catch (ElementClickInterceptedException e) {
			e.printStackTrace();			
			a.moveToElement(element).click();			
		} 
		catch (WebDriverException e) {//Super class 
			e.printStackTrace();
			System.out.println("WebDriver Exception occured.Cannot Proceed Further");
		}
		catch(Exception e)//Super class .So Give at the Bottom
		{
			e.printStackTrace();
			System.out.println(element +": Some other Exception found");
		}
	  }
	  
	  public void clearData(WebElement element) {
		  try {
			  
			  wait.until(ExpectedConditions.elementToBeClickable(element));
			  element.clear();
			  System.out.println("Data Cleared Successfully");
			  //LOG			
		     }
		  	catch (NoSuchElementException e) {		
				e.printStackTrace();
				System.out.println(element+" Element Not Found!");		
			 }			  
			catch (InvalidSelectorException e) {
				e.printStackTrace();
				System.out.println(element+" Invalid Selector, Check locator");
			 }
			catch (ElementNotVisibleException e) {
				e.printStackTrace();
				System.out.println(element+" Element Not Visible");	  
			 }
		  	catch (StaleElementReferenceException e) {
		  		e.printStackTrace();
				System.out.println(element+ " Stale Element Reference Exception found");			
		  	 }		  
		    catch (WebDriverException e) {//Super class 
				e.printStackTrace();
				System.out.println("WebDriver Exception occured.Cannot Proceed Further");
			}		  
		    catch(Exception e)//Super class .So Give at the Bottom
			{
				e.printStackTrace();
				System.out.println(element +": Some other Exception found");
			}
	  }
	  
	  public String getData(WebElement element) {
		  String text1="";
		  try {
			  wait.until(ExpectedConditions.visibilityOf(element));
			  text1 = element.getText();
			  element.getText();
			  System.out.println("Data Retrived From UI Successfully");
			  //return text1;
		  }catch (NoSuchElementException e) {		
				e.printStackTrace();
				System.out.println(element+" Element Not Found!");		
		  }catch (InvalidSelectorException e) {
				e.printStackTrace();
				System.out.println(element+" Invalid Selector, Check locator");
		  }catch (ElementNotVisibleException e) {
				e.printStackTrace();
				System.out.println(element+" Element Not Visible");
		  }catch (StaleElementReferenceException e) {
		  		e.printStackTrace();
				System.out.println(element+" Stale Element Reference Exception found");			
		  }catch (WebDriverException e) { //Super class 
				e.printStackTrace();
				System.out.println("WebDriver Exception occured.Cannot Proceed Further");
		  }catch(Exception e)//Super class .So Give at the Bottom
		  {
			    e.printStackTrace();
				System.out.println(element +": Some other Exception found");
		  }
		return text1;
	}

	 public void scrollIntoView(WebElement element ) { //gET THE APPROPRIATE EXCEPTION
		
		try {
			
			js.executeScript("argurmnts[0].scrollIntoView();", element);//Correct?????
		} 
		catch(ElementClickInterceptedException e) {
			
			js.executeScript("argurmnts[0].scrollIntoView()", element);
			System.out.println(e+ "ElementClickInterceptedexception");
		}		
		catch (WebDriverException e) { //Super class 
			e.printStackTrace();
			System.out.println("WebDriver Exception occured.Cannot Proceed Further");
		}	
		catch (Exception e) //Super class .So Give at the Bottom
		{
			
			e.printStackTrace();
			System.out.println(element +": Some other Exception found");
		}
	} 
	
	 
	public void moveToElement(WebElement element) 
	{
		a.moveToElement(element).click().build();
			
	}
	
	//HOW TO WRITE REUSABLE MTD TO PERFORM KEYS.TAB IN ACTION?????????
	public void moveToElement(WebElement element,String text) 
	{
	
		a.moveToElement(element).click().sendKeys(text).sendKeys(Keys.ENTER).build().perform();
		System.out.println(text+" Entered in inputBox Successfully");
		
	}
}

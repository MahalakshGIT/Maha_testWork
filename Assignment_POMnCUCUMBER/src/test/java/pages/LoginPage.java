package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import base.BaseClass;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;


public class LoginPage extends BaseClass {
			
		//Action+FieldName-->This is How Methods are written.For each of ACTIONS in the PAGE
	
	    //Constructor	
		/*
		 * public LoginPage(RemoteWebDriver driver) { this.driver=driver; }
		 */
		//@Parameters({"url","username","password","browser"})
	    @Given("Enter Username")
	//	public LoginPage enterUsername(String url, String username, String password,String browser) {
		public LoginPage enterUsername() {	
			driver.findElement(By.id("username")).sendKeys("bowyakarthikeyan@testleaf.com");	
			//driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");	
			return this;
		}
		
	//	@Parameters({"url","username","password","browser"})
	    @And("Enter password")
	//	public LoginPage enterPassword(String url, String username, String password,String browser) {
	    public LoginPage enterPassword() {	
			driver.findElement(By.id("password")).sendKeys("India@123");		
			//driver.findElement(By.id("password")).sendKeys("Bootcamp@123");	
			return this;
		}
	
		@When("Click Login Button")
		public OneSetUpHomePage clickLoginButton() throws Exception {
			
			driver.findElement(By.id("Login")).click();				
			return new OneSetUpHomePage();		
		}
	
}

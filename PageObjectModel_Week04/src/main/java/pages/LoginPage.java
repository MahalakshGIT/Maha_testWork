package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.ProjectSpecificMethods;

public class LoginPage extends ProjectSpecificMethods {
			
		//Action+FieldName-->This is How Methods are written.For each of ACTIONS in the PAGE
	
	    //Constructor	
	    public LoginPage(RemoteWebDriver driver)
		{
			this.driver=driver;
		}
		
		public LoginPage enterUsername() {
			//driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");	
			//using Reusable Utility methods
			WebElement element = driver.findElement(By.id("username"));			
			String str_username="cypress@testleaf.com";
			enterData(element, str_username);
			
			return this;
		}
		
		public LoginPage enterPassword() {
			//driver.findElement(By.id("password")).sendKeys("Bootcamp@123");	
			
			WebElement element = driver.findElement(By.id("password"));
			String str_password="Bootcamp@123";
			enterData(element, str_password);
			
			return this;
		}
		
		public OneSetUpHomePage clickLoginButton() throws Exception {
			//driver.findElement(By.id("Login")).click();
			WebElement element = driver.findElement(By.id("Login"));
			elementClick(element);
			
			return new OneSetUpHomePage(driver);		
		}
	
}

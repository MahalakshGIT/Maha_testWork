package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OneSetUpHomePage extends BaseClass{
	
	//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	//JavascriptExecutor js = (JavascriptExecutor) driver;
	//Actions a = new Actions(driver);
	String expected_title_setUpOneHome = "Home | Salesforce";
	
	
	//Constructor	
	/*
	 * public OneSetUpHomePage(RemoteWebDriver driver)//Constructor to receive
	 * session id from @BeforeMethod to Global driver variable. { this.driver =
	 * driver; }
	 */
    
    @Then ("verify SetUpOneHome Page")
    public void verifySetUpOneHome() throws InterruptedException {
    	Thread.sleep(9000);
    	wait.until(ExpectedConditions.titleIs(expected_title_setUpOneHome));
    	String actual_Pagetitle_setUpOneHome = driver.getTitle();    	
    	
    	assertEquals(actual_Pagetitle_setUpOneHome, "Home | Salesforce");
    	System.out.println("Landed in SetUpOneHome Page");

	}     

    @And ("Click on App Launcher")
	public OneSetUpHomePage clickAppLauncherToggleMenu() throws InterruptedException {
		//Click on toggle menu button from the left corner
		//====================================================
					
		//driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		WebElement findElement = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
		findElement.click();
		return this;
	}
	
    @And ("Click on View All")
	public OneSetUpHomePage clickViewAll() throws InterruptedException {
		// 3.Click on View All link
		//=======================

		//driver.findElement(By.xpath("//button[text()='View All']")).click();
		WebElement findElementByXPath = driver.findElement(By.xpath("//button[text()='View All']"));
		findElementByXPath.click();
		return this;
	}

    @When ("Click on Service")
	public HomePage clickServicesLink() throws InterruptedException {
		
		WebElement findElement = driver.findElement(By.xpath("//p[text()='Service']"));
	    findElement.click();
				
		return new HomePage();
	}
	
    
	

		
	
	
	
	

}
	


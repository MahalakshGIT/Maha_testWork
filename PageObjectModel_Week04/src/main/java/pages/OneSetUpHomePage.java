package pages;

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

import base.ProjectSpecificMethods;

public class OneSetUpHomePage extends ProjectSpecificMethods{
	
	//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	//JavascriptExecutor js = (JavascriptExecutor) driver;
	//Actions a = new Actions(driver);
	
	//Constructor	
    public OneSetUpHomePage(RemoteWebDriver driver)//Constructor to receive session id from @BeforeMethod to Global driver variable.
	{
		  this.driver = driver;			
	}

	public OneSetUpHomePage clickGlobalSVGIcon() throws InterruptedException {
	    //Click on  Global Actions SVG Icon
	    //=================================		
		System.out.println("into HomePage Class - CreateNewCase Method");	
	    WebElement findElement1 = driver.findElement(By.xpath("(//ul[@class='slds-global-actions']/li[3]/div/div/div)[1]/div/div/a"));
	
	    elementClick(findElement1);
		System.out.println("Global Actions SVG icon clicked");				
		return this;
	}
	
	public OneSetUpHomePage clickNewCaseFromSVGIconDropdown() throws InterruptedException {
		//Click New Case
		//===============			
		WebElement we_NewCase = driver.findElement(By.xpath("(//ul[@class='scrollable' and @role='presentation']/li/a)[6]"));
	
		elementClick(we_NewCase);
		
		System.out.println("New Case Clicked");		
		return this;
	}
	
	public OneSetUpHomePage enterContactName(String contactName) throws InterruptedException {		
		
		//Enter Contact name as Your Name
		//===============================
		//driver.findElement(By.xpath("//input[@title='Search Contacts']")).click();
		WebElement findElement = driver.findElement(By.xpath("//input[@title='Search Contacts']"));
		elementClick(findElement);
		//WebElement WebElementSearchContact = driver.findElement(By.xpath("//input[@title='Search Contacts']"));
		
		//Action seriesOfAction=a.moveToElement(WebElementSearchContact).click().sendKeys("Mahalakshmi").sendKeys(Keys.TAB).build();
	//Actions a = new Actions(driver);
		Action seriesOfAction=a.moveToElement(findElement).click().sendKeys(contactName+System.currentTimeMillis()).sendKeys(Keys.TAB).build();
		seriesOfAction.perform();	
		
		//moveToElement(findElement,contactName,);	
		
		System.out.println("Contact Name Entered");
		return this;
	}
	
	public OneSetUpHomePage selectStatusFromDropdown() throws InterruptedException {
		//Click on Status Drop Down:
		String status_xpath = "//span[text()='Status']/parent::span/following-sibling::div/div/div/div/a";
	
		WebElement we_status_dpdn = driver.findElement(By.xpath(status_xpath));
		//js.executeScript("arguments[0].click();", we_status_dpdn);
		elementClick(we_status_dpdn);
		
		//Click on Status  as Escalated from Drop down
		//============================================
		WebElement WebElementStatusDropdown = driver.findElement(By.xpath("//a[@title='Escalated']"));
			
		//js.executeScript("arguments[0].scrollIntoView();", WebElementStatusDropdown);
		//js.executeScript("arguments[0].click();", WebElementStatusDropdown);
		
		elementClick(WebElementStatusDropdown);
		return this;
	}
	
	public OneSetUpHomePage enterSubject(String subject) {
		//Give subject as Testing
		//=========================
		//driver.findElement(By.xpath("//span[text()='Subject']/parent::label/following-sibling::input")).clear();
		WebElement findElement = driver.findElement(By.xpath("//span[text()='Subject']/parent::label/following-sibling::input"));
		clearData(findElement);
		//driver.findElement(By.xpath("//span[text()='Subject']/parent::label/following-sibling::input")).sendKeys(subject+System.currentTimeMillis());	
		WebElement findElement2 = driver.findElement(By.xpath("//span[text()='Subject']/parent::label/following-sibling::input"));
		enterData(findElement2, subject+System.currentTimeMillis());
		return this;
	}
	
	public OneSetUpHomePage enterDescription(String Desc) {
		//Give Description
		//=================
		//driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea")).clear();
		WebElement findElement = driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea"));
		clearData(findElement);
		
		//driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea")).sendKeys(Desc);
		WebElement findElement2 = driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea"));
		enterData(findElement2, Desc);
	
		//try {Thread.sleep(1000);}
		//catch(InterruptedException e) {e.printStackTrace();} 
		return this;
	}
	
	public OneSetUpHomePage clickSaveButton() throws InterruptedException {
		//Save button press
		//=================
		//driver.findElement(By.xpath("//button[@data-aura-class='uiButton']//span[text()='Save']")).click();
		WebElement findElement = driver.findElement(By.xpath("//button[@data-aura-class='uiButton']//span[text()='Save']"));
		elementClick(findElement);
		System.out.println("Save button Clicked");		
		//Verify the Message	
		//==================

		if(driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).isDisplayed())
		{
			//String txt_Confirmation_msg = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
			//System.out.println(txt_Confirmation_msg);
			
			WebElement findElement2 = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
			String txt_Confirmation_msg = getData(findElement2);
			
			
			String case_No = txt_Confirmation_msg.replaceAll("\\D","");
			int created_caseNo = Integer.parseInt(case_No);		
			//System.out.println(created_caseNo);
			if((txt_Confirmation_msg.contains("Case")) && (txt_Confirmation_msg.contains("was created."))) 
		    {System.out.println("New Case Has Been Created Successfully :"+created_caseNo);}	
		else
			System.out.println("New Case Not Created");
		}
				
			
		return this;
	}	

	//EditCase:
	public OneSetUpHomePage clickAppLauncher() throws InterruptedException {
		//Click on toggle menu button from the left corner
		//====================================================
					
		//driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		WebElement findElement = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
		elementClick(findElement);
		return this;
	}
	
	public OneSetUpHomePage clickViewAll() throws InterruptedException {
		// 3.Click on View All link
		//=======================

		//driver.findElement(By.xpath("//button[text()='View All']")).click();
		WebElement findElementByXPath = driver.findElement(By.xpath("//button[text()='View All']"));
		elementClick(findElementByXPath);
		return this;
	}

	public HomePage clickSalesLink() throws InterruptedException {
		//driver.findElement(By.xpath("//p[text()='Sales']")).click();
		WebElement findElement = driver.findElement(By.xpath("//p[text()='Sales']"));
		elementClick(findElement);
		System.out.println(driver.getTitle());
		
		return new HomePage(driver);

	}
	
	

		
	
	
	
	

}
	


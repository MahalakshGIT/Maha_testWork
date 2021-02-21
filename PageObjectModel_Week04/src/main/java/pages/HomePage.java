package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.ProjectSpecificMethods;

public class HomePage extends ProjectSpecificMethods{

	//public boolean edit_case_found = false;//????????WHERE TO DECLARE. IN THIS CLASS OR BASE-PROJECTSPEC CLASS??
	
	//WRITE ALL ELEMENTS OF A PAGE IN 1 SINGLE PAGE-JAVACLASS ITSELF FOR MAINTENANCE,READABILITY
	//Constructor
	public HomePage(RemoteWebDriver driver) {
		this.driver=driver;		
	}
	
	//W/O_mandatoryFields  //Editcase
	public HomePage clickCasesTab() throws InterruptedException{
	
		Thread.sleep(5000);
		WebElement we_casesTab = driver.findElement(By.xpath("//span[text()='Cases']"));
		js.executeScript("arguments[0].click();", we_casesTab);// Instead Selenium Click use JAVASCRIPT click
	
		System.out.println("Cases Tab clicked successfully");
		return this;	
		
	}	
	
	//W/O_mandatoryFields
	public HomePage clickNewButton() throws InterruptedException {

		//Click on New button
		//=====================
	
		WebElement findElement = driver.findElement(By.xpath("//div[@title='New' and contains(text(),'New')]/parent::a[@title='New']"));		
		elementClick(findElement);		
		
		System.out.println("New button clicked successfully");		
		return this;
	}
	
	//W/O_mandatoryFields
	public HomePage selectContactName(String ContactName) throws InterruptedException {

		//driver.findElement(By.xpath("//span[contains(text(),'Contact Name')]/parent::label/following-sibling::div//input[@title='Search Contacts']")).click();
		WebElement findElement = driver.findElement(By.xpath("//span[contains(text(),'Contact Name')]/parent::label/following-sibling::div//input[@title='Search Contacts']"));
		elementClick(findElement);		
		System.out.println("Contact Name Searchbox found successfully");
		
		//GET Contact Name - 'Naveen Elumalai' from Search Box
		//====================================================
		//AUTOMATE USING LIST OF WEBELEMENTS OF CONTACT NAME AND FROM IT CHOOSE GIVEN NAME
		//Actions a = new Actions(driver);
		WebElement we_GivenContactName = driver.findElement(By.xpath("//ul[@class='lookup__list  visible']/li/a/div[2]/div[contains(text(),'"+ContactName+"')]"));
	
		//a.moveToElement(we_GivenContactName).click().build().perform();;
		moveToElement(we_GivenContactName);		
		System.out.println("Given Contact Name-Naveen Elumalai has been chosen successfully");
		
		return this;
	}
	
	//W/O_mandatoryFields
	public  HomePage selectStatusAsNone() throws InterruptedException {
		//Select status as None
		//=======================
		//driver.findElement(By.xpath("//label[@class='slds-form-element__label']/following-sibling::div")).click();
		WebElement findElement = driver.findElement(By.xpath("//label[@class='slds-form-element__label']/following-sibling::div"));
		elementClick(findElement);			

		WebElement we_StatusDropdown = driver.findElement(By.xpath("//span[contains(text(),'--None--') and @class='slds-truncate']"));
			
		//js.executeScript("arguments[0].scrollIntoView();", we_StatusDropdown);
		//js.executeScript("arguments[0].click();", we_StatusDropdown);
		elementClick(we_StatusDropdown);	
					
		return this;
	}
	
	//W/O_mandatoryFields
	public  HomePage enterSubject() {
		
		WebElement we_subject = driver.findElement(By.xpath("//label[@data-aura-class='uiLabel']/span[text()='Subject']"));
		
		//js.executeScript("arguments[0].scrollIntoView();", we_subject);
		scrollIntoView(we_subject); 
				
		
		WebElement findElement = driver.findElement(By.xpath("//span[text()='Subject']/parent::label/following-sibling::input"));
		clearData(findElement);
		enterData(findElement, "Testing");		
		
		return this;
	}
	
	//W/O_mandatoryFields
	public HomePage enterDescription() {
		//Give Description
	
		WebElement findElement = driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea"));
		clearData(findElement);
		enterData(findElement, "Automation testing");
		
		return this;
	}
	
	//W/O_mandatoryFields
	public HomePage ClickSaveButton() throws InterruptedException {
		//driver.findElement(By.xpath("//button[@data-aura-class='uiButton forceActionButton' and @title='Save']/span[text()='Save']")).click();
		WebElement findElement = driver.findElement(By.xpath("//button[@data-aura-class='uiButton forceActionButton' and @title='Save']/span[text()='Save']"));
		elementClick(findElement);
		
		System.out.println("Save button Clicked");		
		
		return this;
	}
	
	//W/O_mandatoryFields
	public HomePage CreateCaseWO_mandateFields_verifyError() {

		//String actual_result1=driver.findElement(By.xpath("//div[@class='genericNotification']/span[@class='genericError uiOutputText']")).getText();
		//System.out.println(actual_result1);
		WebElement findElement = driver.findElement(By.xpath("//div[@class='genericNotification']/span[@class='genericError uiOutputText']"));
		String actual_result1 = getData(findElement);
		
		//String actual_result2=driver.findElement(By.xpath("//div[@class='desktop forcePageError']/ul[@class='errorsList']")).getText();
		//System.out.println(actual_result2);
		
		WebElement findElement2 = driver.findElement(By.xpath("//div[@class='desktop forcePageError']/ul[@class='errorsList']"));
		String actual_result2 = getData(findElement2);
		
		//Verify Error Message		
		WebElement findElement3 = driver.findElement(By.xpath("//div[@class='desktop forcePageError']/ul[@class='errorsList']"));
		String error_msg = getData(findElement3);
		if(actual_result1.contains("error"))
		{
			System.out.println("Test Case Passed: As Cannot Create New Case Without Mandatory Fields " +error_msg );	
			
		}			
		return this;
	}
	
	//Editcase
		//public HomePage enterEditCaseNoIn_Search(String to_editCaseNumber) {
		public HomePage enter_valid_caseno_In_Searchbox(String to_editCaseNumber) {
			//==========================================================================
			WebElement we_SearchCase = driver.findElementByXPath("//input[@name='Case-search-input' and @class='slds-input']");
		System.out.println(to_editCaseNumber);
			//WebElement we_SearchCase = driver.findElementByName("Case-search-input");
			//WebElement we_SearchCase = driver.findElement(By.name("Case-search-input"));
			//Actions a= new Actions(driver);
			//a.moveToElement(we_SearchCase).click().sendKeys(to_editCaseNumber).sendKeys(Keys.ENTER).build().perform();

			//moveToElement(we_SearchCase, to_editCaseNumber);//Enter Pressed on Action-Reusable Utility
			
			enterData(we_SearchCase, to_editCaseNumber);
			
			return this;
		}
		
		//EditCase
		//verify whether the resultant table case no is = to the given 'to_editCaseNumber'
		public HomePage verifyCaseNoFromSearchResult(String to_editCaseNumber) {
			
			WebElement findElementByXPath = driver.findElementByXPath("(//table[@data-aura-class='uiVirtualDataTable'])[1]/tbody/tr/th/span/a[@data-aura-class='forceOutputLookup']");
			String str_caseNo_from_Resultant_Table = getData(findElementByXPath);	
			System.out.println(str_caseNo_from_Resultant_Table);
			System.out.println(edit_case_found);
		//	System.out.println(str_caseNo_from_Resultant_Table==to_editCaseNumber);
			if(str_caseNo_from_Resultant_Table.equals(to_editCaseNumber))
			{
				System.out.println("Resultant case is Matching to Search-to_editCaseNumber");
				edit_case_found =true;
				//System.out.println(edit_case_found);
			}
			return this;	
			
		}

		//EditCase
		public HomePage clickEditActionDropdown() throws InterruptedException {
		
			//WebElement findElement2 = driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-down slds-icon_container']//lightning-primitive-icon)[2]"));
		//	WebElement findElement2 = driver.findElement(By.xpath("//div[@class='forceVirtualActionMarker forceVirtualAction']//a"));
			
			Thread.sleep(10000);
			WebElement findElement2 = driver.findElement(By.xpath("//a[@title='Show 3 more actions']"));
			//noSuchElement //ElementInterceptedException
			
			//elementClick(findElement2);
			findElement2.click();
					
			WebElement findElement = driver.findElement(By.xpath("//li[@role='presentation' and @class='uiMenuItem']//a[@title='Edit']"));
			
			//elementClick(findElement);
			findElement.click();
			
			return  this;	
		}
		
		public HomePage selectStatusAsWorking() throws InterruptedException {
			
			WebElement findElementByXPath = driver.findElementByXPath("(//input[@class='slds-input slds-combobox__input'])[2]");
			elementClick(findElementByXPath);
			
			WebElement we_status = driver.findElementByXPath("//lightning-base-combobox-item[@data-value='Working']");
			elementClick(we_status);		
			
			return this;
		}
		
		//Editcase
		public HomePage selectPriorityAsLow() throws InterruptedException {
			
			WebElement findElementByXPath = driver.findElementByXPath("//span[text()='Priority']/parent::span/following-sibling::div//a");
			elementClick(findElementByXPath);		
			
			WebElement we_priority = driver.findElementByXPath("//a[@role='menuitemradio'and @title='Low' and text()='Low']");
			elementClick(we_priority);
			
			return this;
		}
		
		//Editcase
		public HomePage selectSLAviolationAsNo() throws InterruptedException {
			
			WebElement we_SLAviolation = driver.findElementByXPath("//span[text()='SLA Violation']/parent::span/following-sibling::div//a");
			elementClick(we_SLAviolation);
		
			WebElement we_SLAviolationDp = driver.findElementByXPath("//div[@class='select-options']//a[@title='No' and text()='No']");
			elementClick(we_SLAviolationDp);
			//System.out.println("SLA violation option Clicked as NO ");		
			
			return this;
		}
		
		//Editcase
		public HomePage selectCaseOriginAsPhone() {
			
			 //To scroll up to get Case Origin DropDown
			//WebElement we_CaseOrigin = driver.findElement(By.xpath("//span[text()='Case Origin']/parent::span/following-sibling::div//a"));
			WebElement we_CaseOrigin_Label = driver.findElementByXPath("//span[text()='Case Origin']");			
			//SCROLLINTOVIEW of label-origin
			scrollIntoView(we_CaseOrigin_Label);
		
			WebElement we_CaseOrigin = driver.findElementByXPath("//span[text()='Case Origin']/parent::span/following-sibling::div//a");
			we_CaseOrigin.click();
			//System.out.println("Case Origin Clicked");
			
			WebElement we_CaseOrigin_Phone = driver.findElementByXPath("//div[@class='select-options']//a[@title='Phone' and text()='Phone']");
				js.executeScript("arguments[0].click();",we_CaseOrigin_Phone ); 
				
				//System.out.println("Case Origin Clicked as Phone ");			
				
			return this;
		}

		//Editcase
		public HomePage clickSave_EditCase() throws InterruptedException {
			WebElement findElement = driver.findElementByXPath("//button[@data-aura-class='uiButton forceActionButton']/span[text()='Save']");
			elementClick(findElement);  
			//System.out.println("Save button Pressed Successfully for Edit operation");
			
			return new HomePage(driver);
		}
		
		//Editcase			
		public HomePage verifyCaseEditSuccessful(String to_editCaseNumber) {
			
			WebElement findElement2 = driver.findElementByXPath("//span[@data-aura-class='forceActionsText']");		
			String txt_verification = getData(findElement2);
			
			if(txt_verification.contains(to_editCaseNumber) && txt_verification.endsWith("was saved."))
			{
				System.out.println("TC_Edit Case->Verified Successfully For Edit Operation: "+to_editCaseNumber); 
			}
			
			return this;
		}
		
		//DeleteCase
		public HomePage clickCaseOwnerAliasDropdown() {
						
			driver.findElement(By.xpath("//span[text()='Case Owner Alias' or (@class='slds-truncate' and @title='Case Owner Alias') ]/ancestor::a")).click(); 
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("CaseOwner Alias Column clicked successfully");	
			
			return this;
		}	
			
		public HomePage getDeleteCaseFromResultantTable(String to_Delete_Case_Number) {
			flag_DeleteAction_Clicked = false;	
			System.out.println(flag_DeleteAction_Clicked);
			
			//take the total count displayed in top of table:""50""+ items • Sorted by Case Owner Alias • Updated 2 minutes ago"
			WebElement findElement = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']"));
			String str_itemcount = findElement.getText();
			System.out.println(str_itemcount);
			
			String replaceAll_str = str_itemcount.replaceAll("\\D", "");
			int int_total_item = Integer.parseInt(replaceAll_str);
			System.out.println(int_total_item);
			
			for (int i=1;i<=int_total_item;i++)
			{	
				//table/tbody/tr[1]/th//a--->each row's case no 
			
				WebElement findElement2 = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/th//a"));
				js.executeScript("arguments[0].scrollIntoView();", findElement2);
				String actual_caseno = findElement2.getText();
				//System.out.println(actual_caseno);
				if(to_Delete_Case_Number.equals(actual_caseno))
				{
				 System.out.println(to_Delete_Case_Number +" : Found in Table.Lets proceed to Delete at Row: " +i);
				 flag_DeleteCase_found = true;
				 
				 WebElement we_action_dropdown = driver.findElement(By.xpath("(((//table/tbody/tr)["+i+"]/td)[7])/span//a"));
				 js.executeScript("arguments[0].scrollIntoView();", we_action_dropdown);
				 js.executeScript("arguments[0].click();", we_action_dropdown);
			     //To select FOUND RECORD'S Action Dropdown as Delete
			     //==================================================
			     WebElement we_delete_action = driver.findElement(By.xpath("//div[@class='forceActionLink' and @data-aura-class='forceActionLink' and text()='Delete']"));
			     js.executeScript("arguments[0].click();", we_delete_action);
			     try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
				 System.out.println("Delete action clicked successfully");		 
				 
				 flag_DeleteAction_Clicked = true;
				 break;				 
				 }	
								
				if(i==int_total_item) {
				 js.executeScript("arguments[0].scrollIntoView();", findElement2);
				 WebElement findElement1 = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']"));
				 String str_itemcount1 = findElement1.getText();
				 System.out.println(str_itemcount1);
				 	
				 String replaceAll_str1 = str_itemcount1.replaceAll("\\D", "");
				 int int_total_item1 = Integer.parseInt(replaceAll_str1);
				 System.out.println(int_total_item1);
				 int_total_item=int_total_item1;			 
			 }
			 
		}	
		System.out.println(flag_DeleteCase_found +","+  flag_DeleteAction_Clicked );
		
		return this;
		}
		
		//DeleteCase
		public HomePage clickDeleteButton() {

			System.out.println("both True . Proceed to Delete Action");
			String confirm_delete_action=driver.findElement(By.xpath("//h2[text()='Delete Case']/following::div[@class='detail slds-text-align--center' and contains(text(),'delete') ]")).getText();
			System.out.println(confirm_delete_action);
			driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button[@data-aura-class='uiButton--default uiButton--brand uiButton forceActionButton']")).click();
			
			try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Delete action Accepted successfully");	

			return this;
		}
		
		//DeleteCase
		public HomePage verifyDeleteOperation(String to_Delete_Case_Number) {
			//6.Verify the case with your name is deleted or not
			//===================================================
			String verify_text = driver.findElement(By.xpath("//div[@class='slds-align-middle slds-hyphenate']//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
			
			if((verify_text.substring(0, 4).equalsIgnoreCase("case")) && (verify_text.contains(to_Delete_Case_Number))&& (verify_text.substring(16, 27).equalsIgnoreCase("was deleted"))   )
			{		
				System.out.println("Case: "+to_Delete_Case_Number+ " Successfully deleted");		
				
			}
			return this;
		}
		
}

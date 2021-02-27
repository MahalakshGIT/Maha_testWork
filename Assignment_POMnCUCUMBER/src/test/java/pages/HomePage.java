package pages;

import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.BaseClass;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class HomePage extends BaseClass{

	String verify_report_name;

	@And ("Click On Reports Tab")
	public HomePage clickCasesTab() throws InterruptedException{

		Thread.sleep(10000);
		WebElement we_casesTab = driver.findElement(By.xpath("//span[text()='Reports']"));
		js.executeScript("arguments[0].click();", we_casesTab);// Instead Selenium Click use JAVASCRIPT click
	
		System.out.println("Cases Tab clicked successfully");
		return this;
		
	}	
	
	@When ("Click on New Report SalesForce Classic") 
	public HomePage clickNewReportSalesForceClassic() throws InterruptedException{

		WebElement we_reportsTab = driver.findElement(By.xpath("//div[@title='New Report (Salesforce Classic)']"));
		js.executeScript("arguments[0].click();", we_reportsTab);// Instead Selenium Click use JAVASCRIPT click
	
		System.out.println("Reports Tab clicked successfully");
		return this;
		
	}	

	
	@Then ("Click on Leads")
	public HomePage clickLeads() {
		//String name = ""		
		
		String xpath = "//iframe[@class='isEdit reportsReportBuilder']";
		
		WebElement total_frames = driver.findElements(By.tagName("iframe")).get(0);		
		driver.switchTo().frame(total_frames);
		System.out.println("inside frame");
		
		WebElement we_Leads = driver.findElement(By.xpath("(//a[@class='x-tree-node-anchor']/span[text()='Leads'])[1]"));
		//WebElement we_Leads = driver.findElement(By.xpath("//div[@class='x-tree-node-el x-unselectable folder x-tree-node-expanded']/following-sibling::ul//div"));
		we_Leads.click();
	
		System.out.println("Leads folder icon clicked successfully");
		
		return this;

	}
	
	@And ("verify iamge for reports displayed")
	public HomePage verifyImageDisplayed() {
			
		WebElement findElementById = driver.findElementById("thePage:dummyForm:report_img");
		boolean displayed = findElementById.isDisplayed();
		Assert.assertTrue(displayed);
		
		System.out.println("Report Image available to Download");
		
		return this;

	}
		
	@And ("Download the Lead Report Image on the Right side")
	public HomePage download_LeadReport() throws MalformedURLException, IOException {
		String url = driver.findElementById("thePage:dummyForm:report_img").getAttribute("src");
		BufferedImage bufferedImage = ImageIO.read(new URL(url));
		File outputfile = new File("saved.png");
		ImageIO.write(bufferedImage, "png", outputfile);
		System.out.println("Downloaded  Lead Report Image and Saved as png file Successfully");
		return this;
	}
	
	@And ("Click on Create")
	public HomePage clickOnCreate() {
		driver.findElement(By.id("thePage:rtForm:createButton")).click();
		driver.switchTo().defaultContent();
		System.out.println("Clicked Create Button successfully");
		return this;
	}
	
	@And ("Select Range as All Time")
	public HomePage selectRange() {
		
		List<WebElement> findElements = driver.findElements(By.tagName("iframe"));
		System.out.println(findElements.size());
		driver.switchTo().frame(0);
					
		driver.findElementByXPath("//input[@name='duration']/following-sibling::img").click();
		driver.findElementByXPath("//div[text()='All Time']").click();
		System.out.println("Selected Range as All time Successfully");
		return this;
	}
	
	@And ("Select From date as todays date")
	public HomePage selectFromDateAsToday() {
		driver.findElementById("ext-gen152").click();
		driver.findElementById("ext-gen268").click();//Today-button Being used.
		System.out.println("Selected From date - todays date sucessfully");
		return this;
	}
	
	@And("Select To as plus 5 days From Today")
	public HomePage selectToDateAsPlusFiveDates() {
		
		driver.findElementByXPath("//input[@name='endDate']/following-sibling::img").click();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 5); // Adding 5 days
		String output = sdf.format(c.getTime());
		System.out.println("5+ Dates :"+output);
		//driver.findElementByName("endDate").sendKeys(output);
		WebElement findElementByName = driver.findElementByName("endDate");

		a.moveToElement(findElementByName).click().sendKeys(output).sendKeys(Keys.ENTER).build().perform();
				
		System.out.println("Selected To date: today's date + 5- successfully");
		return this;
	}
	
	@Then ("Verify Whether the Preview is in Tabular Format")
	public HomePage verifyTabularView() {

		String txt_tabView = driver.findElementByXPath("//em[@class='x-btn-arrow']/button[text()='Tabular Format']").getText();
		
		if(txt_tabView.contentEquals("Tabular Format")) {
			System.out.println("Verified: Preview as in Tabular Format-Successfully");
		}
		
		return this;
	}
	
	@When ("Get the List of Company /Account")
	public HomePage getListOfCompanyAccount() throws InterruptedException {
		
		Thread.sleep(5000);//Before accessing the Table items, Give enough time to load. To avoid StaleEleException
		
		int_GrandTotals = 0; //initialize	
		System.out.println("initial grandtotal : "+int_GrandTotals);
		String txt_TotalRecords = driver.findElementByXPath("//table[@class='x-grid3-row-table']//b[contains(text(),'Grand Totals')]").getText();
		System.out.println("gotListOfCompanyAccount_total as :"+txt_TotalRecords);
		String str_replaceAll = txt_TotalRecords.replaceAll("\\D", "");
		int_GrandTotals = Integer.parseInt(str_replaceAll);
				
		System.out.println("Total Records for the Report : "+int_GrandTotals);
		
		 List<String> company_account = new ArrayList<>();
	 		
	    String xpath="//div[@class='x-grid3-body']/div";
	    			  //div[@class='x-grid3-body']/div[1]/table/tbody/tr/td[5]
	    
		  for(int i=1;i<=int_GrandTotals;i++) { 
			  
			  System.out.println(i);
			  String z = xpath + "[" + i + "]/table/tbody/tr/td[5]";
			  System.out.println(z);
			  
			  WebElement we_Report_TableRowElement = driver.findElementByXPath(xpath+"["+i+"]/table/tbody/tr/td[5]");			  
			  js.executeScript("arguments[0].scrollIntoView();", we_Report_TableRowElement);
					  
			  company_account.add(we_Report_TableRowElement.getText());
			  System.out.println("Added to List: "+company_account.get(i-1));			  
		  }
		System.out.println("Got into List: Company/Account names Successfully");      
		return this;
	}	
	
	@When("Get the List of Company /Account-Reference")
	public void getTheListOfBillingStateProvince() {
		
		List<WebElement> elerowCount = driver.findElements(By.xpath("//div[@class='x-grid3-body']/div"));
		
		int rowCount= elerowCount.size();
		
				System.out.println("row count*******"+rowCount);
				
				for (int i = 1; i <= rowCount; i++) {
					
					System.out.println(i);
					String x = "(//div[@class='x-grid3-body']/div)["+i+"]//td[5]/div";
					System.out.println(x);
					
					List<WebElement> elecpyActNameLst = driver.findElementsByXPath("(//div[@class='x-grid3-body']/div)["+i+"]//td[5]/div");
					System.out.println(elecpyActNameLst.size());
					
					List<String> lstActName = new ArrayList<String>();
					for (WebElement ele :elecpyActNameLst) {
						String extAccName = ele.getText();
						
						System.out.println("for each listelement: to be added to List" +extAccName);
						lstActName.add(extAccName);						
					}
					
					System.out.println("***"+lstActName.toString());
				}
				
	   
	}
	
	
	@Then ("Get the Grand Total of Records Available")
	public HomePage getGrandTotalOfRecords() {
		
		System.out.println("Total Records: "+int_GrandTotals);
		
	
		return this;

	}
	
	  @When ("Click on Save") 
	  public HomePage clickSave() {
		  
		  driver.findElementByXPath("(//button[@class=' x-btn-text'])[1]").click();		  
		  System.out.println("Clicked on Save Button Successfully");		
		  
		  return this;	  
	  
	  }
	  
	  
	  @Then ("Save Report is displayed")
	 public HomePage verifySaveReportIsDisplayed()
	  {
		  WebElement we_SaveReport = driver.findElementById("saveReportDlg");
		  Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(we_SaveReport)).isDisplayed());
		 
		  System.out.println("Verified successfully that Save Report-Dialog is displayed");
		  
		  return this;
	  }
	  
	 

@And("Enter Report name as (.*)")
public HomePage enterReportname(String reportName) {
	
    driver.findElementByName("reportName").sendKeys(reportName);
    verify_report_name = reportName;
    System.out.println(verify_report_name);
    System.out.println("Entered Report name as <Your name> Successfully");
    return this;
    
}

@And("Enter Report Unique name as (.*)")
public HomePage enterReportUniquename(String unique_report_name) {
	
	  driver.findElementById("saveReportDlg_DeveloperName").clear();		 
	  driver.findElementById("saveReportDlg_DeveloperName").sendKeys(unique_report_name+System.currentTimeMillis());	 
	  
	  System.out.println("Entered Report Unique name as <Your name> Successfully");
	  return this;
}

@And("Enter Report Description as Report Updated by (.*)")
public HomePage enterReportDescription(String reportname) {
	
	  driver.findElementByName("reportDescription").sendKeys(reportname);
	  System.out.println("Entered Report Description as Report Updated by <yourName>");
	  return this;
	    
}

@And("Select Report Folder as Unfiled Public Reports")
public HomePage selectReportFolder() {
	
	 // driver.findElementById("ext-gen415").click(); //Click on img tag for Dropdown Icon
	  
	  WebElement findElementByXPath = driver.findElementByXPath("//input[@id='saveReportDlg_folderFinder']/following-sibling::img[1]");
	  js.executeScript("arguments[0].click();",findElementByXPath );
	  System.out.println("Clicked dropdown");
	  
	  //WebElement findElementByXPath2 = driver.findElementByXPath("//div[@id='ext-gen312']//div[text()='Unfiled Public Reports']");
	  WebElement findElementByXPath2 = driver.findElementByXPath("//div[@id='ext-gen324']//div[text()='Unfiled Public Reports']");
	  wait.until(ExpectedConditions.elementToBeClickable(findElementByXPath2));
	  js.executeScript("arguments[0].scrollIntoView();", findElementByXPath2);
	  js.executeScript("arguments[0].click();",findElementByXPath2 );
	  
	  System.out.println("Selected Report Folder as Unfiled Public Reports Successfully");
	  return this;
	  
}

@When("Click on Save-Save Report")
public HomePage clickSaveOnSaveReportDialog() throws InterruptedException {
	
	  Thread.sleep(5000);
	  driver.findElementByXPath("//table[@id='dlgSaveReport']").click();
 
	  System.out.println("Clicked Save-on Save Report Dialog Successfully");
	  
	  
	  driver.switchTo().defaultContent(); //Till here the Frame Content is Same-Its Working in the same frame
	  
	  return this;
	  
}

@Then("Verify the Created Report name is Displayed On Left side of The Page as (.*)")
public HomePage verifyReportCreated(String ReportName) throws InterruptedException {
	//NOT WORKING
	/*
	 * List<WebElement> findElementsByTagName =
	 * driver.findElementsByTagName("iframe");
	 * System.out.println("frame size:"+findElementsByTagName.size());
	 * 
	 * String str_title =
	 * driver.findElement(By.tagName("iframe")).getAttribute("title");
	 * System.out.println(str_title);
	 */
	
	WebElement we_frame = driver.findElementByXPath("//iframe[starts-with(@title,'"+ReportName+"')]");
	driver.switchTo().frame(we_frame);
	
	//driver.switchTo().frame("//iframe[contains(@title,'maha')]");
	//driver.switchTo().frame("//iframe[contains(@title,'Unsaved')]");
	System.out.println("inside Frame for Created Report name is Displayed- verification");
	
    Thread.sleep(4000); //After Save being Given wait for sometime to get the Visible text of Report name	
	 // String txt_Created_ReportName = driver.findElementByXPath("//h1[text()='Report Type: Leads']/following-sibling::h2").getText();
	 //String txt_Created_ReportName = driver.findElementByTagName("h2").getText();
    
    // String txt_Created_ReportName = driver.findElementByXPath("//div[@class='content']//h2[1]").getText();
     String txt_Created_ReportName = driver.findElementByXPath("//div[@class='content']//h2").getText();
	
	 if(txt_Created_ReportName.equalsIgnoreCase(ReportName)) {	
		  
		  System.out.println("Verified that the created Report IS-Displayed in left side-Successfully");
	  }
	  else {
		  System.err.println("Report name not Same");
	  }
 
	 driver.switchTo().defaultContent();
	  return this;    
	  
}

@When("Click on Run Report as (.*)")
public HomePage clickRunReport(String ReportName) throws InterruptedException {
		
	WebElement we_frame = driver.findElementByXPath("//iframe[starts-with(@title,'"+ReportName+"')]");
	driver.switchTo().frame(we_frame);
	
	System.out.println("inside Frame for Run Report Click action");
	
	 	//driver.switchTo().frame("//iframe[contains(@title,'maha')]");	
		//System.out.println("inside Frame for RunReport Click action");
	
	  driver.findElementByXPath("//button[@class=' x-btn-text run-report-btn-icon']").click();
	  
	  System.out.println("Clicked Run-Report Button Successfully");
	  Thread.sleep(5000); //UI takes more time to Load next page
	  driver.switchTo().defaultContent();
	  
	  return this;
	  
}
@And ("Get the total Number of Records")
public HomePage getTotalRecords() throws InterruptedException {
		
	  	
	  Thread.sleep(2000);	
	  
	List<WebElement> findElementsByTagName = driver.findElementsByTagName("iframe");
	System.out.println(findElementsByTagName.size());
	  
	 // String title = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("//iframe[@title='Report Viewer']")).getTitle();
	 // System.out.println("Frame Title: "+title);
	 // driver.switchTo().frame("(//iframe[@data-aura-class='reportsReportBuilder'])[3]");
	
	 driver.switchTo().frame(1);
	 // WebElement findElementByXPath = driver.findElementByXPath("//div[@id='report-main']//h1");
	  
	  String txt_TotalRecords_Created = driver.findElementByXPath("//div[@title='Total Records']/following-sibling::div").getText(); 
		  
	  System.out.println("Total No Of Records Created :" + txt_TotalRecords_Created);
	  
	  driver.switchTo().defaultContent();
	  return this;
	  
}

@When ("Click on Edit") 
public HomePage ClickEdit() throws InterruptedException {
		
	//div[@class='slds-button-group actionBarButtonGroup']/button
	
	 // driver.switchTo().frame("//iframe[@title='Report Viewer']");
	  
	  driver.switchTo().frame(1);
	  driver.findElementByXPath("//button[text()='Edit']").click(); 
		  
	  System.out.println("Edit Button Clicked Successfully");
	  Thread.sleep(4000);
	  
	  driver.switchTo().defaultContent();
	  
	  return this;
	  
}


  @And ("Click on Close") public HomePage ClickClose() throws InterruptedException {
  
	  
  Thread.sleep(6000); 
//  driver.switchTo().frame("//iframe[@title='Report Builder']");
WebElement we_frame_close = driver.findElementByXPath("//iframe[@class = 'isEdit reportsReportBuilder'][@title='Report Builder']");
    
  driver.switchTo().frame(we_frame_close);
 // driver.switchTo().frame(1);
  driver.findElementByXPath("//div[@class='panelItemGroup']//button[text()='Close']").click();
  driver.findElementByXPath("//div[@class='panelItemGroup']//button[text()='Close']").click();
  
  System.out.println("Close Button Clicked Successfully");
  driver.switchTo().defaultContent(); //Afterwards Control goes to Parent
  
  return this;
  
  }

@Then ("Verify the Report Name and Get the Date and Time When the Report is Created On (.*)")
public HomePage getDateTimeOfReportCreation(String Reportname) {
		
	  driver.switchTo().defaultContent();
	  
	  String str_tableCount = driver.findElementByXPath("//span[@class='countSortedByFilteredBy uiOutputText']").getText();
	System.out.println(str_tableCount);
	  int tableCount = Integer.parseInt(str_tableCount);
	  System.out.println(tableCount);
	  
	  String xpath="(//table[@class='slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr)";
		    
	  for(int i=0;i<=tableCount;i++) {
		  
		  WebElement we_Report_TableRowElement = driver.findElementByXPath(xpath+"["+i+"]/th");
		  js.executeScript("arguments[0].scrollIntoView();", we_Report_TableRowElement);
		  
		  String txt_Matching_report_Name =we_Report_TableRowElement.getText();		  
		  System.out.println(txt_Matching_report_Name);
		  
		  if(txt_Matching_report_Name.equalsIgnoreCase(Reportname)) {
			  
			  String txt_Creation_DateTime = driver.findElementByXPath(xpath+"["+i+"]/td[4]").getText();
			  System.out.println("Report Creation Date and Time : "+txt_Creation_DateTime);			  			  
		  } 
		  
	  }
	  System.out.println("Got the Report Creation Date and Time Successfully");
	  return this;
	  
}

}

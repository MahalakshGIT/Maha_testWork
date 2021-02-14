package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.LoginPage;

public class TC_CreateNewCase extends ProjectSpecificMethods {
	
	@BeforeTest
	public void setfile() {
		excelFileName = "CreateNewCase";
	}
		
	@Test(dataProvider = "fetchData")//to integrate the dp with the tc use ATTRIBUTE
	public void CreateNewCase(String contactName, String Subject,String Description) throws InterruptedException, Exception {
	//	LoginPage lp = new LoginPage(); //For testcase-CreateNewCase the action starts from Login So create an OBJ for Login
	//	lp.enterUsername().enterPassword().clickLoginButton().clickGlobalSVGIcon().clickNewCaseFromDropdown().enterContactName().enterSubject().enterDescription().clickSaveButton();
		
	//	OR instead of above 2 lines, just use the below 1 line:
		
		new LoginPage(driver)
		
		.enterUsername()		
		.enterPassword()
		.clickLoginButton()
		.clickGlobalSVGIcon()
		.clickNewCaseFromSVGIconDropdown()
		.enterContactName(contactName)		
		.enterSubject(Subject)
		.enterDescription(Description)
		.clickSaveButton();		

	}
	
}

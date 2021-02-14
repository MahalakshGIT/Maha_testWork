package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.LoginPage;

public class TC_CreateNewCaseWithoutMandatoryFields extends ProjectSpecificMethods {

	
	 // @BeforeClass 
	 // public void setFile() {
	  
	  //excelFileName="CreateCaseWithOutMandatoryFields"; 
	 // }

	//@Test(dataProvider = "fetchData")//DataProvider Not required as Only One field:Just pass it as Argument inselectContactName(NaveenElumalai)
	@Test
	public void mtdNewCaseWithoutMandatoryFields() throws Exception
	{
	
		new LoginPage(driver)
		.enterUsername()
		.enterPassword()
		.clickLoginButton()
		.clickAppLauncher()
		.clickViewAll()
		.clickSalesLink()
		.clickCasesTab() //Cases AnchorLink click //clickNewButton //->NewCaseWindow // selectNavenElumalai // selectStatusAsNone // enterSubject //enterDescription //ClickSaveButton // verifyError
		.clickNewButton()
		.selectContactName("Naveen Elumalai")
		.selectStatusAsNone()
		.enterSubject()
		.enterDescription()
		.ClickSaveButton()
		.CreateCaseWO_mandateFields_verifyError();
		
		
	}
	
}	
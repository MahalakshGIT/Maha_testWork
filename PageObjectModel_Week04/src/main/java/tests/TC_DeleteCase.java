package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.HomePage;
import pages.LoginPage;

public class TC_DeleteCase extends ProjectSpecificMethods {

	@BeforeClass
	public void setFile() {
		excelFileName = "DeleteCase";

	}
	
	@Test(dataProvider = "fetchData")
	public void tc_deleteCase(String to_Delete_Case_Number) throws InterruptedException, Exception {
		
		flag_DeleteCase_found = false;
		flag_DeleteAction_Clicked = false;
		
		new LoginPage(driver)
		.enterUsername()
		.enterPassword()
		.clickLoginButton()
		.clickAppLauncher()
		.clickViewAll()
		.clickSalesLink()
		.clickCasesTab()
		.clickCaseOwnerAliasDropdown()
		.getDeleteCaseFromResultantTable(to_Delete_Case_Number);
		if(flag_DeleteCase_found==true && flag_DeleteAction_Clicked==true) {
			new HomePage(driver)
			.clickDeleteButton()
			.verifyDeleteOperation( to_Delete_Case_Number);			
		}	
		else {
			System.out.println("Cannot do Delete of CaseNo:" + to_Delete_Case_Number);
			 }
		}
}
	

package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.HomePage;
import pages.LoginPage;

public class TC_EditCase extends ProjectSpecificMethods {
	
	@BeforeClass  //or @BeforeTest
	public void setexcelName() {
		excelFileName = "EditCase";

	}
	
	@Test(dataProvider ="fetchData")
	public void EditCase(String to_editCaseNumber) throws Exception {
		
		System.out.println(to_editCaseNumber);
		
		new LoginPage(driver)
		.enterUsername()
		.enterPassword()
		.clickLoginButton()
		.clickAppLauncher()
		.clickViewAll()
		.clickSalesLink()
		.clickCasesTab()
		.enter_valid_caseno_In_Searchbox(to_editCaseNumber)
		.verifyCaseNoFromSearchResult(to_editCaseNumber);
		System.out.println(edit_case_found+"value of flag");
		if(edit_case_found==true)
		{
			new HomePage(driver)
			.clickEditActionDropdown()
			.selectStatusAsWorking()
			.selectPriorityAsLow()
			.selectSLAviolationAsNo()
			.selectCaseOriginAsPhone()
			.clickSave_EditCase()
			.verifyCaseEditSuccessful(to_editCaseNumber);
		}
		else {
			System.out.println(edit_case_found+ "Given case Not Found in search Result. Cannot Continue Edit");
		}	
		

	}
	
}

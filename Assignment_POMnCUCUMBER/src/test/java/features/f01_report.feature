Feature: report
Scenario Outline: Create new report and verify
Given Enter Username 
And Enter password 
When Click Login Button
Then verify SetUpOneHome Page   
And Click on App Launcher
And Click on View All
When Click on Service 
And Click On Reports Tab 
When Click on New Report SalesForce Classic
#Then verify Create New Report icon On Left side of The Page Is Displayed
Then Click on Leads
And verify iamge for reports displayed
And Download the Lead Report Image on the Right side 
When Click on Create
And Select Range as All Time 
And Select To as plus 5 days From Today                 
Then Verify Whether the Preview is in Tabular Format 
When Get the List of Company /Account
Then Get the Grand Total of Records Available 
When Click on Save 
Then Save Report is displayed
And Enter Report name as <reportname> 
And Enter Report Unique name as <uniqueReportName> 
And Enter Report Description as Report Updated by <reportname>
And Select Report Folder as Unfiled Public Reports
When Click on Save-Save Report 
Then Verify the Created Report name is Displayed On Left side of The Page as <reportname> 
When Click on Run Report as <reportname> 
And Get the total Number of Records 
When Click on Edit  
And Click on Close 
Then Verify the Report Name and Get the Date and Time When the Report is Created On <Reportname> 

Examples: 
		  |reportname|uniqueReportName|
		  |mahalakshmi|m1|
		


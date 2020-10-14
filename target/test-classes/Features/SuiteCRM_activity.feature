@SuiteCRM_activity
Feature: Performs various operations on Suite CRM portal

  
  Background: Login to Suite CRM portal
  	Given User navigates to Suite CRM portal and provides login credentials
  	Then User is logged in to Suite CRM portal
  	
  @countDashlets	
  Scenario:	Open the homepage and count the number of the dashlets on the page
  	Given Count the number of dashlets on the homepage and print the title of the dashlet
  	#Then Print the number and title of each Dashlet into the console
  	And close the browser for SuiteCRM portal
  	
  @createLeadsUsingParameterization
  Scenario: Create leads using parameterization
  	Given Navigate to Sales -> Leads -> Create Lead
  	When User fills following details "Mr.", "FirstName6", "LastName6" and "Department head6" and saves form
  	Then Navigate to View Leads page and see result for "Mr.", "FirstName6", "LastName6" and "Department head6"
  	And close the browser for SuiteCRM portal 
  	
  @ScheduleMeetingAndInviteMembers
  Scenario Outline: To schedule a meeting and include at least 3 invitees
  	Given Navigate to Activities -> Meetings -> Schedule a Meeting
  	And Enter the details of the meeting "<Subject>"
  	When User Search for members "<Member1>", "<Member2>", "<Member3>" and add them to the meeting and click save
  	Then Navigate to View Meetings page and confirm creation of the meeting "<Subject>" 
  	And close the browser for SuiteCRM portal
  
  Examples:
  | Member1 | Member2 | Member3 | Subject |
  |fname.lname14@test.com | fname.lname15@test.com | fname.lname16@test.com | RandomMeetingSubject4 |
  
  
  @CreatingProduct
  Scenario Outline: Add products using examples table
  	Given Navigate to All -> Products-> Create Product
  	When User enters "<Product Name>", "<Part Number>" and "<Price>" and saves the product
  	Then Verify that the product "<Product Name>" is created
  	And close the browser for SuiteCRM portal
  	
  Examples:
  | Product Name | Part Number | Price |
  | SampleProduct6 | 14102026 | 100 |
  | SampleProduct7 | 14102027 | 150 |
  
  	
  	
  	 

  
  
  	
  	
		  			
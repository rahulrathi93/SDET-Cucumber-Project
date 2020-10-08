@JobBoard_activity
Feature: Performs various operations on Alchemy Jobs portal
  
  @createNewUser
  Scenario: Create a new user
    Given User is on Alchemy jobs portal
    And User logs in to the portal
    When User clicks the menu item Users
    And User locates the Add New button and click on it
    And User fills all the necessary details
    #And click the Add New User button
    Then User is created
    And close the browser
    
   @searchJobs
   Scenario: Searching for jobs using XPath
   	Given User navigates to Alchemy jobs site
   	And User navigates to Jobs page and search for jobs
   	And User filters full time jobs
   	Then User finds the title of the job listing and applies for it
   	And close the browser 
   	
   	@postJob
   	Scenario: Posting a job using parameterization
   	Given User navigates to Alchemy jobs site
   	And User navigates to Post a job page
   	And User logs in to the portal
   	When User posts a job with details "Sample Job Title", "Pune" and "Sample Job Description"
   	And User clicks the submit button
   	Then Job listing "Sample Job Title" is available
   	And close the browser
   	
   	@postJobsUsingExamplesTable
   	Scenario Outline: Posting jobs using examples table
   	Given User navigates to Alchemy jobs site
   	And User navigates to Post a job page
   	And User logs in to the portal
   	When User posts a job with details "<Job Title>", "<Location>" and "<Job Description>"
   	And User clicks the submit button
   	Then Job listing "<Job Title>" is available
   	And close the browser  
   	
   	Examples:
   	|	Job Title | Location | Job Description |
   	| Test Specialist Automation | Pune | Candiate should have minimum 2+ years of experience in Selenium, TestNG |
   	| Application Developer Java | Bangalore | Candiate should have minimum 5+ years of experience in Java |
   	| Application Developer PL/SQL | Kolkata | Candiate should have minimum 4+ years of experience in PL/SQL |  	
   	
   	
   	
   	
   	  
   	  
    
    


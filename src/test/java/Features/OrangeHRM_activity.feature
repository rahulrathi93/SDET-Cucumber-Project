@OrangeHRM_activity
Feature: Performs various operations on Orange HRM portal

  
  Background: Login to Orange HRM portal
  	Given User navigates to orange HRM portal and provides login credentials
  	Then User is logged in
  	
  @createJobVacancy	
  Scenario:	Create a job vacancy
  	Given User navigates to the recruitment page
  	And User clicks on Vacancies menu item and navigates to vacancies page
  	When User clicks on Add button to navigate to the Add Job Vacancy form
  	And User enters the necessary details and saves the vacancy
  	#And User saves the vacancy
  	Then Vacancy is created
  	And close the browser for OrangeHRM portal
  	
  @addCandidateForRecruitment
  Scenario: Adding a candidate for recruitment
  	Given User navigates to the recruitment page
  	When User clicks on Add button to navigate to the Add Candidate form
  	And User enters the necessary details
  	And User uploads resume
  	And User Saves the form
  	Then Confirm candidate entry
  	And close the browser for OrangeHRM portal
  	
  @addMultipleEmployees	
  Scenario Outline: Add multiple employees using an the Examples table
  	Given User navigates to the recruitment page
  	And User finds the PIM option in the Menu and click it
  	And User clicks Add button to add new employee
  	And User checks Create Login Details checkbox
  	When Fill required details in Add Employee form "<Fname>", "<Lname>" and "<Username>"
  	And User saves the information of Add Employee form
  	Then Verify that the employee with "<Fname>" and "<Lname>" is created
  	And close the browser for OrangeHRM portal
  	
  Examples:
  	| Fname | Lname | Username |
  	| First name16 | Last name16 | User16 |
  	| First name17 | Last name17 | User17 |
  	
   @createMultipleVacancies
   Scenario Outline: Creating multiple vacancies using data from an Examples table
   	Given User navigates to the recruitment page
   	And User clicks on Vacancies menu item and navigates to vacancies page
   	When User clicks on Add button to navigate to the Add Job Vacancy form 
   	And User enters the necessary details "<JobTitle>", "<VacancyName>", "<HiringManager>" and saves the vacancy
  	Then Vacancy is created for "<JobTitle>", "<VacancyName>" and "<HiringManager>"
  	And close the browser for OrangeHRM portal
  	
    Examples:
  	| JobTitle | VacancyName | HiringManager |
  	| Android Developer | Sample Android Developer2 | Antu Singh |
  	| Automation Test Engineer | Sample Automation Test Engineer2 | Guruprasad Sawant |	
  	
  	
  	
  	 
  	
  	
  	
  	
  
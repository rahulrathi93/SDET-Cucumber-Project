package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class OrangeHRM_activity_stepDefinition {
	WebDriver driver;
	WebDriverWait wait;
	String empId;
	
	@Given("^User navigates to orange HRM portal and provides login credentials$")
	public void loginToOranageHRM() {
		// Instantiate Chrome driver
		driver = new ChromeDriver();
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.manage().window().maximize();
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		driver.findElement(By.id("btnLogin")).click();	
		
	}
	
	@Then("^User is logged in$")
	public void verifyLogin() {
		
		//driver = new ChromeDriver();
		//driver.findElement(By.id("welcome")).isDisplayed();
		Assert.assertTrue(driver.findElement(By.id("welcome")).isDisplayed());	
		
	}
	
	@Given("^User navigates to the recruitment page$")
	public void NavigateToRecruitmentPage() {
		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();		
		
	}
	
	@And("^User clicks on Vacancies menu item and navigates to vacancies page$")
	public void NavigateToVacanciesPage() {
		
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
	}
	
	@When("^User clicks on Add button to navigate to the Add Job Vacancy form$")
	public void NavigateToAddJobVacancyPage() {
		driver.findElement(By.id("btnAdd")).click();
	}
	
	@And("^User enters the necessary details and saves the vacancy$")
	public void FillAddJobVacancyForm() {
		Select jobTitle = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));
		jobTitle.selectByIndex(3);
		driver.findElement(By.id("addJobVacancy_name")).sendKeys("Sample DevOps Engineer Vacancy1");
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Antu Singh");
		driver.findElement(By.id("btnSave")).click();
			
	}
	
	@Then("^Vacancy is created$")
	public void VerifyCreatedVacancy() throws InterruptedException {
		NavigateToVacanciesPage();
		Select jobTitle = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
		jobTitle.selectByIndex(3);
		
		Select jobVacancy = new Select(driver.findElement(By.id("vacancySearch_jobVacancy")));
		Thread.sleep(1000);
		jobVacancy.selectByVisibleText("Sample DevOps Engineer Vacancy1");
		
		
		Select hiringManager = new Select(driver.findElement(By.id("vacancySearch_hiringManager")));
		Thread.sleep(1000);
		hiringManager.selectByVisibleText("Antu Singh");
		
		driver.findElement(By.id("btnSrch")).click();
		
		String jobVacancy1= driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/form/div[4]/table/tbody/tr/td[2]/a")).getText();
		Assert.assertEquals("Sample DevOps Engineer Vacancy1", jobVacancy1);
		
		
		
	}
	
	@And("^close the browser for OrangeHRM portal$")
	public void closeBrowser() {
		driver.close();
	}
	
	@When("^User clicks on Add button to navigate to the Add Candidate form$")
	public void NavigateToAddCandidateForm() {
		driver.findElement(By.id("btnAdd")).click();
			
	}
	
	@And("^User enters the necessary details$")
	public void FillAddCandidateForm() throws InterruptedException {
		
	driver.findElement(By.id("addCandidate_firstName")).sendKeys("Sample First Name3");
	driver.findElement(By.id("addCandidate_lastName")).sendKeys("Sample Last Name3");
	driver.findElement(By.id("addCandidate_email")).sendKeys("SampleEmail3@email.com");
	
	Select jobVacancy = new Select(driver.findElement(By.id("addCandidate_vacancy")));
	Thread.sleep(1000);
	jobVacancy.selectByVisibleText("Sample DevOps Engineer Vacancy1");
	
	}
	
	@And("^User uploads resume$")
	public void UploadResume() throws InterruptedException {	
	
	WebElement uploadResume = driver.findElement(By.id("addCandidate_resume"));
	//WebElement uploadResume = driver.findElement(By.xpath(".//input[@type='file']"));  
	Thread.sleep(1000);
	//uploadResume.sendKeys("C://Users//RahulRathi//Desktop//SDET//Cucumber//Sample Resume.docx");
	uploadResume.sendKeys("C:/Users/RahulRathi/Desktop/SDET/Cucumber/Sample Resume.docx");
	
	}
	
	@And("^User Saves the form$")
	public void SaveAddCandidateForm() throws InterruptedException {
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(15000);
	}
	
	@Then("^Confirm candidate entry$")
	public void VerifyAddedCandidate() throws InterruptedException {
		driver.findElement(By.id("menu_recruitment_viewCandidates")).click();
		Select applicationStatus = new Select(driver.findElement(By.id("candidateSearch_status")));
		Thread.sleep(1000);
		applicationStatus.selectByIndex(1); 
		
		Select searchJobVacancy = new Select(driver.findElement(By.id("candidateSearch_jobVacancy")));
		Thread.sleep(1000);
		searchJobVacancy.selectByVisibleText("Sample DevOps Engineer Vacancy1");
		
		driver.findElement(By.id("candidateSearch_candidateName")).sendKeys("Sample First Name3 Sample Last Name3");
		driver.findElement(By.id("btnSrch")).click();
		
		String candidateName = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/form/div[4]/table/tbody/tr[1]/td[3]")).getText();
		Assert.assertEquals("Sample First Name3 Sample Last Name3", candidateName);
	}
	
	@And("^User finds the PIM option in the Menu and click it$")
	public void NavigateToPIMOption() {
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
				
	}
	
	@And("^User clicks Add button to add new employee$")
	public void ClickAddButtonToAddNewEmployee() {
		driver.findElement(By.id("btnAdd")).click();
		
	}
	
	@And("^User checks Create Login Details checkbox$")
	public void CheckCreateLoginDetailsCheckbox() {
		driver.findElement(By.id("chkLogin")).click();
		
	}
	
	@When("^Fill required details in Add Employee form \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void FillAddEmployeeForm(String FirstName, String LastName, String Username) {
		
		empId = driver.findElement(By.id("employeeId")).getAttribute("value");
		System.out.println(empId);
		driver.findElement(By.id("firstName")).sendKeys(FirstName);
		driver.findElement(By.id("lastName")).sendKeys(LastName);
		driver.findElement(By.id("user_name")).sendKeys(Username);
		
				
	}
	
	@And("^User saves the information of Add Employee form$")
	public void SaveAddEmployeeForm() throws InterruptedException {
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(5000);
	}
	
	@Then("^Verify that the employee with \"(.*)\" and \"(.*)\" is created$")
	public void VerifyEmployeeIsAdded(String FirstName, String LastName) throws InterruptedException {
		
		driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("empsearch_id")).sendKeys(empId);
		Thread.sleep(1000);
		System.out.println(empId);
		driver.findElement(By.id("searchBtn")).click();
		
		String FirstName1 = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/form/div[4]/table/tbody/tr/td[3]/a")).getText();
		String LastName1 = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/form/div[4]/table/tbody/tr/td[4]/a")).getText();
		
		Assert.assertEquals(FirstName, FirstName1);
		Assert.assertEquals(LastName, LastName1);
		
	}
	
	@And("^User enters the necessary details \"(.*)\", \"(.*)\", \"(.*)\" and saves the vacancy$")
	public void FillAddJobVacancyForm1(String JobTitle, String VacancyName, String HiringManager) {
		
		Select jobTitle = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));
		jobTitle.selectByVisibleText(JobTitle);
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(VacancyName);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(HiringManager);
		driver.findElement(By.id("btnSave")).click();
			
	}
	
	@Then("^Vacancy is created for \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void VerifyCreatedVacancy1(String JobTitle, String VacancyName, String HiringManager) throws InterruptedException {
		NavigateToVacanciesPage();
		Select jobTitle = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
		jobTitle.selectByVisibleText(JobTitle);
		
		Select jobVacancy = new Select(driver.findElement(By.id("vacancySearch_jobVacancy")));
		Thread.sleep(1000);
		jobVacancy.selectByVisibleText(VacancyName);
		
		
		Select hiringManager = new Select(driver.findElement(By.id("vacancySearch_hiringManager")));
		Thread.sleep(1000);
		hiringManager.selectByVisibleText(HiringManager);
		
		driver.findElement(By.id("btnSrch")).click();
		
		String jobVacancy1= driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/form/div[4]/table/tbody/tr/td[2]/a")).getText();
		Assert.assertEquals(VacancyName, jobVacancy1);
	}
	
	
}

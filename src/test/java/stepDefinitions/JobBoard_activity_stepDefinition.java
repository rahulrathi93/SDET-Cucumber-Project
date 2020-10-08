package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class JobBoard_activity_stepDefinition {
	WebDriver driver;
	WebDriverWait wait;
	List<WebElement> JobPosting;
	
	@Given("^User is on Alchemy jobs portal$")
	public void UserIsOnAlchemyJobsPortal(){
        //Create a new instance of the Chrome driver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
                
        //Open the browser and navigate to Alchemy jobs portal
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}
	
	@And("^User logs in to the portal$")
	public void AlchemyJobsLogin(){
		// Enter Username and password
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		// click on the login button
		driver.findElement(By.id("wp-submit")).click();		
		
	}
	
	@When("^User clicks the menu item Users$")
	public void UserClicksMenuItemUsers() throws InterruptedException{
		driver.findElement(By.xpath("//div[@class='wp-menu-name'][contains(.,'Users')]")).click();
		//wait(5000);	
	}
	
	@And("^User locates the Add New button and click on it$")
	public void UserLocatesAddNewButton() {
		driver.findElement(By.className("page-title-action")).click();
	}
	
	@And("^User fills all the necessary details$")
	public void FillAddNewUserDetails() throws Throwable{
		driver.findElement(By.id("user_login")).sendKeys("RandomUser8");
		driver.findElement(By.id("email")).sendKeys("RandomUser8@xyz.com");
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(15000);
		//driver.findElement(By.id("first_name")).sendKeys("Random5");
		//driver.findElement(By.id("last_name")).sendKeys("User5");
		//driver.findElement(By.xpath("//button[@type='button'][contains(.,'Show password')]")).click();
		//wait(5000);
		driver.findElement(By.id("createusersub")).click();
		
	}
	
	@And("^click the Add New User button$")
	public void ClickAddNewUserButton() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("createusersub")).click();
		
	}

	@Then("^User is created$")
	public void checkIfUserIsCreated() {
		
		boolean NewUserCreated = driver.findElement(By.xpath("//p[contains(.,'New user created. Edit user')]")).isDisplayed();
		Assert.assertEquals(true, NewUserCreated);
		driver.findElement(By.id("user-search-input")).sendKeys("RandomUser1@xyz.com");
		driver.findElement(By.id("search-submit")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='email column-email'][contains(.,'RandomUser1@xyz.com')]")).isDisplayed());
		
	}
	
	@And("^close the browser$")
	public void closeBrowser() {
		driver.close();
	}
	
	@Given("^User navigates to Alchemy jobs site$")
	public void OpenAlchemyJobsSite(){
        //Create a new instance of the Chrome driver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
                
        //Open the browser and navigate to Alchemy jobs portal
        driver.get("https://alchemy.hguy.co/jobs/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@And("^User navigates to Jobs page and search for jobs$")
	public void openJobsPage(){
		driver.findElement(By.id("menu-item-24")).click();
		driver.findElement(By.id("search_keywords")).sendKeys("Intelligent Tester");
		driver.findElement(By.id("search_location")).sendKeys("Berlin");
		driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
		
	}
	
	@And("^User filters full time jobs$")
	public void ApplyFullTimeJobFilter() {
		driver.findElement(By.id("job_type_freelance")).click();
		driver.findElement(By.id("job_type_internship")).click();
		driver.findElement(By.id("job_type_part-time")).click();
		driver.findElement(By.id("job_type_temporary")).click();
		driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
		
	}
	
	@Then("^User finds the title of the job listing and applies for it$")
	public void findJobTitle() throws InterruptedException {
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JobPosting = driver.findElements(By.xpath("//*[@id=\"post-7\"]/div/div/ul/li/a/div[1]/h3"));
		for (WebElement job : JobPosting) {
			System.out.println(job.getText());
		}
		
		JobPosting.get(0).click();
		driver.findElement(By.xpath("//input[contains(@value,'Apply for job')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'To apply for this job email your details to abhiram@cklabs.com')]")).isDisplayed());
		
		
	}
	
	@And("^User navigates to Post a job page$")
	public void OpenPostAJob() {
		driver.findElement(By.id("menu-item-26")).click();	
		driver.findElement(By.cssSelector("a.button")).click();
	}
	
	@When("^User posts a job with details \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void EnterJobDetails(String JobTittle, String Location, String JobDesc) {
		driver.findElement(By.id("job_title")).sendKeys(JobTittle);
		driver.findElement(By.id("job_location")).sendKeys(Location);
		driver.switchTo().frame("job_description_ifr");
		//driver.findElement(By.id("job_description_ifr")).sendKeys(JobDesc);
		driver.findElement(By.xpath("/html/body/p")).sendKeys(JobDesc);
    	driver.switchTo().parentFrame();
		driver.findElement(By.cssSelector("input.button:nth-child(4)")).click();
		
		
		
	}
	
	@And("^User clicks the submit button$")
	public void SubmitListing() {
		driver.findElement(By.id("job_preview_submit_button")).click();
		
	}
	@Then("^Job listing \"(.*)\" is available$") 
	public void CheckJobListing(String JobTitle) {
		driver.findElement(By.id("menu-item-24")).click();
		driver.findElement(By.id("search_keywords")).sendKeys(JobTitle);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/article/div/div/form/div[1]/div[4]/input")).click();
		String ActualJobTitle = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")).getText();
		Assert.assertEquals(JobTitle, ActualJobTitle);
		
	}
	
	
}

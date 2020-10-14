package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class SuiteCRM_activity_stepDefinition {
	WebDriver driver;
	//WebDriverWait wait =  new WebDriverWait(driver, 20);
	WebDriverWait wait;
	Actions action;
	
	@Given("^User navigates to Suite CRM portal and provides login credentials$")
	public void loginToSuiteCRM() {
		// Instantiate Chrome driver
		
		driver = new FirefoxDriver();
		
		//driver = new ChromeDriver();
		wait =  new WebDriverWait(driver, 20);
		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().window().maximize();
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("bigbutton")).click();	
		
	}
	
	@Then("^User is logged in to Suite CRM portal$")
	public void verifySuiteCRMLogin() throws InterruptedException {
		
		//driver = new ChromeDriver();
		//driver.findElement(By.id("welcome")).isDisplayed();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[5]/ul/li[5]/button/span[2]")).isDisplayed());	
		
	}
	
	@Given("^Count the number of dashlets on the homepage and print the title of the dashlet$")
	public void countDashlets() {
		List<WebElement> dashlet = driver.findElements(By.xpath("//*/tr/td[1]/h3/span[2]"));
		System.out.println("Number of available dashlets are: " + dashlet.size());
		
		for(WebElement titleName: dashlet)
			System.out.println(titleName.getText());
					
	}
	
	@And("^close the browser for SuiteCRM portal$")
	public void closeBrowserForSuiteCRM() {
		driver.close();
	}
	
	@Given("^Navigate to Sales -> Leads -> Create Lead$")
	public void NavigateToCreateLeadPage() throws InterruptedException {
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tab0")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("grouptab_0")));
		
		/*
		action = new Actions(driver);
		WebElement Sales = driver.findElement(By.id("grouptab_0"));
		action.moveToElement(Sales).click();
		wait.until(ExpectedConditions.visibilityOf(Sales));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("moduleTab_9_Leads")));
		action.moveToElement(driver.findElement(By.id("moduleTab_9_Leads"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]/ul/li[1]/a/div[1]/span")));
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/ul/li[1]/a/div[1]/span")).click();		
		*/
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("grouptab_0"))).build().perform();
		//driver.findElement(By.id("grouptab_0")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("moduleTab_9_Leads"))));
		driver.findElement(By.id("moduleTab_9_Leads")).click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]/ul/li[1]/a/div[2]")));
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/ul/li[1]/a/div[2]")).click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]/ul/li[1]/a/div[2]"))).click();
		
	}
	
	@When("^User fills following details \"(.*)\", \"(.*)\", \"(.*)\" and \"(.*)\" and saves form$")
	public void createLeadForm(String Salutaion, String FirstName, String LastName, String Title ) throws InterruptedException {
		Thread.sleep(2000);
		Select salutation1 = new Select(driver.findElement(By.id("salutation")));
		salutation1.selectByVisibleText(Salutaion);
		driver.findElement(By.id("first_name")).sendKeys(FirstName);
		driver.findElement(By.id("last_name")).sendKeys(LastName);
		driver.findElement(By.id("title")).sendKeys(Title);
		driver.findElement(By.id("SAVE")).click();	
		
		
	}
	
	@Then("^Navigate to View Leads page and see result for \"(.*)\", \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void verifyResultForCreateLead(String Salutaion, String FirstName, String LastName, String Title) throws InterruptedException {
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]/h2")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/ul/li[3]/a/div[1]/span")).click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/div[1]/h2")));
		Thread.sleep(2000);
		
		List<WebElement> availableLeads = driver.findElements(By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr/td[3]/b/a"));
		
		for(WebElement leads: availableLeads) {
			
			if(leads.getText().equals(Salutaion + " " + FirstName + " " + LastName)) {
				System.out.println("Lead is available");
				break;
				
			}
				
		}
	}
	
	@Given("^Navigate to Activities -> Meetings -> Schedule a Meeting$")
	public void NavigateToMeetingsPage() throws InterruptedException {
		//Thread.sleep(2000);
		
		action = new Actions(driver);
		WebElement activities = driver.findElement(By.id("grouptab_3"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("grouptab_3")));
		action.moveToElement(activities).build().perform();
		
		//driver.findElement(By.id("grouptab_3")).click();
		//Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moduleTab_9_Meetings")));		
		driver.findElement(By.id("moduleTab_9_Meetings")).click();
		
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebElement scheduleMeetingBtn = driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(2) > a:nth-child(1) > div:nth-child(2)"));
		
		wait.until(ExpectedConditions.visibilityOf(scheduleMeetingBtn));
		scheduleMeetingBtn.click();
		
		
				
	}
	
	@And("^Enter the details of the meeting \"(.*)\"$")
	public void EnterMeetingDetails(String MeetingSubj) throws InterruptedException {
		//Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
		
		/*
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement sub = driver.findElement(By.id("name"));
		js.executeScript("arguments[0].scrollIntoView();", sub);				
		sub.sendKeys("RandomMeetingSubject");
		
		*/
		
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).sendKeys(MeetingSubj);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='module-title-text'][contains(.,'CREATE')]")));
		driver.findElement(By.id("description")).sendKeys("Random Meeting description1");
		
		
		
	}
	
	@When("^User Search for members \"(.*)\", \"(.*)\", \"(.*)\" and add them to the meeting and click save$")
	public void SearchInviteesAndAddthem(String Invitee1, String Invitee2, String Invitee3) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement emailSearch = driver.findElement(By.id("search_email"));
		js.executeScript("arguments[0].scrollIntoView();", emailSearch);
		emailSearch.sendKeys(Invitee1);
		
		//driver.findElement(By.id("search_email")).sendKeys(Invitee1);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_search")));
		driver.findElement(By.id("invitees_search")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_add_1")));
		driver.findElement(By.id("invitees_add_1")).click();
		
		Thread.sleep(1000);
		emailSearch.clear();
		emailSearch.sendKeys(Invitee2);
		//driver.findElement(By.id("search_email")).clear();
		//driver.findElement(By.id("search_email")).sendKeys(Invitee2);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_search")));
		driver.findElement(By.id("invitees_search")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_add_1")));
		driver.findElement(By.id("invitees_add_1")).click();
		
		Thread.sleep(1000);
		emailSearch.clear();
		emailSearch.sendKeys(Invitee3);
		//driver.findElement(By.id("search_email")).clear();
		//driver.findElement(By.id("search_email")).sendKeys(Invitee3);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_search")));
		driver.findElement(By.id("invitees_search")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_add_1")));
		driver.findElement(By.id("invitees_add_1")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.id("save_and_send_invites_header")).click();
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]/h2")));	
		
		
		
	}
	
	@Then("^Navigate to View Meetings page and confirm creation of the meeting \"(.*)\"$")
	public void VerifyMeeting(String MeetingSubj) {		
		
		driver.findElement(By.cssSelector("li.actionmenulinks:nth-child(3) > a:nth-child(1) > div:nth-child(2)")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> availableMeetings = driver.findElements(By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr/td[4]/b/a"));
		
		for (WebElement meeting: availableMeetings) {
			if( (meeting.getText().equals(MeetingSubj))) {
				System.out.println("Meeting record found");
				break;
				
			}
		}
		
		
	}
	
	@Given("^Navigate to All -> Products-> Create Product$")
	public void NavigateToCreateProduct() {
		action = new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("grouptab_5")));
		action.moveToElement(driver.findElement(By.id("grouptab_5"))).build().perform();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement products = driver.findElement(By.xpath("//a[text()='Products']"));
		wait.until(ExpectedConditions.visibilityOf(products));
		js.executeScript("arguments[0].scrollIntoView();", products);
		products.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".suitepicon-action-create")));
		driver.findElement(By.cssSelector(".suitepicon-action-create")).click();
		
		
	}
	
	@When("^User enters \"(.*)\", \"(.*)\" and \"(.*)\" and saves the product$")
	public void EnterProductDetails(String prodName, String partNum, String Price) {
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
		driver.findElement(By.id("name")).sendKeys(prodName);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("part_number")));
		driver.findElement(By.id("part_number")).sendKeys(partNum);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("price")));
		driver.findElement(By.id("price")).sendKeys(Price);
		
		driver.findElement(By.id("SAVE")).click();
		
		
	}
	
	@Then("^Verify that the product \"(.*)\" is created$")
	public void VerifyProduct(String prodName) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/div[1]/h2")));
		driver.findElement(By.cssSelector(".suitepicon-action-list")).click();
		List<WebElement> availableProd = driver.findElements(By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr/td[3]/b/a"));
		for(WebElement prod : availableProd) {
			if(prod.getText().equals(prodName)) {
				System.out.println("Product is available");
				break;
				
			}
			
		}
		
		
		
	}
	
	

}
	
	
	
	


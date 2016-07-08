package Tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.CommonFunctions;

public class Sweety {
	
	public static WebDriver driver;
	public static String URL = "http://damp-sands-8561.herokuapp.com/";
	
	/* Element Locators */
	By email_ID = By.id("user_email");
	By pwd_ID = By.id("user_password");
	By submit_NAME = By.name("commit");
	
	By message_XPATH = By.xpath(".//*[@id='page-content-wrapper']/div/div[1]");
	By header_XPATH = By.xpath(".//*[@id='page-content-wrapper']/div/div[1]/div/h2");
	By tbodyRows_XPATH = By.xpath(".//table/tbody/tr");
	
	By sweety_link = By.linkText("Sweety");
	By view_link = By.linkText("view");
	By levels_link = By.linkText("Levels"); 
	By add_link = By.linkText("Add new");
	By reports_link = By.linkText("Reports");
	By daily_link = By.linkText("Daily");
	By monthly_link = By.linkText("Monthly");
			
	By year_ID = By.id("entry_recorded_at_1i");
	By month_ID = By.id("entry_recorded_at_2i");
	By day_ID = By.id("entry_recorded_at_3i");
	
	By hour_ID = By.id("entry_recorded_at_4i");
	By minute_ID = By.id("entry_recorded_at_5i");
	
	By entryLevel_ID = By.id("entry_level");
	
	
			
	
	
@Parameters({"email" , "password"})
  @Test(priority=0)
  public void loginTest(String email, String  password) {
	
	  driver.findElement(email_ID).sendKeys(email);
	  driver.findElement(pwd_ID).sendKeys(password);
	  driver.findElement(submit_NAME).submit();
	  
	  String message = driver.findElement(message_XPATH).getText();
	  
	  Assert.assertEquals(message, "Signed in successfully.", message);
	  System.out.println(message);
	
	    
  }
  
  /*@Test(priority=1, enabled=true)
  public void levelEntries()
  {
	  driver.findElement(levels_link).click();
	  
	  String header = driver.findElement(header_XPATH).getText();
	  
	  Assert.assertEquals(header, "Level Entries");
	  System.out.println("Navigated to " + header);
  }
  
  
  @Parameters({"date" , "time", "level"})
  @Test(priority=2,enabled=true)
  public void addLevelEntry(String date, String time, String level)
  {

	  
	  driver.findElement(add_link).click();
	  
	  String d[] = date.split("/");
	 Select year = new Select(driver.findElement(year_ID));
	 year.selectByValue(d[2]);
	 Select month = new Select(driver.findElement(month_ID));
	 month.selectByValue(d[0]);
	 Select day = new Select(driver.findElement(day_ID));
	 day.selectByValue(d[1]);
	 
	
	 String t[]=time.split(":");
	 Select hour = new Select(driver.findElement(hour_ID));
	 hour.selectByValue(t[0]);
	 Select minute = new Select(driver.findElement(minute_ID));
	 minute.selectByValue(t[1]);
	 
	 driver.findElement(entryLevel_ID).sendKeys(level);
	 driver.findElement(submit_NAME).submit();
	 
	 String message = driver.findElement(message_XPATH).getText();
	 Assert.assertEquals(message, "Entry was successfully created.",message);
	 System.out.println(message);
	 
	  
  }
  
  @Parameters({"date" , "time", "level"})
  @Test(priority=3, enabled = true)
  public void validateLevelEntries(String date, String time, String level){
	  
	 // Date and time in formats as given in Level Entries table 
	 String time12HR = CommonFunctions.time12HR(time);
	 String dateYY = CommonFunctions.yearYY(date);
	 
	 int noOfRecords=0;
	 boolean flag = false;
	 
	  List <WebElement> entries = driver.findElements(tbodyRows_XPATH);
	  for(WebElement we : entries)
	  {		
		
		  String rowText = we.getText().replace("Delete", "");
		  
		  if(rowText.contains(dateYY))
		  {	
			 noOfRecords++;
			 Assert.assertTrue(noOfRecords<=4, "There are more than four entries for the given date");
			  
			  if(rowText.contains(time12HR))
			  {
				  if(rowText.contains(level))
				  {		
					  flag = true;
					  System.out.println("Record found : " + rowText);  
					  break;
				  }
			  }
		  }
	  }	 
	  Assert.assertEquals(flag, true, "Added Level Entry Record not found in table");
  }
  
  @Test(priority=4, enabled = true)
  public void levelReports()
  {
	  
	  driver.findElement(reports_link).click();
	  
	  String header = driver.findElement(header_XPATH).getText();
	  Assert.assertEquals(header, "Level Reports");
	  System.out.println("Navigated to " + header);
  }
  
  @Parameters({"timePeriod_dailyReport"})
  @Test(priority=5, enabled= true)
  public void viewDailyReports(String timePeriod_dailyReport)
  {  System.out.println("daily");
	  CommonFunctions.validateReports(timePeriod_dailyReport, driver);
  }
  
  @Parameters({"timePeriod_monthlyReport"})
  @Test(priority=6, enabled = true)
  public void viewMonthlyReports(String timePeriod_monthlyReport)
  {	System.out.println("monthly");
	  driver.findElement(monthly_link).click();
	  CommonFunctions.validateReports(timePeriod_monthlyReport, driver);
  }
  

@Parameters({"date" , "time", "level"})
  @Test(priority = 10, enabled=false)
  public void deleteLevelEntry(String date, String time, String level)
  {
	    
	  Date and time in formats as given in Level Entries table 
	 String time12HR = CommonFunctions.time12HR(time);
	 String dateYY = CommonFunctions.yearYY(date);
	 
	 int noOfRecords=0;
	 boolean flag = false;
	 
	 List <WebElement> entries = driver.findElements(tbodyRows_XPATH);
	  int rowSize = entries.size();
	  for(int i=1;i<=rowSize; i++)
	  {
		  String rowText = driver.findElement(By.xpath(".//table/tbody/tr["+i+"]")).getText();
		
		 // String rowText = we.getText().replace("Delete", "");
		  
		  if(rowText.contains(dateYY))
		  {	
			 noOfRecords++;
			 Assert.assertTrue(noOfRecords<=4, "There are more than four entries for the given date");
			  
			  if(rowText.contains(time12HR))
			  {
				  if(rowText.contains(level))
				  {		
					  driver.findElement(By.xpath(".//table/tbody/tr["+i+"]//a")).click();
					 driver.switchTo().alert().accept();
					  System.out.println("Record found and deleted: " + rowText);  
					  flag = true;
					  break;
				  }
			  }
		  }
	  }	 
	  Assert.assertEquals(flag, true, "Record not found/deleted in table");
  }*/
  
 
  @BeforeTest
  public void beforeTest() {
	  
	  driver = new FirefoxDriver();
	  driver.get(URL);
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }
  
  

  @AfterTest
  public void afterTest() {
	  
	  driver.quit();
  }

}

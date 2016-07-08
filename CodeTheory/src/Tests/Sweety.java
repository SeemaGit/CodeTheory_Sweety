package Tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.CommonFunctions;
import utilities.ObjRep;

public class Sweety {
	
	public static WebDriver driver;
	public static String URL = "http://damp-sands-8561.herokuapp.com/";
	public static WebDriverWait wait;

  @Parameters({"email" , "password"})
  @Test()
  public void loginPositive(String email, String  password) 
  {
	  driver.findElement(ObjRep.email_ID).sendKeys(email);
	  driver.findElement(ObjRep.pwd_ID).sendKeys(password);
	  driver.findElement(ObjRep.submit_NAME).submit();	  
	  String message = driver.findElement(ObjRep.message_XPATH).getText();
	  Assert.assertEquals(message, "Signed in successfully.", message);
	  System.out.println(message);    
  }
  
  @Parameters({"date" , "time", "level"})
  @Test()
  public void addLevelEntryPositive(String date, String time, String level)
  {	
	 CommonFunctions.levelEntries(driver, wait);
	 driver.findElement(ObjRep.add_LINK).click();  
	 String d[] = date.split("/");
	 Select year = new Select(driver.findElement(ObjRep.year_ID));
	 year.selectByValue(d[2]);
	 Select month = new Select(driver.findElement(ObjRep.month_ID));
	 month.selectByValue(d[0]);
	 Select day = new Select(driver.findElement(ObjRep.day_ID));
	 day.selectByValue(d[1]);
	 String t[]=time.split(":");
	 Select hour = new Select(driver.findElement(ObjRep.hour_ID));
	 hour.selectByValue(t[0]);
	 Select minute = new Select(driver.findElement(ObjRep.minute_ID));
	 minute.selectByValue(t[1]);
	 driver.findElement(ObjRep.entryLevel_ID).sendKeys(level);
	 driver.findElement(ObjRep.submit_NAME).submit();
	 String message = driver.findElement(ObjRep.message_XPATH).getText();
	 System.out.println(message);
	 Assert.assertEquals(message, "Entry was successfully created.",message);
  }
  
  @Parameters({"date" , "time", "level"})
  @Test()
  public void validateLevelEntries(String date, String time, String level)
  {
	 CommonFunctions.levelEntries(driver, wait);
	 String time12HR = CommonFunctions.time12HR(time);
	 String dateYY = CommonFunctions.yearYY(date);
	 int noOfRecords=0;
	 boolean flag = false;
	  List <WebElement> entries = driver.findElements(ObjRep.tbodyRows_XPATH);
	  for(WebElement we : entries)
	  {		
		  String rowText = we.getText().replace("Delete", "");
		  if(rowText.contains(dateYY))
		  {	
			 noOfRecords++;
			 Assert.assertTrue(noOfRecords<=4, "There are more than four entries for the given date");	  
			  if(rowText.contains(time12HR))
			  {
				  if(rowText.contains(CommonFunctions.validateInteger(level)))
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
  
  @Parameters({"timePeriod_dailyReport"})
  @Test()
  public void viewDailyReports(String timePeriod_dailyReport) throws InterruptedException
  {  	
	  CommonFunctions.levelReports(driver, wait);
	  driver.findElement(ObjRep.daily_LINK).click();
	  wait.until(ExpectedConditions.visibilityOfElementLocated(ObjRep.reportDaily_XPATH));
	  System.out.println("Daily Report");
	  CommonFunctions.validateReports(timePeriod_dailyReport, driver);
  }
  
  @Parameters({"timePeriod_monthlyReport"})
  @Test()
  public void viewMonthlyReports(String timePeriod_monthlyReport) throws InterruptedException
  {	
	  CommonFunctions.levelReports(driver, wait);
	  driver.findElement(ObjRep.monthly_LINK).click();
	  wait.until(ExpectedConditions.visibilityOfElementLocated(ObjRep.reportMonthly_XPATH));
	  System.out.println("Monthly Report");
	  CommonFunctions.validateReports(timePeriod_monthlyReport, driver);
  }
  

  @Parameters({"date" , "time", "level"})
  @Test()
  public void deleteLevelEntry(String date, String time, String level)
  {
	 CommonFunctions.levelEntries(driver, wait); 
	 String time12HR = CommonFunctions.time12HR(time);
	 String dateYY = CommonFunctions.yearYY(date);
	 int noOfRecords=0;
	 boolean flag = false;
	 List <WebElement> entries = driver.findElements(ObjRep.tbodyRows_XPATH);
	  int rowSize = entries.size();
	  for(int i=1;i<=rowSize; i++)
	  {
		  String rowText = driver.findElement(By.xpath(".//table/tbody/tr["+i+"]")).getText();  
		  if(rowText.contains(dateYY))
		  {	
			 noOfRecords++;
			 Assert.assertTrue(noOfRecords<=4, "There are more than four entries for the given date");
			  if(rowText.contains(time12HR))
			  {
				  if(rowText.contains(CommonFunctions.validateInteger(level)))
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
  }
@Test
public void defaultSelectedDateTime()
{
	
	CommonFunctions.levelEntries(driver, wait);
	driver.findElement(ObjRep.add_LINK).click();  
	Select year = new Select(driver.findElement(ObjRep.year_ID));
	Select month = new Select(driver.findElement(ObjRep.month_ID));
	Select day = new Select(driver.findElement(ObjRep.day_ID));
	Select hour = new Select(driver.findElement(ObjRep.hour_ID));
	Select minute = new Select(driver.findElement(ObjRep.minute_ID));
	String defaultDateTime[] = {year.getFirstSelectedOption().getText(),month.getFirstSelectedOption().getText(),
			 day.getFirstSelectedOption().getText(),hour.getFirstSelectedOption().getText(),minute.getFirstSelectedOption().getText()};
	 Assert.assertEquals(defaultDateTime, CommonFunctions.currentDate());
}
/* Negative Test Scenarios */

  @Parameters({"email" , "password"})
  @Test()
  public void loginNegative(String email, String  password) 
  {
	driver.findElement(ObjRep.email_ID).sendKeys(email);
	driver.findElement(ObjRep.pwd_ID).sendKeys(password);
	driver.findElement(ObjRep.submit_NAME).submit();
	String message = driver.findElement(ObjRep.message_XPATH).getText();  
	Assert.assertEquals(message, "Invalid email or password.", message);
	System.out.println(message);	    
  }
  
  @Parameters({"date" , "time", "level"})
  @Test()
  public void addLevelEntryNegative(String date, String time, String level)
  {
	 CommonFunctions.levelEntries(driver, wait);
	 driver.findElement(ObjRep.add_LINK).click(); 
	 String d[] = date.split("/");
	 Select year = new Select(driver.findElement(ObjRep.year_ID));
	 year.selectByValue(d[2]);
	 Select month = new Select(driver.findElement(ObjRep.month_ID));
	 month.selectByValue(d[0]);
	 Select day = new Select(driver.findElement(ObjRep.day_ID));
	 day.selectByValue(d[1]);
	 String t[]=time.split(":");
	 Select hour = new Select(driver.findElement(ObjRep.hour_ID));
	 hour.selectByValue(t[0]);
	 Select minute = new Select(driver.findElement(ObjRep.minute_ID));
	 minute.selectByValue(t[1]); 
	 driver.findElement(ObjRep.entryLevel_ID).sendKeys(level);
	 driver.findElement(ObjRep.submit_NAME).submit();
	 String message = driver.findElement(ObjRep.message_XPATH).getText();
	 System.out.println(message);
	 Assert.assertEquals(message, "Maximum entries reached for this date.",message);	  
}

/* BeforeTest and AfterTest methods */ 
  @BeforeTest
  public void beforeTest() 
  {
	  driver = new FirefoxDriver();
	  driver.get(URL);
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  wait = new WebDriverWait(driver, 20);
  }
  
  @AfterTest
  public void afterTest() 
  {
	  driver.quit();
  }

}

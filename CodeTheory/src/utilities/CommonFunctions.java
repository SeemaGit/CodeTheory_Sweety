package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class CommonFunctions {
	
	 /** This method validates the navigation to Level Entries page
	 * @param driver
	 */
	public static void levelEntries(WebDriver driver, WebDriverWait wait)
	  {
		  driver.findElement(ObjRep.levels_LINK).click(); 
		  wait.until(ExpectedConditions.textToBePresentInElementLocated(ObjRep.header_XPATH, "Level Entries"));
		  String header = driver.findElement(ObjRep.header_XPATH).getText();
		  System.out.println("Navigated to " + header); 
	  }
	
	 /** This method validates the navigation to Level Reports page
	 * @param driver
	 */
	public static void levelReports(WebDriver driver, WebDriverWait wait)
	  {
		  driver.findElement(ObjRep.reports_LINK).click();		  
		  wait.until(ExpectedConditions.textToBePresentInElementLocated(ObjRep.header_XPATH, "Level Reports"));
		  String header = driver.findElement(ObjRep.header_XPATH).getText();
		  System.out.println("Navigated to " + header);	
	  }
	/** This method validates single integer 
	 * @param level
	 * @return
	 */
	public static String validateInteger(String level)
	{
		Pattern p = Pattern.compile("[^0-9]");
		String b = String.valueOf(level.charAt(0));
		 String str[]=p.split(level,2);
		 if(Pattern.matches("[^0-9]",b))
			{
				str[0]=Integer.toString(0);
			}
		
		return str[0];
	}

	/** This method validates reports for a given time period and checks if max, min, avg readings are included 
	 * @param timePeriod in below formats 
	 * YYYY-MM-DD for daily reports
	 * YYYY-Mmm for monthly reports
	 * @throws InterruptedException 
	 */
	public static void validateReports(String timePeriod, WebDriver driver) throws InterruptedException
	  {
		  boolean flag = false;
		  List <WebElement> level = driver.findElements(By.xpath(".//table/tbody/tr"));
		  int rowSize = level.size();
		  for(int i=1;i<=rowSize; i++)
		  {
			  String rowValue = driver.findElement(By.xpath(".//table/tbody/tr["+i+"]")).getText();
			  if(rowValue.contains(timePeriod))
			  {	  
				  	System.out.println(rowValue);
				  	List <WebElement> levelData = driver.findElements(By.xpath(".//table/tbody/tr["+i+"]/td"));
				  	int colSize = levelData.size();		  
				  	for(int j=1;j<=colSize;j++)
				  	{
				  		String colValue = driver.findElement(By.xpath(".//table/tbody/tr["+i+"]/td["+j+"]")).getText();			
				  		Assert.assertNotEquals(colValue,"","Either one or all of Maximum, Minumum and Average readings is not present for the time period");
				  	} 
				  	flag=true;
				  	break;
			  }	  
		  } 
		  Assert.assertEquals(flag, true, "Reading for given time period not found");
	  }
	  /** This method returns time in 12hour format as used in reports
	 * @param time
	 * @return
	 */
	public static String time12HR(String time)
	  {
		  String timeSplit[] = time.split(":");
		  int hour = Integer.parseInt(timeSplit[0]);
		  String time12HR = null;
		  if(hour==00)
		  {
			  time12HR="12:"+timeSplit[1]+" AM";
		  }
		  else if(hour<12){
			  
			  time12HR=time.concat(" AM");	
		  }
		  else if (hour==12)
		  {
			  time12HR = timeSplit[0]+":"+timeSplit[1]+" PM";
		  }
		  else
		  {
			  time12HR = String.valueOf(hour-12)+":"+timeSplit[1]+" PM";  //.concat(":").concat(timeSplit[1]).concat(" PM");
			  
		  }
		  return time12HR;
	  }
	  

	  /** This method returns date with year in yy format, used in Level Entries
	 * @param date
	 * @return
	 */
	public static String yearYY(String date)
	  {
		  String year = date.replace("2016", "16");
		  return year;
	  }
	
	public static String[] currentDate()
	{
		DateFormat df = new SimpleDateFormat("YYYY MMMM d HH mm");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		String date = df.format(dateobj);
		String splitDate[] = date.split(" ");
		return splitDate;
	}
	
}

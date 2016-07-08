package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonFunctions {

	/** This method validates reports for a given time period and checks if max, min, avg readings are included 
	 * @param timePeriod in below formats 
	 * YYYY-MM-DD for daily reports
	 * YYYY-Mmm for monthly reports
	 */
	public static void validateReports(String timePeriod, WebDriver driver)
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
		  if(hour<12){
			  
			  time12HR=time.concat(" AM");	
		  }
		  else
		  {
			  time12HR = String.valueOf(Integer.parseInt(timeSplit[0])-12).concat(":").concat(timeSplit[1]).concat(" PM");
			  
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
}

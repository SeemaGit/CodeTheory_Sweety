package utilities;

import org.openqa.selenium.By;

public class ObjRep {

	 /*Element Locators */
		public static By email_ID = By.id("user_email");
		public static By pwd_ID = By.id("user_password");
		public static By submit_NAME = By.name("commit");
		
		public static By message_XPATH = By.xpath(".//*[@id='page-content-wrapper']/div/div[1]");
		public static By header_XPATH = By.xpath(".//*[@id='page-content-wrapper']/div/div[1]/div/h2");
		public static By tbodyRows_XPATH = By.xpath(".//table/tbody/tr");
		
		public static By sweety_LINK = By.linkText("Sweety");
		public static By view_LINK = By.linkText("view");
		public static By levels_LINK = By.linkText("Levels"); 
		public static By add_LINK = By.linkText("Add new");
		public static By reports_LINK = By.linkText("Reports");
		public static By daily_LINK = By.linkText("Daily");
		public static By monthly_LINK = By.linkText("Monthly");
				
		public static By year_ID = By.id("entry_recorded_at_1i");
		public static By month_ID = By.id("entry_recorded_at_2i");
		public static By day_ID = By.id("entry_recorded_at_3i");
		
		public static By hour_ID = By.id("entry_recorded_at_4i");
		public static By minute_ID = By.id("entry_recorded_at_5i");
		
		public static By entryLevel_ID = By.id("entry_level");
		public static By reportDaily_XPATH = By.xpath(".//h3[contains(text(),'Daily Report as of')]");
		public static By reportMonthly_XPATH = By.xpath(".//h3[contains(text(),'Monthly Report as of')]");
		
			
}

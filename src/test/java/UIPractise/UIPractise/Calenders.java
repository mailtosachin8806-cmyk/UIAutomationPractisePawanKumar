package UIPractise.UIPractise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Calenders {
	
	@Test
	public void handleCalenders() {
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/datepicker/");
		
		driver.switchTo().frame(0);
		
		//Select a date
		
		driver.findElement(By.xpath("//input[@id='datepicker']")).click(); //Opens date picker
		
		//Method2 :- Using date picker
		String year = "2028";
		String month = "October";
		String date = "5";
		
		//Select month and year
		
		while(true) {
		String currentMonth =	driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText(); //current month
		String currentYear =	driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText(); //current year
		
		if(currentMonth.equals(month) && currentYear.equals(year)) {
			
			break;
		}
		
		driver.findElement(By.xpath("//a[@title='Next']")).click(); //next button
		
		}
		
		//Select a date
		
	List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr/td/a"));
		
		for(WebElement dates: allDates) {
			if(dates.getText().equalsIgnoreCase(date))
			{
				dates.click();
				break;
			}
		}
		
		
		driver.close();
		
	}

}

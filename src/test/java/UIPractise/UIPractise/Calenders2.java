package UIPractise.UIPractise;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Calenders2 {

	@Test
	public void selectDateDropdown() {
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		
		//Input DOB
		String requiredYear = "2021";
		String requiredMonth = "October";
		String requiredDate = "5";
		
		//Switch to frame
		driver.switchTo().frame(1);
		driver.findElement(By.xpath("//input[@id='start-date']")).click();
		driver.findElement(By.xpath("//input[@id='start-date']")).click();
		//driver.switchTo().frame("");
		
		
		
		
		
		
		
		
		
		
		
	}
}

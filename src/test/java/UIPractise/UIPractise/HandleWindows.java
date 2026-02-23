package UIPractise.UIPractise;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HandleWindows {
	ChromeDriver driver = new ChromeDriver();

	@Test
	public void handleMultipleWindows() {

		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		System.out.println("The title first page is:" + driver.getTitle());

		driver.findElement(By.xpath("//button[text()='New Tab']")).click();

		// Multiple window will be open

		List<String> ids = new ArrayList(driver.getWindowHandles());
		driver.switchTo().window(ids.get(1)); // Switch to next page
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Selenium");
		System.out.println("The title second page is:" + driver.getTitle());

		// Home Page
		driver.switchTo().window(ids.get(0));
		WebElement name = driver.findElement(By.xpath("//input[@id='name']"));
		name.sendKeys("Home Page");

		System.out.println("The text of home page is " + name.getText());
		driver.quit();

	}
	
	@Test
	public void TabsAndWindows() {
		
		driver.get("https://www.opencart.com/index.php?route=cms/demo");
		
		//Selenium 4.0 version 
	//	driver.switchTo().newWindow(WindowType.TAB); //Tab will be open
		driver.switchTo().newWindow(WindowType.WINDOW); //New Window will be open
		driver.get("https://www.orangehrm.com/");
		
		driver.quit();

	}

}

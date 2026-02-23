package UIPractise.UIPractise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class JavaScriptExecutor {
	/*
	 * JavascriptExecutor used when we get a ElementInterception exception WebDriver
	 * If i initailize the driver with WebDriver interface
	 * WebDriver driver = new ChromeDriver(); 
	 * JavascriptExecutor js =(JavascriptExecutor)driver;
	 */
	ChromeDriver driver = new ChromeDriver();

	@Test(description = "Passinng the text into inputBox - alternate of send keys")
	public void handlePageScrolling() {

		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();

		//Passinng the text into inputBox - alternate of send keys
		WebElement inputBox = driver.findElement(By.xpath("//input[@id='name']"));
		JavascriptExecutor js = driver;
		js.executeScript("arguments[0].setAttribute('value','Sachin')", inputBox);

		
		//Click radio button through javascript	
		WebElement radioBtn = driver.findElement(By.xpath("//input[@id='male']"));
		js.executeScript("arguments[0].click()", radioBtn);
		
		driver.quit();
	}
	
	
	@Test(description="Page Scrolling Bar")
	public void pageScrolling() {
		
		
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		
		JavascriptExecutor js = driver;
		
		//1) Scroll down the page by pixel number
		js.executeScript("window.scrollBy(0,400)", "");
		System.out.println(js.executeScript("return window.pageYOffset;"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//2) Scroll the page till the element is visible
		
		WebElement ele = driver.findElement(By.xpath("//h2[text()='Dynamic Web Table']"));
		js.executeScript("arguments[0].scrollIntoView();", ele);
		System.out.println(js.executeScript("return window.pageYOffset;"));
		
		//3) Scroll till bottom of the page
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		System.out.println(js.executeScript("return window.pageYOffset;"));
		
		//4) Scrolling upto initial position
		js.executeScript(("window.scrollBy(0,-document.body.scrollHeight)"));
		System.out.println(js.executeScript("return window.pageYOffset;"));
		
		driver.close();
		
	}
	
	@Test(description="Handle zoomIn and ZoomOut by javascript")
	public void ZoomInZoomOut() throws InterruptedException {
		
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.manage().window().minimize();
		Thread.sleep(5000);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
		JavascriptExecutor js = driver;
		js.executeScript("document.body.style.zoom='50%'"); //Set zoom level 50%
		
		Thread.sleep(5000);
		js.executeScript("document.body.style.zoom='80%'"); //Set zoom level 50%
		Thread.sleep(5000);
		
		driver.close();
		
	}
	
}

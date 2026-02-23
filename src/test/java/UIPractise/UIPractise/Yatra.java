package UIPractise.UIPractise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Yatra {
	
	WebDriver driver;
	WebDriverWait wait ;
	
	@Test
	public void habdleYatraCalender() throws InterruptedException
	{
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://www.yatra.com/");
		
		By popupCancelLocator= By.xpath("(//img[@alt='cross'])[1]");
		
		if(driver.findElement(popupCancelLocator).isDisplayed()) {
			driver.findElement(popupCancelLocator).click();
		}
		
		By departureDateButtonLocator = By.xpath("//div[@aria-label='Departure Date inputbox' and @role='button']");
		WebElement 	departureDateButton = wait.until(ExpectedConditions.elementToBeClickable(departureDateButtonLocator));
		departureDateButton.click();
		
		By calendarMonthsLocator = By.xpath("//div[contains(@class,'react-datepicker__month-container')]");
		
		List<WebElement> calendarMonthList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarMonthsLocator));
		
		System.out.println(calendarMonthList.size());
		
		WebElement currentMonthCalendar = calendarMonthList.get(1); //Current Month Calendar
	//	WebElement currentMonthCalendar = calendarMonthList.get(1); //Next Month Calendar
		Thread.sleep(3000);
		By priceLocator = By.xpath(".//span[contains(@class,'custom-day-content')]");
		List<WebElement>   currentMonthPriceLocator = currentMonthCalendar.findElements(priceLocator);
		
		int lowestPrice = Integer.MAX_VALUE;
		WebElement priceElement = null;
		for(WebElement price : currentMonthPriceLocator)
		{
		//	System.out.println(price.getText());
			
			//Tell me the lowest price!!
			String priceString = price.getText();
			if(priceString.length()>0) {
			priceString= priceString.replace("₹", "").replace(",", "");
			
			int priceInt = Integer.parseInt(priceString);
			if(priceInt < lowestPrice)
			{
				lowestPrice = priceInt;
				priceElement = price;
			}
		}
		}
		
		System.out.println(lowestPrice);
		WebElement dateElement	= priceElement.findElement(By.xpath(".//../.."));
		System.out.println(dateElement.getAttribute("aria-label"));
		
		
		
		
		driver.quit();
			
		
	}

}

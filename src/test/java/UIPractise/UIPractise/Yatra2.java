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

public class Yatra2 {
	/*
	 * https://github.com/jatin99/Yatra-Automation-Solution
	 */
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
		
		
		WebElement currentMonthCalendar = selectTheMonthFromCalendar(wait, 0); //Current Month
		WebElement nextMonthCalendar = selectTheMonthFromCalendar(wait,1); //Next month
		
		Thread.sleep(3000);
		String lowestPriceForCurrentMonth = getMeLowestPrice(currentMonthCalendar);
		String lowestPriceForNextMonth = getMeLowestPrice(nextMonthCalendar);
		System.out.println(lowestPriceForCurrentMonth);
		System.out.println(lowestPriceForNextMonth);
		compareTwoMonthsPrices(lowestPriceForCurrentMonth, lowestPriceForNextMonth);
		driver.quit();
			
		
	}
	
		public static WebElement selectTheMonthFromCalendar(WebDriverWait wait,int index) {
			
		By calendarMonthsLocator = By.xpath("//div[contains(@class,'react-datepicker__month-container')]");
		
		List<WebElement> calendarMonthList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarMonthsLocator));
		
		System.out.println(calendarMonthList.size());
		
		WebElement currentMonthCalendar = calendarMonthList.get(index); //Current Month Calendar
		return currentMonthCalendar;
	}
		
		public static String getMeLowestPrice(WebElement currentMonthCalendar) {
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
			String result = dateElement.getAttribute("aria-label") + "----Price is Rs" + lowestPrice;
			return result;
		}
		
		public static void compareTwoMonthsPrices(String currentMonthPrice , String nextMonthPrice )
		{
			int currentMonthRSIndex = currentMonthPrice.indexOf("RS");
			int nextMonthRSIndex= nextMonthPrice.indexOf("Rs");
			
//			String currentPrice = currentMonthPrice.substring(currentMonthRSIndex + 2);
//			String nextPrice = nextMonthPrice.substring(nextMonthRSIndex + 2);
			
			String currentPrice =currentMonthPrice.split("Rs")[1];
			String nextPrice =nextMonthPrice.split("Rs")[1];
					
			int current = Integer.parseInt(currentPrice);
			int next = Integer.parseInt(nextPrice);
			
			if(current<next)
			{
				System.out.println("The lowest price for the two months is " + current);
			}
			else if(current == next)
			{
				System.out.println("Price is same for both months! Choose whatever you prefer!!");
			}
			else
			{
				System.out.println("The lowest price for the two months is" + next);
			}
		}

}

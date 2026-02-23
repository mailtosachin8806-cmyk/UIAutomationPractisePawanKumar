package UIPractise.UIPractise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RedBusAutomation2 {

	/*
	 * Automate RedBus Search Functionality
	 */
	WebDriver driver;
	WebDriverWait wait;

	@Test(description = "Automate RedBus Search Functionality")
	public void bookTickenInRedBus() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		By sourceButtonLocator = By.xpath("(//div[contains(@class,'srcDestWrapper')])[1]");
		WebElement sourceButton = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceButtonLocator));
		sourceButton.click();

		By searchSuggestionSectionLocator = By.xpath("//div[contains(@class,'searchSuggestionWrapper')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionSectionLocator));

		selectBoardingLocation(driver, wait, "Mumbai"); // For location
		selectDepartureLocation(driver, wait, "Pune"); // To location

		By searchButtonLocator = By.xpath("//button[contains(@class,'searchButtonWrapper')]");
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
		searchButton.click();

		//Thread.sleep(5000);
		By tuppleWrapperLocator = By.xpath("//li[contains(@class,'tupleWrapper')]"); // Found the row locator
		wait.until(ExpectedConditions.visibilityOfElementLocated(tuppleWrapperLocator));
		By primoButtonLocator = By.xpath("//div[contains(text(),'Primo Bus')]");
		WebElement primoButton = wait.until(ExpectedConditions.elementToBeClickable(primoButtonLocator));
		primoButton.click();

		By sleeperButtonLocator = By.xpath("//div[contains(text(),'SLEEPER')]");
		WebElement sleeperButton = wait.until(ExpectedConditions.elementToBeClickable(sleeperButtonLocator));
		sleeperButton.click();

		By subTitleLocator = By.xpath("//span[contains(@class,'subtitle')]");
		WebElement subTitle = null;
		if (wait.until(ExpectedConditions.textToBePresentInElementLocated(subTitleLocator, "buses"))) {
			subTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitleLocator));
		}

		System.out.println(subTitle.getText());

		By busesNameLocator = By.xpath(".//div[contains(@class,'travelsName')]"); // Bus name locator
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		List<WebElement> rowList = wait
//				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
		// Initially there will be a set of rows present!rowList --> 10
	//	System.out.println("Total number of buses: " + rowList.size());
//
//		for (WebElement row : rowList) {
//			// Transverse each row one by one
//			System.out.println(row.findElement(busesNameLocator).getText());
//		}//First 10 buses name will printed!!

		// To scroll a webDriver
		// Next set of Buses we need to scroll down to 3rd last elements
		
//		js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth'})", rowList.get(rowList.size() - 3));
//
//		
//		List<WebElement> newRowList = wait
//				.until(ExpectedConditions.numberOfElementsToBeMoreThan(tuppleWrapperLocator, rowList.size()));
//		System.out.println("Total nummber of buses: " + newRowList.size());
		
		while(true) { //Lazy loading
			//Get the rows from the pages
			List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
			List<WebElement> endOfList = driver.findElements(By.xpath("//span[contains(text(),'End of list')]"));
			
			if(!endOfList.isEmpty())
			{
				break; //Loop!! exit conditions from the while loop
			}
			
		//	js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth'})", rowList.get(rowList.size() - 3));
			 js.executeScript("window.scrollBy(0,500)");
			
		}
		
		List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
		for (WebElement row : rowList) {
			String busName = row.findElement(busesNameLocator).getText();
			System.out.println(busName);
		}
	
		System.out.println("Total number of Buses Loaded with Primo and SEATER Filter " + rowList.size());
		
		driver.quit();

	}

	public static void selectBoardingLocation(WebDriver driver, WebDriverWait wait, String BoardingCityLocation) {
		WebElement searchTextBoxElement = driver.switchTo().activeElement(); // Give me that textbox!!
		searchTextBoxElement.sendKeys(BoardingCityLocation);

		// By searchCategoryLocator =
		// By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> searchList = wait.until(ExpectedConditions
				.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class,'searchCategory')]"), 2));

		System.out.println(searchList.size());

		WebElement locationSearchResult = searchList.get(0);
		// Chaning of webelement
		By locationNameLocator = By.xpath("//div[contains(@class,'listHeader')]");
		List<WebElement> locationList = locationSearchResult.findElements(locationNameLocator);
		System.out.println(locationList.size());
		for (WebElement location : locationList) {
			String lName = location.getText();

			if (lName.equalsIgnoreCase(BoardingCityLocation)) {
				location.click();
				break;
			}
		}
	}

	public static void selectDepartureLocation(WebDriver driver, WebDriverWait wait, String DepartureCityLocation) {
		// Focus on To section
		WebElement toTextBoxElement = driver.switchTo().activeElement();
		toTextBoxElement.sendKeys(DepartureCityLocation);

		List<WebElement> toSearchList = wait.until(ExpectedConditions
				.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class,'searchCategory')]"), 2));

		System.out.println(toSearchList.size());

		WebElement toLocationCategory = toSearchList.get(0);
		By toLocationNameLocator = By.xpath("//div[contains(@class,'listHeader')]");
		List<WebElement> toLocationList = toLocationCategory.findElements(toLocationNameLocator);
		System.out.println(toLocationList.size());
		for (WebElement toLocation : toLocationList) {
			String toLName = toLocation.getText();

			if (toLName.equalsIgnoreCase(DepartureCityLocation)) {
				toLocation.click();
				break;
			}
		}

	}

}

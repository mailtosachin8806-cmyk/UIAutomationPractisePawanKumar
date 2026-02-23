package UIPractise.UIPractise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RedBus {

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
		WebElement searchTextBoxElement = driver.switchTo().activeElement(); // Give me that textbox!!
		searchTextBoxElement.sendKeys("Mumbai");

		// By searchCategoryLocator =
		// By.xpath("//div[contains(@class,'searchCategory')]");)
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

			if (lName.equalsIgnoreCase("Mumbai")) {
				location.click();
				break;
			}
		}

		// Focus on To section
		WebElement toTextBoxElement = driver.switchTo().activeElement();
		toTextBoxElement.sendKeys("Pune");

		List<WebElement> toSearchList = wait.until(ExpectedConditions
				.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class,'searchCategory')]"), 2));

		System.out.println(toSearchList.size());

		WebElement toLocationCategory = toSearchList.get(0);
		By toLocationNameLocator = By.xpath("//div[contains(@class,'listHeader')]");
		List<WebElement> toLocationList = toLocationCategory.findElements(toLocationNameLocator);
		System.out.println(toLocationList.size());
		for (WebElement toLocation : toLocationList) {
			String toLName = toLocation.getText();

			if (toLName.equalsIgnoreCase("Pune")) {
				toLocation.click();
				break;
			}
		}

		driver.quit();

	}

}

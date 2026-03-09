package JavaStream;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class paginationJavaStream {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Test (description = "Sorting in a webpage table")
	public void handlePagination()
	{
		driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		//Click on column
		By fruitNameColumnHeader = By.xpath("//table[contains(@class,'table-bordered')]//tr/th[1]");
		WebElement fruitNameColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(fruitNameColumnHeader));
		fruitNameColumn.click();
		
		//Capture all web  elements into a list
		By elementFromFirstColumn = By.xpath("//tr//td[1]");
		List<WebElement> elementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementFromFirstColumn));
		
		//Capture text of all web Elements into new original list
		List <String> originalElementList = elementList.stream().map(s -> s.getText()).collect(Collectors.toList());
		
		//Sort on the original list of step 3 -> sorted list
		List<String> sortedList = originalElementList.stream().sorted().collect(Collectors.toList());
		
		//Compare original list with sorted list
		Assert.assertTrue(originalElementList.equals(sortedList));
		
		driver.close();
	}
	
	@Test(description = "Print the price of the Rice")
	public void extractVegetablePrice() {
		List<String> price;
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Click on column
		By fruitNameColumnHeader = By.xpath("//table[contains(@class,'table-bordered')]//tr/th[1]");
		WebElement fruitNameColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(fruitNameColumnHeader));
		fruitNameColumn.click();

		// Capture all web elements into a list
		By elementFromFirstColumn = By.xpath("//tr//td[1]");
		List<WebElement> elementList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementFromFirstColumn));

		
		
		// Pagination concepts
		do {

			By rows = By.xpath("//tr//td[1]");
			List<WebElement> rowsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rows));
			// Scan the name column with getText() -> Rice -> Print the price of the Rice
			price = rowsList.stream().filter(x -> x.getText().contains("Rice")).map(x -> getPriceVeggie(x, wait))
					.collect(Collectors.toList());
			price.forEach(a -> System.out.println(a));
			By nextButtonLocator = By.xpath("//a[@aria-label='Next']");
			if (price.size() < 1) {
				WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
				nextButton.click();
			}

		} while (price.size() < 1);

		driver.close();
	}

	private static String getPriceVeggie(WebElement x, WebDriverWait wait) {
		// TODO Auto-generated method stub
		By fruitPriceColumnHeader = By.xpath("//tr/td[1]/following-sibling::td[1]");
		WebElement fruitPriceColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(fruitPriceColumnHeader));
		String priveValue = fruitPriceColumn.getText();
		System.out.println("Price of selected veggetable is: " + priveValue);
		return priveValue;
	}
	
	@Test
	public void handleFilterFunctionality()
	{
		driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		By inputSearchBoxLocator = By.id("search-field");
		WebElement inputSearchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(inputSearchBoxLocator));
		inputSearchBox.sendKeys("Rice");
		
		By veggiesRows = By.xpath("//tr/td[1]");
		List <WebElement> listOfVeggies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(veggiesRows)); //1 results 
		
		List <WebElement> filterList = listOfVeggies.stream().filter(i -> i.getText().contains("Rice"))
				.collect(Collectors.toList()); // 1 result
		
		Assert.assertEquals(listOfVeggies.size(), filterList.size());
		
	}

}

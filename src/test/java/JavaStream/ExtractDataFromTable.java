package JavaStream;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Pojo.Team;

public class ExtractDataFromTable {
	/*
	 * What is mean by JavStream 
	 * Process (perform action on collection) the collection using functional programming without modifying the actual collection.
	 */
	WebDriver driver;
	ChromeOptions options;
	WebDriverWait wait;

	@Test(description = "Extract a data from table and find maximu the strike rate and points")
	public void IPLWebTableAutomation() {
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("https://www.iplt20.com/points-table/men/2025");
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		//Step 1 :- Find the entire web table 
		By tableLocator = By.className("ih-td-tab");
		WebElement table= wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator));
		
		//Step 2 :- Locate the tbody with the chaining of webElement 
		By tbodyLocator = By.id("pointsdata");
		WebElement tbody = table.findElement(tbodyLocator);
		
		//Step 3 :- Find all table rows and extract a data from table
		
		By tableRowLocator = By.tagName("tr");

		List<WebElement> tableRowList = tbody.findElements(tableRowLocator);
		System.out.println("Number of table row is: " + tableRowList.size());
		
		List<Team> teamList = new ArrayList<Team>(); //Team arrgument passed bcz we import a pojo class i.e. Team

		for (WebElement row : tableRowList) {
			By tableDataRowLocator = By.tagName("td");
			List<WebElement> tableDataList = row.findElements(tableDataRowLocator);

//			for (WebElement tableData : tableDataList) {
//				System.out.println(tableData.getText());
//			}
			
			double nrr = Double.parseDouble(tableDataList.get(7).getText());
			int pts = Integer.parseInt(tableDataList.get(10).getText());
			Team team = new Team(tableDataList.get(0).getText(), tableDataList.get(2).getText(), nrr, pts);
			teamList.add(team);
		

		}
		
//		for(Team data : teamList) {
//			System.out.println(data);
//		}
		
		//JavaStream, transformation
//		teamList.stream().forEach(i -> System.out.println(i));
//		
//		teamList.stream()
//		.map(i -> i.getTeamName()) //transformation[Stream<Team> to Stream <String>
//		.forEach(i -> System.out.println(i)); //Termina -- Last
//		
//		teamList.stream()
//		.map(i -> i.getPoints()) // Transformation
//		.forEach(i -> System.out.println(i)); //Terminal operation
		
//	//Filteration
//		teamList.stream()
//		.filter(i -> i.getPoints()==19) //Stream<Team>
//		.map(i -> i.getTeamName()) //Stream<Team> to Stream<String>
//		.forEach(i -> System.out.println(i)); //
//		
	//Max
		int maxPoints = teamList.stream() //Stream<Team>-- Transformation
		.mapToInt(i -> i.getPoints()) //Stream<Team> ---> Stream<Int>
		.max()
		.orElse(0);
		System.out.println("The Maximum Point is: " + maxPoints);
		
	//Filteration	
		teamList.stream()
		.filter(i -> i.getPoints()==maxPoints) //Stream<Team>
		.map(i -> i.getTeamName()) //Stream<Team> to Stream<String>
		.forEach(i -> System.out.println(i)); //
		
	//You are supposed to print the  max NRR of 2 teams!!!
		Team maxTeamNRR = teamList.stream()
				.filter(i->i.getPoints() == maxPoints)
		.max(Comparator.comparingDouble(i -> i.getNrr())).orElse(null);
		
		System.out.println(maxTeamNRR);
		
		
	driver.quit();	
	}

}

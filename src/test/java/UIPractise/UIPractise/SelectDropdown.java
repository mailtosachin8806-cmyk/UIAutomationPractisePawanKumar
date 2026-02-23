package UIPractise.UIPractise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
			
			System.setProperty("Webdriver.chrome.com", "G://Chromedriver//chromedriver-win64//chromedriver.exe");
			ChromeDriver driver = new ChromeDriver();	
			driver.manage().window().maximize();
			driver.get("https://testautomationpractice.blogspot.com/");
			String title = driver.getTitle();
			System.out.println("The title is " + title);
			//Dropdown
			WebElement countyrnamelist=	driver.findElement(By.xpath("//select[@id='country']"));
			Select drpCountry = new Select(countyrnamelist);
			drpCountry.selectByIndex(1);
			Thread.sleep(3000);
			drpCountry.selectByVisibleText("Germany");
			
			//Capture the options from the dropdown
			List<WebElement> options  = drpCountry.getOptions();
			System.out.println("Number of a option in a drop down : " + options.size());
			
			//Printing all the options
//			for (WebElement option : options)
//			{
//				System.out.println(option.getText());
//			}
			
			for (int i=0; i<options.size() ; i++)
			{
				System.out.println(options.get(i).getText());
			}
			
			
			
	}

}

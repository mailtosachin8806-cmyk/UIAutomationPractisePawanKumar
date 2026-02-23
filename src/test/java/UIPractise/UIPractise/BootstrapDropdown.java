package UIPractise.UIPractise;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BootstrapDropdown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	//	System.setProperty("Webdriver.chrome.com", "G://Chromedriver//chromedriver-win64//chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		String title = driver.getTitle();
		System.out.println("The title is " + title);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper']//i)[1]")).click();
		Thread.sleep(3000);
		List<WebElement> dropdownvalue =driver.findElements(By.xpath("//div[@role='listbox']//span"));
	
		for (WebElement value : dropdownvalue)
		{
		String options =value.getText();
		System.out.println("THe Dropdown value is " + options);
		driver.findElement(By.xpath("//div[@class='oxd-select-option']//span[contains(text(),'Admin')]")).click();
		break;
		}
		
		driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys("A");
		Thread.sleep(3000);
		List<WebElement> EmployeeName=	driver.findElements(By.xpath("//div[@role='listbox']//div"));
	
	System.out.println(EmployeeName.size());
	
//	for(WebElement EmployeeOption : EmployeeName )
//	{
//		if(EmployeeOption.getText().equalsIgnoreCase("Deonte Sawyer Blanda"))
//		{
//			EmployeeOption.click();
//			break;
//		}
//			
//	}
	
	for(int i=0;i<EmployeeName.size();i++)
	{
		EmployeeName.get(1).click();
		break;
	}
	
	}

	
}

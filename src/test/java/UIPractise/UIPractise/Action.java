package UIPractise.UIPractise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Action {
	ChromeDriver driver = new ChromeDriver();

	@Test
	public void handleMouseEvent() {

		driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();

		WebElement mac = driver.findElement(By.xpath("//a[text()='Mac (1)']"));
		WebElement desktop = driver.findElement(By.xpath("//a[text()='Desktops']"));

		// Actions is a predefined class
		// action is a interface
		// Build is method to create a action
		// Perform is a method to perform(complete) a action
		Actions action = new Actions(driver);

		// Mouse hover
		action.moveToElement(desktop).moveToElement(mac).click().build().perform();

	}

	@Test(description = "Right click:- ContextClick() is used for right click")
	public void rightClickMouseEvent() {

		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().window().maximize();

		WebElement button = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		// Right click:- ContextClick() is used for right click
		action.contextClick(button).build().perform();

		// Click on copy

		driver.findElement(By.xpath("//span[text()='Copy']")).click();

		// Close alert box
		driver.switchTo().alert().accept();

		// Close browser
		driver.close();

	}

	@Test(description = "Double click :- doublclick() method is used")
	public void doubleClickMouseEvent() {

		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();

		WebElement box1 = driver.findElement(By.xpath("//input[@id='field1']"));
		WebElement box2 = driver.findElement(By.xpath("//input[@id='field2']"));
		WebElement button = driver.findElement(By.xpath("//button[text()='Copy Text']"));

		box1.clear();
		box1.sendKeys("Welcome");

		Actions action = new Actions(driver);
		action.doubleClick(button).build().perform();

		// Validation

		String text = box2.getAttribute("value");

		System.out.println("THe value of text is: " + text);

		if (text.equals("Welcome")) {
			System.out.println("The value is matched");
		} else {
			System.out.println("The value is miss-matched");
		}
		driver.close();
	}

	@Test(description = "DragandDrop:- THis can be perform by draganddrop(source, target)")
	public void dragAndDrop() {

		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();

		WebElement dragElement = driver.findElement(By.xpath("//div[@id='draggable']"));

		WebElement dropElement = driver.findElement(By.xpath("//div[@id='droppable']"));

		Actions action = new Actions(driver);

		action.dragAndDrop(dragElement, dropElement).build().perform();
		driver.close();
	}

	@Test(description = "Action Vs Actions")
	public void ActionVsAction() {

		/*
		 * Actions :- Class, will be used to perform mouse actions. Action :- Interface,
		 * will be used to store created actions.
		 */

		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().window().maximize();

		WebElement button = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions act = new Actions(driver);
		// Right click:- ContextClick() is used for right click
		org.openqa.selenium.interactions.Action myaction = act.contextClick(button).build();
		// Building/ Creating an action and storing into a variable

		myaction.perform(); // We are performing/Completing action

		driver.close();

	}

	@Test(description = "To handle slider :- Use dragAndDropBy(Element, x,y)")
	public void handleSliderElement() {

		driver.get("https://www.jqueryscript.net/demo/Price-Range-Slider-jQuery-UI/");
		driver.manage().window().maximize();

		Actions act = new Actions(driver);

		// Min_Slider
		WebElement min_slider = driver.findElement(By.xpath("//div[@id='slider-range']//span[1]"));
		System.out.println("Default Location of minimum slider : " + min_slider.getLocation()); // (890, 2019) = (x,y)
		act.dragAndDropBy(min_slider, 100, 250).perform();
		System.out.println("Location of minimum slider after moving : " + min_slider.getLocation());

		// Max_Slider
		WebElement max_slider = driver.findElement(By.xpath("//div[@id='slider-range']//span[2]"));
		System.out.println("Default Location of maximum slider : " + max_slider.getLocation()); // (890, 2019) = (x,y)
		act.dragAndDropBy(max_slider, -200, 250).perform();
		System.out.println("Location of maximum slider after moving : " + max_slider.getLocation());

		driver.close();

	}

	@Test(description = "KeyBoard Action")
	public void handleKeyBoardAction() {

		/*
		 * Ex 1:- ctr+shift+A
		 * act.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendkeys("A").keyUp(Keys.SHIFT)
		 * .keyUp(Keys.CONTROL).perform();
		 * 
		 * Ex 2:- Enter act.KeyDown(Keys.ENTER).KeyUP(Keys.ENTER)
		 */

		driver.get("https://text-compare.com/");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//textarea[@id='inputText1']")).sendKeys("Welcome to Selenium");
		Actions act = new Actions(driver);

		// Ctrl+A - Select the text

		act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();

		// CTRL+C => Copy the text into clipboard
		act.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).build().perform();

		// TAB => shift to 2nd box
		act.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();

		// CTRL+V => Paste the text
		act.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).build().perform();

		driver.close();

	}

	@Test(description = "Open link through keyboard action")
	public void OpentheLinkInNEwTab() {

		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement regLink = driver.findElement(By.xpath("//a[text()='Register']"));

		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL).click(regLink).keyUp(Keys.CONTROL).perform();

		driver.close();
	}
}

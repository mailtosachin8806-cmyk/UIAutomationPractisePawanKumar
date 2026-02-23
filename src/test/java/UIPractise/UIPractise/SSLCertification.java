package UIPractise.UIPractise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class SSLCertification {

	/*
	 * ChromeOption driver is used for headless chrome browser ChromeOptions options
	 * = new ChromeOptions(); options.addArguments("--headless=new"); WebDriver
	 * driver = new ChromeDriver(options);
	 */

	WebDriver driver;

	@Test(description = "Handle headless browser")
	public void handleHeadlessBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");

		driver = new ChromeDriver(options);
		driver.get("https://jsonformatter.org/");
		System.out.println("Browser Launch");

		String act_Title = driver.getTitle();
		System.out.println("The title is : " + act_Title);
		driver.quit();

	}

	@Test(description = " Handle SSL Certification ")
	public void handleSSLHandling() {

		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true); // Accept SSL certificate
		driver = new ChromeDriver(options);

		driver.get("https://expired.badssl.com/");

		System.out.println(" Title of the page is: " + driver.getTitle());
		driver.quit();

	}

	@Test(description = "To disable Automated Message")
	public void disableAutomatedMessageonScreen() {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		driver = new ChromeDriver(options);

		driver.get("https://jsonformatter.org/");
		System.out.println("Browser Launch");

		System.out.println(" Title of the page is: " + driver.getTitle());
		driver.quit();

	}

	@Test(description = "Run the test in incognito Mode")
	public void runTestsInconitoMode() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		driver = new ChromeDriver(options);
		driver.get("https://jsonformatter.org/");
		System.out.println("Browser Launch");

		String act_Title = driver.getTitle();
		System.out.println("The title is : " + act_Title);
		driver.quit();

	}

}

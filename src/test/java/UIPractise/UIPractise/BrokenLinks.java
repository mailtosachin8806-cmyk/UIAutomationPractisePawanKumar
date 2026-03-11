package UIPractise.UIPractise;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrokenLinks {
	/*
	 * Broken link is a link which doesn't have any resource in the server.
	 * Means it dose not have a status code. If status code>400 means it is a broken link
	 */

	WebDriver driver;

	@Test(description = "Handle Broken Link")
	public void handleBrokenLinks() {

		driver = new ChromeDriver();
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		int noOfBrokenLink = 0;
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("THe total number of links : " + links.size());

		for (WebElement linkElement : links) {

			String hrefattValue = linkElement.getAttribute("href");

			if (hrefattValue == null || hrefattValue.isEmpty()) {
				System.out.println("Href value is null or empty, not possible to check");
				continue;
			}

			// Hit url to the server
			try {
				URL linkURL = new URL(hrefattValue); // converted href attribute to URL
				HttpURLConnection conn = (HttpURLConnection) linkURL.openConnection(); // Open connction to the server
				conn.connect(); // Connect to server and send request to server

				if (conn.getResponseCode()>= 400) {
					System.out.println(hrefattValue + "===> Broken Link");
					noOfBrokenLink++;
				} else {
					System.out.println(hrefattValue + "===> Not Broken Link");
				}
			}

			catch (Exception e) {

			}
		}

		System.out.println(" Number of Broken Links : " + noOfBrokenLink); //42
		driver.quit();
	}

}

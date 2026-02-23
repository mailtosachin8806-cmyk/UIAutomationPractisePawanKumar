package UIPractise.UIPractise;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class StaticTable {
	@Test
	public void getStaticTableData() {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://testautomationpractice.blogspot.com/");
	String title = driver.getTitle();
	System.out.println("The title is " + title);	
	
	//1) Find the total no. of rows
	int rows= driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size();
	
	System.out.println("Rows count is :" + rows);
	
	//2) Find the total number of columns	
	int columns = driver.findElements(By.xpath("//table[@name='BookTable']//th")).size();
	System.out.println("Number of columns: " + columns);
	
	//3) Read the data from specific row and column
	
	String bookName = driver.findElement(By.xpath("//table[@name='BookTable']//tr[5]/td[1]")).getText();
	System.out.println("Book name is: " + bookName);
	
	//4) Read the all the data from table
	/*
	for (int i=2; i<=rows; i++)
	{
		for(int j=1;j<=columns;j++)
		{
			String value=	driver.findElement(By.xpath("//table[@name='BookTable']//tr["+i+"]/td["+j+"]")).getText();
			System.out.print("The value of table is: " + value + "\t");
		}
		System.out.println();
	}
	*/
	
	//5) Print book name whose name is Mukesh
/*	
	for(int r=2; r<=rows; r++)
	{
		String authorName	= driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[2]")).getText();
		
		if(authorName.equals("Mukesh")) {
			String bookname = driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[1]")).getText();
			System.out.println(bookname+"\t"+authorName);
		}
		
	}
	*/
	
	
	//6) Find total price of all the books
	int totalPrice = 0;
	for(int r=2; r<=rows; r++)
	{
	String price = driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[4]")).getText();
	totalPrice = totalPrice + Integer.parseInt(price);
	}
	
	System.out.println("Total Price of book is: " + totalPrice);
	
	
	
	
	
	
	driver.close();

}
}

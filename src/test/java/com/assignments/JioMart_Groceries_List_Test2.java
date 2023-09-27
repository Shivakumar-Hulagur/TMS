package com.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class JioMart_Groceries_List_Test2 {
	public static void main(String[] args) throws InterruptedException {
		//to open the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//to enter the url
		driver.get("https://www.jiomart.com/");
		Thread.sleep(10000);
		String ele = driver.findElement(By.cssSelector("a:hover")).getText();
		System.out.println("Selected category: "+ele);
		List<WebElement> allItems = driver.findElements(By.xpath("//a[text()='"+ele+"']/following-sibling::div[@class='header-nav-l3-wrapper']/descendant::li"));
		System.out.println("The items present in selected category");
		for(WebElement e:allItems)
		{
			System.out.println(e.getText());
		}
		driver.quit();
	}
}

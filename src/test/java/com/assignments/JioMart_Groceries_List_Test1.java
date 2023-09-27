package com.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JioMart_Groceries_List_Test1 {
	public static void main(String[] args) {

		//to launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//to enter the url
		driver.get("https://www.jiomart.com/");
		
		//to click on groceries link
		driver.findElement(By.id("nav_link_2")).click();
		
		//to find and print the products from selected catagory
		WebElement categorySelected = driver.findElement(By.xpath("//div[@class='accordion-outer top_category_list jm-list category-content-title' and @aria-expanded='true' ]/descendant::span"));
		System.out.println("The selected category: "+categorySelected.getText());
		List<WebElement> allItems = driver.findElements(By.xpath("//span[@class='text-truncate']/ancestor::div[@class='accordion-outer top_category_list jm-list category-content-title' and @aria-expanded='true']/ancestor::div[@class='accordion-wrapper']/descendant::div[@class='jm-list category-content-text-list-item']"));
		System.out.println("The sub products under the selected category");
		for(WebElement e:allItems)
		{
			System.out.println(e.getText());
		}
		
		//to close the browser
		driver.quit();
	}
}

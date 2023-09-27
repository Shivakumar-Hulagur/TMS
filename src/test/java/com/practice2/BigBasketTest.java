package com.practice2;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BigBasketTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//to enter the url
		driver.get("https://www.bigbasket.com/");
		
		//to click on shop by category
		driver.findElement(By.xpath("//button[@id='headlessui-menu-button-:R5bclm:']")).click();
		
		//to perform mouse over action
		Thread.sleep(10000);
		WebElement mainItem = driver.findElement(By.xpath("//div[contains(@class,'CategoryMenu___StyledMenuItems') and @data-headlessui-state='open']/descendant::ul[position()=1]/descendant::a[contains(@class,'wWSyB')]"));
		System.err.println("**********************************");
		System.out.println("The main category you have selected is: "+mainItem.getText());
		WebElement selectedCategory = driver.findElement(By.xpath("//div[contains(@class,'CategoryMenu___StyledMenuItems') and @data-headlessui-state='open']/descendant::ul[position()=2]/descendant::a[contains(@class,'hggaA')]"));
		System.out.println("The sub category you have selected : "+selectedCategory.getText()+"-and the items available under this category are:");
		System.err.println("***********************************");
		List<WebElement> allItems = driver.findElements(By.xpath("//div[contains(@class,'CategoryMenu___StyledMenuItems') and @data-headlessui-state='open']/descendant::ul[contains(@class,'white text-darkOny')]/descendant::a"));
		for(WebElement e:allItems)
		{
			System.out.println(e.getText());
		}
		System.err.println("***************************************");
		driver.quit();
	}
}

package com.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon_MobilePhone_Test {
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		int price=20000;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Phones"+Keys.ENTER);
		try {
			driver.findElement(By.xpath("//div[@id='brandsRefinements']/descendant::i[@class='a-icon a-icon-checkbox']/preceding-sibling::input[@type='checkbox' and @checked]"));
			List<WebElement> allPhones = driver.findElements(By.xpath("//span[@class='a-price-whole'][number(translate(text(),'₹,',''))<"+price+"]/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-size-medium a-color-base a-text-normal']"));
			System.out.println("The number of phones matches for the given criteria : "+allPhones.size());
			List<WebElement> allPrices=driver.findElements(By.xpath("//span[@class='a-price-whole'][number(translate(text(),'₹,',''))<"+price+"]"));
			for(int i=0;i<allPhones.size();i++)
			{
				System.out.println(allPhones.get(i).getText()+"= "+allPrices.get(i).getText());
			}
		}
		catch(Exception e)
		{
				System.out.println("The number of phones found for the given criteria");
		}
		driver.quit();
	}
	
}

package com.praticepackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//span[text()='England']/../../../tr[*]/td[2]/span[2]
public class ICCTest {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.icc-cricket.com/rankings/womens/team-rankings/t20i");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String country1 = "Scotland";
		List<WebElement> countries = driver.findElements(By.xpath("//span[text()='England']/../../../tr[*]/td[2]/span[2]"));
		int count=0;
		for(int i=0; i<countries.size(); i++)
		{
			String country=countries.get(i).getText();
			if(country.equalsIgnoreCase(country1))
			{
				count++;
				break;
			}
		}
		if(count>0)
		{
			System.out.println(country1+" is present");
		}
		else
			System.out.println(country1+" is not present");
		driver.quit();
	}
}

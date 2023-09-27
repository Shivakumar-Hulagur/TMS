package com.praticepackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoiboCalenderTest {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		int Date = 21;
		String month="November 2023";
		driver.get("https://www.goibibo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		driver.findElement(By.xpath("//span[@class='sc-12foipm-22 oSYrJ fswDownArrow']")).click();
		String actual="//div[text()='"+month+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+Date+"']";
		String nextArrow="//span[@aria-label='Next Month']";
		for(;;)
		{
			try {
				driver.findElement(By.xpath(actual)).click();
				break;
			}
			catch(Exception e)
			{
				driver.findElement(By.xpath(nextArrow)).click();
			}
		}
		driver.findElement(By.xpath("//span[text()='Done']")).click();
		driver.close();
	}
}

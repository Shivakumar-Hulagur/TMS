package com.praticepackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class makeMyTripTest {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions act = new Actions(driver);
		act.doubleClick().perform();
		Thread.sleep(3000);
		act.moveByOffset(0, 80).click().perform();
		act.doubleClick().perform();
	
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		String actualDate = "//div[text()='November 2023']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='16']";
		String NextArrow = "//span[@aria-label='Next Month']";
		for (;;) {
			try {
				driver.findElement(By.xpath(actualDate)).click();
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath(NextArrow)).click();
			}
		}

	}
}

package com.assignments;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Kayak_Co_In_Test {
	public static void main(String[] args) throws InterruptedException {
		//to launch th browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((20)));
		String from="Bengaluru";
		String to="Delhi";
		driver.get("https://www.kayak.co.in/");
		driver.findElement(By.xpath("//div[@aria-label='Remove']")).click();
		driver.findElement(By.xpath("//input[@placeholder='From?']")).sendKeys(from);
		driver.findElement(By.xpath("//div[@class='JyN0-item JyN0-pres-item-mcfly']")).click();
		driver.findElement(By.xpath("//input[@class='k_my-input' and @placeholder='To?']")).sendKeys(to);
		driver.findElement(By.xpath("//div[@class='JyN0-item JyN0-pres-item-mcfly']")).click();
		driver.findElement(By.xpath("//span[@aria-label='Start date calendar input']")).click();
		String fromDate="//div[text()='October 2023']/ancestor::div[@class='onx_ onx_-pres-mcfly onx_-double']/descendant::div[text()='18']";
		String toDate="//div[text()='October 2023']/ancestor::div[@class='onx_ onx_-pres-mcfly onx_-double']/descendant::div[text()='18']";
		for(;;)
		{
			try {
				driver.findElement(By.xpath(fromDate)).click();
				break;
			}
			catch (Exception e) {
				driver.findElement(By.xpath("//button[@aria-label='Next month']//span")).click();
			}
		}
		driver.findElement(By.xpath("//span[@aria-label='End date calendar input']")).click();
		for(;;)
		{
			try {
				driver.findElement(By.xpath(toDate)).click();
				break;
			}
			catch (Exception e) {
				driver.findElement(By.xpath("//button[@aria-label='Next month']//span")).click();
			}
		}
		driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
		Thread.sleep(5000);
		String mainWind = driver.getWindowHandle();
		Set<String> allWind = driver.getWindowHandles();
		for(String s:allWind)
		{
			if(mainWind.equalsIgnoreCase(s))
			{
			}
			else
			{
				driver.switchTo().window(s);
				driver.findElement(By.xpath("//div[@aria-label='Other sort']")).click();
			}

		}
		driver.findElement(By.xpath("//span[text()='Slowest']")).click();
		String flightName=driver.findElement(By.xpath("//div[@class='vrqM-provider-brand']")).getText();
		System.out.println("The lowest fligh company: "+flightName);
		String flightTicketPrice=driver.findElement(By.xpath("//div[@class='vrqM-provider-brand']/preceding-sibling::div")).getText();
		System.out.println("The price of flight ticket is: "+flightTicketPrice);
		driver.quit();
	}
}

package com.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Emi_Calculator_Test1 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://emicalculator.net/");
		Thread.sleep(10000);
		List<WebElement> values = driver.findElements(By.xpath("//*[name()='g' and @class='highcharts-label highcharts-tooltip highcharts-color-undefined']//*[name()='text' and @data-z-index='1']//*[name()='tspan']"));
		for(WebElement e: values)
		{
			System.out.println(e.getText());
		}
		driver.quit();
	}
}

package com.praticepackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Zulu_Longiners_Test {
public static void main(String[] args) {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://zulu.longines.com/");
	Actions a=new Actions(driver);
	a.scrollByAmount(100, 1000).perform();
	driver.findElement(By.xpath("//span[text()='Scroll to explore']")).click();
//	driver.findElement(By.xpath("//div[@class='container']//span[text()='READ MORE']")).click();
	
}
}

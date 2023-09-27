package com.practice2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Zulu_Longiners {
public static void main(String[] args) {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://zulu.longines.com/");
	
	WebElement ele1 = driver.findElement(By.xpath("//h3[text()='Swiss Made since 1832']"));
	Actions a=new Actions(driver);
	a.scrollToElement(ele1).perform();
}
}

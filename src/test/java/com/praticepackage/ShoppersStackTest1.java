package com.praticepackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ShoppersStackTest1 {
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.shoppersstack.com/");
		 driver.findElement(By.id("electronics")).click();
		Actions a=new Actions(driver);
		driver.findElement(By.xpath("//a[text()='Cameras ']")).click();
		List<WebElement> allCameras = driver.findElements(By.xpath("//p[@class='CalculateOffer_wrapper__R8RZf']/child::span[1][text()<50000]"));
		List<WebElement> allCamerasName = driver.findElements(By.xpath("//p[@class='CalculateOffer_wrapper__R8RZf']/child::span[1][text()<50000]/ancestor::div[@class='featuredProducts_footerLeft__iP3CQ']/span[1]"));
		for(int i=0;i<allCameras.size();i++)
		{
			System.out.println("Camera name: "+allCamerasName.get(i).getText()+" "+"camera price: "+allCameras.get(i).getText());
		}

	}
}

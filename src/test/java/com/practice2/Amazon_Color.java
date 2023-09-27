package com.practice2;

import java.awt.Color;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon_Color {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Iphone 14"+Keys.ENTER);
		Thread.sleep(5000);
		List<WebElement> allColors = driver.findElements(By.xpath("//span[text()='Apple iPhone 14 (128 GB) - Starlight']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='s-color-swatch-inner-circle-fill']"));
		ArrayList<String> ls=new ArrayList();
		for(WebElement e:allColors)
		{
			String res = e.getCssValue("background-color");
			System.out.println(res);
			String res1 = org.openqa.selenium.support.Color.fromString(res).asHex();
			System.out.println(res1);
			ls.add(res1);
		}
		driver.switchTo().newWindow(WindowType.TAB).get("https://www.w3schools.com/colors/colors_converter.asp");
		for(int i=0;i<ls.size();i++)
		{
			WebElement converter = driver.findElement(By.xpath("//input[@id='color01']"));
			converter.clear();
			converter.sendKeys(ls.get(i));
			String colorName=driver.findElement(By.xpath("//td[@id='name01']")).getText();
			System.out.println(colorName);
		}
		driver.quit();
	}
}
	
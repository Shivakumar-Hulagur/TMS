package com.practice2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class IsDigitMethod {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System");
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		driver.findElement(By.xpath("//span[text()='Manage Pages']")).click();
		WebElement dropDown = driver.findElement(By.xpath("//select[@name='menu1']"));
		Select s=new Select(dropDown);
		s.selectByVisibleText("aboutus");
		String res = driver.findElement(By.xpath("//div[@class=' nicEdit-main']")).getText();
		int length = res.length();
		String num="";
		for(int i=0;i<length;i++)
		{
			if(Character.isDigit(res.charAt(i)))
			num=num+res.charAt(i);
		}
		System.out.println(num);
		
	}
}

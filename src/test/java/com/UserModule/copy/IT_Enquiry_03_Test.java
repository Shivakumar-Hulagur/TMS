package com.UserModule.copy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class IT_Enquiry_03_Test {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String expectedValue = "AXAI";
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System/");
		driver.findElement(By.xpath("//a[normalize-space(text())='Enquiry']")).click();
		driver.findElement(By.xpath("//form[@name='enquiry']/descendant::input[@name='fname']")).sendKeys(expectedValue);
		driver.findElement(By.xpath("//form[@name='enquiry']/descendant::input[@name='email']")).sendKeys("Abcde@gmail.com");
		driver.findElement(By.xpath("//form[@name='enquiry']/descendant::input[@name='mobileno']")).sendKeys("9874563210");
		driver.findElement(By.xpath("//form[@name='enquiry']/descendant::input[@name='subject']")).sendKeys("Regarding package");
		driver.findElement(By.xpath("//form[@name='enquiry']/descendant::textarea[@name='description']")).sendKeys("Need more offers to the packages");
		driver.findElement(By.xpath("//form[@name='enquiry']/descendant::button[@name='submit1']")).click();
		driver.findElement(By.linkText("Admin Login")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		driver.findElement(By.xpath("//span[text()='Manage Enquiries']")).click();
		driver.findElement(By.xpath("//span[@class='fa fa-bars']")).click();
		WebElement btmElement = driver.findElement(By.xpath("//div[@class='copyrights']"));
		Actions a=new Actions(driver);
		a.scrollToElement(btmElement).perform();
		boolean flag=false;
		 List<WebElement> alle = driver.findElements(By.xpath("//tbody/tr/td[2]/span"));
		// System.out.print(alle.size());
		for(int i=0;i<alle.size();i++)
		{
			if(i==alle.size()-1)
			{
				//System.out.println(alle.get(i).getText());
				if(alle.get(i).getText().contains(expectedValue))
				{
					
					flag=true;
					break;
				}
			}
		}
		if(flag)
			System.out.println("The enquiry generated successfully and pass");
		else
			System.out.println("Unable to genrate enquiry and fail");
		WebElement logoutEle = driver.findElement(By.xpath("//i[@class='fa fa-angle-down']"));
		a.scrollToElement(logoutEle).perform();
		a.click(logoutEle).perform();
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.quit();
	}
}

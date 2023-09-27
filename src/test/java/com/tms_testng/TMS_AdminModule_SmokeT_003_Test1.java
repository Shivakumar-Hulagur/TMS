package com.tms_testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TMS_AdminModule_SmokeT_003_Test1 {
	@Test
	public void tms_Admin_Model_Smoke_03_Test()
	{
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//to enter the url
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System/");

		//to click on admin login link
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();

		//to get the title of the page and validate login
		String expectedTitle="TMS | Admin Dashboard";
		String actualTitle = driver.getTitle();
		if(actualTitle.equalsIgnoreCase(expectedTitle))
			System.out.println("Login successful and passed");
		else
			System.out.println("Login unsuccessful and failed");
		driver.quit();
	}
}
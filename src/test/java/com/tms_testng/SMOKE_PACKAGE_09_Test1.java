package com.tms_testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SMOKE_PACKAGE_09_Test1 {
	@Test
	public void smoke_Package_Test() {
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//to enter the url
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System");
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//input[@id='email' and @placeholder='Enter your Email']")).sendKeys("anuj@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='signin']")).click();
		driver.findElement(By.xpath("//a[text()='Tour Packages']")).click();
		String expectedValue="TMS | Package List";
		String actualValue=driver.getTitle();
		if(actualValue.equalsIgnoreCase(expectedValue))
			System.out.println("Navigating to Tour package list page pass");
		else
			System.out.println("Not naviagting to tour package list page and fail");
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		driver.quit();
	}
}

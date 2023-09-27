package com.testscripts_generic;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;

public class SMOKE_PACKAGE_09_Test {
	public static void main(String[] args) throws IOException {
		
		WebDriverUtility1 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);

		//to enter the url
		String url = pLib.getPropertyKeyValue("url");
		driver.get(url);
		String un=pLib.getPropertyKeyValue("username");
		String pwd=pLib.getPropertyKeyValue("password");
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//input[@id='email' and @placeholder='Enter your Email']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
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

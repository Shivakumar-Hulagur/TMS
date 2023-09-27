package com.testscripts_generic;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;

public class TMS_AdminModule_SmokeT_003_Test {
	public static void main(String[] args) throws IOException
	{
		WebDriverUtility1 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);

		//to enter the url
		String url=pLib.getPropertyKeyValue("url");
		driver.get(url);
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");
		//to click on admin login link
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(adminUN);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(adminPwd);
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
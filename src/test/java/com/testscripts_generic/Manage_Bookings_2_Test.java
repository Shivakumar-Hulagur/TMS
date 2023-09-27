package com.testscripts_generic;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.tms.genericutils.ExcelFileUtilites1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;

public class Manage_Bookings_2_Test {
	public static void main(String[] args) throws IOException
	{
		WebDriverUtility1 wLib = new WebDriverUtility1();
		ExcelFileUtilites1 eLib = new ExcelFileUtilites1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);
		
		//to fecth the data from property file
		String url = pLib.getPropertyKeyValue("url");
		driver.get(url);
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");

		//to click on admin login link
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(adminUN);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(adminPwd);
		driver.findElement(By.xpath("//input[@name='login']")).click();

		String bookingId="#BK-98";	
		//to click on Manage Bookings link
		driver.findElement(By.xpath("//span[text()='Manage Booking']")).click();
		WebElement ele = driver.findElement(By.xpath("//table/tbody/tr/td[1]/span[text()='"+bookingId+"']"));
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
		List<WebElement> allelem = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		//System.out.println(elem.size());

		//System.out.println(i);
		driver.findElement(By.xpath("//span[text()='"+bookingId+"']/../following-sibling::td[8]/descendant::a[2]")).click();
		wLib.acceptAlertPopUp(driver, "cancel booking");
		WebElement ele1 = driver.findElement(By.xpath("//div[@class='succWrap' and contains(text(),':Booking Confirm successfully') ]"));
		System.out.println(ele1.getText());
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();

		//User login
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		String un=pLib.getPropertyKeyValue("username");
		String pwd = pLib.getPropertyKeyValue("password");
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='email']")).sendKeys(un);
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@name='signin']")).click();
		driver.findElement(By.xpath("//a[text()='My Tour History']")).click();
		String userBookingId="#BK98";
		wLib.mouseHover(driver, driver.findElement(By.xpath("//table/tbody/tr/td[text()='"+userBookingId+"']")));
		  String res = driver.findElement(By.xpath("//tbody/tr/td[text()='"+userBookingId+"']/following::td[5]")).getText();
		boolean flag=false;
				if(res.contains("Confirmed"))
					flag=true;
			
		if(flag)
			System.out.println("The admin confirmed booking is updating in 'My tour histroy' model of user and test case pass");
		else
			System.out.println("The admin confirmed booking is not get updating in 'My tour history' of  user model test case fail");
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		driver.quit();
	}
}

package com.tms_testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Manage_Bookings_2_Test1 {
	@Test
	public void manage_Bookings_Test() {
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

		String bookingId="#BK-25";	
		//to click on Manage Bookings link
		driver.findElement(By.xpath("//span[text()='Manage Booking']")).click();
		WebElement ele = driver.findElement(By.xpath("//table/tbody/tr/td[1]/span[text()='"+bookingId+"']"));
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
		//List<WebElement> allelem = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		//System.out.println(elem.size());

		//System.out.println(i);
		a.click(driver.findElement(By.xpath("//span[text()='"+bookingId+"']/../following-sibling::td[8]/descendant::a[2]"))).perform();
		driver.switchTo().alert().accept();
		WebElement ele1 = driver.findElement(By.xpath("//div[@class='succWrap' and contains(text(),':Booking Confirm successfully') ]"));
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();

		//User login
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='email']")).sendKeys("anuj@gmail.com");
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='signin']")).click();
		driver.findElement(By.xpath("//a[text()='My Tour History']")).click();
		String userBookingId="#BK25";
		a.moveToElement(driver.findElement(By.xpath("//table/tbody/tr/td[text()='"+userBookingId+"']"))).perform();
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

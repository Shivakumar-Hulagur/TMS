package com.tms_testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ST_03_Test1 {
	
	@Test
	public void st_03_Test(){
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//to enter the url
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System/");

		Actions a=new Actions(driver);
		//user login code
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='email']")).sendKeys("anuj@gmail.com");
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='signin']")).click();

		//to book the tour package
		String packName="Package Name: Kerla";
		String fromMonth="September";
		int fromYear=2023;
		int fromDate=20;
		String toMonth="October";
		int toYear=2023;
		int toDate=10;
		driver.findElement(By.xpath("//a[text()='Tour Packages']")).click();
		driver.findElement(By.xpath("//h4[text()='"+packName+"']/ancestor::div[@class='rom-btm']/descendant::a")).click();
		driver.findElement(By.id("datepicker")).click();
		for(;;)
		{
			try {
				driver.findElement(By.xpath("//span[text()='"+fromMonth+"']/following-sibling::span[text()='"+fromYear+"']/ancestor::div[@id='ui-datepicker-div']/descendant::a[@class='ui-state-default' and text()='"+fromDate+"']")).click();
				break;
			}
			catch (Exception e) {
				driver.findElement(By.xpath("//a[@data-handler='next']")).click();
			}
		}
		driver.findElement(By.id("datepicker1")).click();
		for(;;)
		{
			try {
				driver.findElement(By.xpath("//span[text()='"+toMonth+"']/following-sibling::span[text()='"+toYear+"']/ancestor::div[@id='ui-datepicker-div']/descendant::a[@class='ui-state-default' and text()='"+toDate+"']")).click();
				break;
			}
			catch (Exception e) {
				driver.findElement(By.xpath("//a[@data-handler='next']")).click();
			}
		}
		String comment="New tour";
		WebElement ele=driver.findElement(By.xpath("//input[@name='comment']"));
		a.scrollToElement(ele).perform();
		ele.sendKeys(comment);
		driver.findElement(By.xpath("//button[text()='Book']")).click();	
		String text = driver.findElement(By.xpath("//div[@class='succWrap']")).getText();
		if(text.contains("SUCCESS:Booked Successfully"))
			System.out.println("**********The package booking request generated successfully**********");
		else
			System.out.println("*********The package booking is not generated successfully**********");

		//to get my tour history(booking id)
		driver.findElement(By.xpath("//a[text()='My Tour History']")).click();
		WebElement bottomPage = driver.findElement(By.xpath("//div[@class='copy-right']"));
		a.scrollToElement(bottomPage).perform();
		List<WebElement> allBookingId = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		String newlyGeneratedBookingId=null;
		for(int i=0;i<allBookingId.size();i++)
		{
			if(i==allBookingId.size()-1)
			{
				newlyGeneratedBookingId=allBookingId.get(i).getText();
			}
		}
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();


		//to click on admin login link
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();

		//to verify newly created booking request is present in manage Bookings module
		driver.findElement(By.xpath("//span[text()='Manage Booking']")).click();
		String adminModelBookingId = newlyGeneratedBookingId.substring(4);
		WebElement bookingele = driver.findElement(By.xpath("//table/tbody/tr/td[1]/span[contains(text(),'"+adminModelBookingId+"')]"));
		a.scrollToElement(bookingele).perform();
		String bookingRes = bookingele.getText().substring(4);
		if(bookingRes.contains(bookingRes))
			System.out.println("*********The user generated booking request is displaying in admin model and pass************");
		else
			System.out.println("*******The user generated booking request is not displying in admin model and fail******");

		//to logout as admin
		WebElement adminLogout1 = driver.findElement(By.xpath("//i[@class='fa fa-angle-down']"));
		a.scrollToElement(adminLogout1).perform();
		a.click(adminLogout1).perform();
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();

		//user login code
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='email']")).sendKeys("anuj@gmail.com");
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='signin']")).click();


		//to cancel the booking from user end through my tour history(booking id)
		String expBookingCancelRes="Cancelled";
		driver.findElement(By.xpath("//a[text()='My Tour History']")).click();
		WebElement ele2 = driver.findElement(By.xpath("//tbody/tr/td[text()='"+newlyGeneratedBookingId+"']"));
		a.scrollToElement(ele2).perform();
		WebElement cancelElement = driver.findElement(By.xpath("//tbody/tr/td[text()='"+newlyGeneratedBookingId+"']/following::td[7]"));
		a.click(cancelElement).perform();
		driver.switchTo().alert().accept();
		WebElement cancEle = driver.findElement(By.xpath("//tbody/tr/td[text()='"+newlyGeneratedBookingId+"']/following::td[7]"));
		a.scrollToElement(cancEle).perform();
		String actualBookingCancelRes = cancEle.getText();
		if(actualBookingCancelRes.contains(expBookingCancelRes))
			System.out.println("******The booking request cancellation successful and pass*******");
		else
			System.out.println("******The booking request cancellation unsuccessful and fail********");

		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		a.scrollToElement(logout).perform();
		a.click(logout).perform();
		driver.quit();
	}
}

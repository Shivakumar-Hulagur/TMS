package com.testscripts_generic;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.tms.genericutils.JsonUtilities1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;

public class ST_03_Test {
	public static void main(String[] args) throws IOException, ParseException{
		
		WebDriverUtility1 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		JsonUtilities1 jLib = new JsonUtilities1();
		
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);

		//to enter the url
		String url=pLib.getPropertyKeyValue("url");
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");
		String userName=pLib.getPropertyKeyValue("username");
		String password=pLib.getPropertyKeyValue("password");
		driver.get(url);
		

		//user login code
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='signin']")).click();

		//to book the tour package
		String packName=jLib.readDataFromJson("packName");
		String fromMonth=jLib.readDataFromJson("fromMonth");
		int fromYear=Integer.parseInt(jLib.readDataFromJson("fromYear"));
		int fromDate=Integer.parseInt(jLib.readDataFromJson("fromDate"));
		String toMonth=jLib.readDataFromJson("toMonth");
		int toYear=Integer.parseInt(jLib.readDataFromJson("toYear"));
		int toDate=Integer.parseInt(jLib.readDataFromJson("toDate"));
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
		String comment=jLib.readDataFromJson("comment");
		WebElement ele=driver.findElement(By.xpath("//input[@name='comment']"));
		wLib.scrollToElement(driver, ele);
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
		wLib.scrollToElement(driver, bottomPage);
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
		wLib.scrollToElement(driver, bookingele);
		String bookingRes = bookingele.getText().substring(4);
		if(bookingRes.contains(bookingRes))
			System.out.println("*********The user generated booking request is displaying in admin model and pass************");
		else
			System.out.println("*******The user generated booking request is not displying in admin model and fail******");

		//to logout as admin
		WebElement adminLogout1 = driver.findElement(By.xpath("//i[@class='fa fa-angle-down']"));
		wLib.scrollToElement(driver, adminLogout1);
		wLib.clickUsingActionsClass(driver, adminLogout1);
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();

		//user login code
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='signin']")).click();


		//to cancel the booking from user end through my tour history(booking id)
		String expBookingCancelRes="Cancelled";
		driver.findElement(By.xpath("//a[text()='My Tour History']")).click();
		WebElement ele2 = driver.findElement(By.xpath("//tbody/tr/td[text()='"+newlyGeneratedBookingId+"']"));
		wLib.scrollToElement(driver, ele2);
		WebElement cancelElement = driver.findElement(By.xpath("//tbody/tr/td[text()='"+newlyGeneratedBookingId+"']/following::td[7]"));
		wLib.clickUsingActionsClass(driver, cancelElement);
		wLib.acceptAlertPopUp(driver, " ");
		WebElement cancEle = driver.findElement(By.xpath("//tbody/tr/td[text()='"+newlyGeneratedBookingId+"']/following::td[7]"));
		wLib.scrollToElement(driver, cancEle);
		String actualBookingCancelRes = cancEle.getText();
		if(actualBookingCancelRes.contains(expBookingCancelRes))
			System.out.println("******The booking request cancellation successful and pass*******");
		else
			System.out.println("******The booking request cancellation unsuccessful and fail********");

		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		wLib.scrollToElement(driver, logout);
		wLib.clickUsingActionsClass(driver, logout);
		driver.quit();
	}
}

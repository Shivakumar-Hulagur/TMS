package com.baseclassscripts;

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

import com.tms.genericutils.BaseClass;
import com.tms.genericutils.ExcelFileUtilites1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.HomePage;
import com.tms.objectrepo.UserDashBoardPage;

public class Manage_Bookings_2_Test extends BaseClass{
	@Test(groups = "regression")
	public void manageBookingsTest() throws IOException
	{
		WebDriverUtility1 wLib = new WebDriverUtility1();
		ExcelFileUtilites1 eLib = new ExcelFileUtilites1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		
		
		//to fecth the data from property file
		String url = pLib.getPropertyKeyValue("url");
		driver.get(url);
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");

		//to click on admin login link
		HomePage hp = new HomePage(driver);
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.getAdminUnTbx().sendKeys(adminUN);
		alp.getAdminPwdTbx().sendKeys(adminPwd);
		alp.getAdminLoginBtn().click();
		
		String bookingId="#BK-98";	
		//to click on Manage Bookings link
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.getManageBookings().click();
		WebElement ele = driver.findElement(By.xpath("//table/tbody/tr/td[1]/span[text()='"+bookingId+"']"));
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
		//List<WebElement> allelem = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		//System.out.println(elem.size());

		//System.out.println(i);
		driver.findElement(By.xpath("//span[text()='"+bookingId+"']/../following-sibling::td[8]/descendant::a[2]")).click();
		wLib.acceptAlertPopUp(driver, "cancel booking");
		WebElement ele1 = driver.findElement(By.xpath("//div[@class='succWrap' and contains(text(),':Booking Confirm successfully') ]"));
		System.out.println(ele1.getText());
		//to logout as admin
		adb.getWelcomeDropDown().click();
		adb.getAdminSignOutLink().click();
		alp.getBackToHomeLink().click();

		//User login
		hp.getUserSignInLink().click();
		String un=pLib.getPropertyKeyValue("username");
		String pwd = pLib.getPropertyKeyValue("password");
		hp.getUserSignInEmailTbx().sendKeys(un);
		hp.getUserSignInPasswordTbx().sendKeys(pwd);
		hp.getUserSignInButton().click();
		
		//to verify booking confirmation
		UserDashBoardPage udb = new UserDashBoardPage(driver);
		udb.getMyTourHistorylink().click();
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
		udb.getUserLogoutLink().click();
	}
}

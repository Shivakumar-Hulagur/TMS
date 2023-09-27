package com.baseclassscripts;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.tms.genericutils.BaseClass;
import com.tms.genericutils.JsonUtilities1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.BookTourPackagePage;
import com.tms.objectrepo.HomePage;
import com.tms.objectrepo.MyTourHistoryPage;
import com.tms.objectrepo.UserDashBoardPage;

public class ST_03_Test extends BaseClass{
	
	@Test(groups="regression")
	public void st_03_Test() throws IOException, ParseException{

		WebDriverUtility1 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		JsonUtilities1 jLib = new JsonUtilities1();


		//to enter the url
		String url=pLib.getPropertyKeyValue("url");
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");
		String userName=pLib.getPropertyKeyValue("username");
		String password=pLib.getPropertyKeyValue("password");
		driver.get(url);


		//user login code
		HomePage hp = new HomePage(driver);
		hp.getUserSignInLink().click();
		hp.getUserSignInEmailTbx().sendKeys(userName);
		hp.getUserSignInPasswordTbx().sendKeys(password);
		hp.getUserSignInButton().click();

		//to book the tour package
		String packName=jLib.readDataFromJson("packName");
		String fromMonth=jLib.readDataFromJson("fromMonth");
		int fromYear=Integer.parseInt(jLib.readDataFromJson("fromYear"));
		int fromDate=Integer.parseInt(jLib.readDataFromJson("fromDate"));
		String toMonth=jLib.readDataFromJson("toMonth");
		int toYear=Integer.parseInt(jLib.readDataFromJson("toYear"));
		int toDate=Integer.parseInt(jLib.readDataFromJson("toDate"));

		//to book tour package
		UserDashBoardPage udb = new UserDashBoardPage(driver);
		udb.getTourPackagesLink().click();
		BookTourPackagePage btp = new BookTourPackagePage(driver);
		driver.findElement(By.xpath("//h4[text()='"+packName+"']/ancestor::div[@class='rom-btm']/descendant::a")).click();
		btp.getFromDateCalenderPopUp().click();
		for(;;)
		{
			try {
				driver.findElement(By.xpath("//span[text()='"+fromMonth+"']/following-sibling::span[text()='"+fromYear+"']/ancestor::div[@id='ui-datepicker-div']/descendant::a[@class='ui-state-default' and text()='"+fromDate+"']")).click();
				break;
			}
			catch (Exception e) {
				btp.getNextMonthSelector().click();
			}
		}
		btp.getToDateCalendarPopUp().click();
		for(;;)
		{
			try {
				driver.findElement(By.xpath("//span[text()='"+toMonth+"']/following-sibling::span[text()='"+toYear+"']/ancestor::div[@id='ui-datepicker-div']/descendant::a[@class='ui-state-default' and text()='"+toDate+"']")).click();
				break;
			}
			catch (Exception e) {
				btp.getNextMonthSelector().click();
			}
		}
		String comment=jLib.readDataFromJson("comment");
		WebElement ele=btp.getCommentTextArea();
		wLib.scrollToElement(driver, ele);
		ele.sendKeys(comment);
		btp.getBookBtn().click();	
		String text =btp.getBookingRequestConfMeg().getText();
		if(text.contains("SUCCESS:Booked Successfully"))
			System.out.println("**********The package booking request generated successfully**********");
		else
			System.out.println("*********The package booking is not generated successfully**********");

		//to get my tour history(booking id)
		udb.getMyTourHistorylink().click();
		MyTourHistoryPage mth = new MyTourHistoryPage(driver);
		WebElement bottomPage = mth.getCopyRightTab();
		wLib.scrollToElement(driver, bottomPage);
		List<WebElement> allBookingId = mth.getUserBookingId();
		String newlyGeneratedBookingId=null;
		for(int i=0;i<allBookingId.size();i++)
		{
			if(i==allBookingId.size()-1)
			{
				newlyGeneratedBookingId=allBookingId.get(i).getText();
			}
		}
		udb.getUserLogoutLink().click();


		//to click on admin login link
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.getAdminUnTbx().sendKeys(adminUN);
		alp.getAdminPwdTbx().sendKeys(adminPwd);
		alp.getAdminLoginBtn().click();

		//to verify newly created booking request is present in manage Bookings module
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.getManageBookings().click();
		String adminModelBookingId = newlyGeneratedBookingId.substring(4);
		WebElement bookingele = driver.findElement(By.xpath("//table/tbody/tr/td[1]/span[contains(text(),'"+adminModelBookingId+"')]"));
		wLib.scrollToElement(driver, bookingele);
		String bookingRes = bookingele.getText().substring(4);
		if(bookingRes.contains(bookingRes))
			System.out.println("*********The user generated booking request is displaying in admin model and pass************");
		else
			System.out.println("*******The user generated booking request is not displying in admin model and fail******");

		//to logout as admin
		WebElement adminLogout1 = adb.getWelcomeDropDown();
		wLib.scrollToElement(driver, adminLogout1);
		wLib.clickUsingActionsClass(driver, adminLogout1);
		adb.getAdminSignOutLink().click();
		alp.getBackToHomeLink().click();

		//user login code
		hp.getUserSignInLink().click();
		hp.getUserSignInEmailTbx().sendKeys(userName);
		hp.getUserSignInPasswordTbx().sendKeys(password);
		hp.getUserSignInButton().click();


		//to cancel the booking from user end through my tour history(booking id)
		String expBookingCancelRes="Cancelled";
		udb.getMyTourHistorylink().click();
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

		WebElement logout = udb.getUserLogoutLink();
		wLib.scrollToElement(driver, logout);
		wLib.clickUsingActionsClass(driver, logout);
	}
}

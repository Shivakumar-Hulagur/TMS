package com.tms.testng_pom;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.tms.genericutils.ExcelFileUtilites1;
import com.tms.genericutils.IPathConstants1;
import com.tms.genericutils.JsonUtilities1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.BookTourPackagePage;
import com.tms.objectrepo.CreateTourPackagePage;
import com.tms.objectrepo.HomePage;
import com.tms.objectrepo.ManageBookingsPage;
import com.tms.objectrepo.ManageTourPackagePage;
import com.tms.objectrepo.UserDashBoardPage;


public class TMS_AdminModel_ST_001_Test {

	@Test
	public void adminSTtest() throws Throwable {
		
		WebDriverUtility1 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		JsonUtilities1 jLib = new JsonUtilities1();
		ExcelFileUtilites1 eLib = new ExcelFileUtilites1();
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.deleteAllCookies(driver);
		wLib.implicitWait(driver, 10);

		//to enter the url
		String url=pLib.getPropertyKeyValue("url");
		String username=pLib.getPropertyKeyValue("username");
		String passWord=pLib.getPropertyKeyValue("password");
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");

		driver.get(url);

		//user login code
		HomePage hp = new HomePage(driver);
		hp.getUserSignInLink().click();
		hp.getUserSignInEmailTbx().sendKeys(username);
		hp.getUserSignInPasswordTbx().sendKeys(passWord);
		hp.getUserSignInButton().click();

		//to book the tour package-fectching the data from json file
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
		driver.findElement(By.xpath("//h4[text()='"+packName+"']/ancestor::div[@class='rom-btm']/descendant::a")).click();
		BookTourPackagePage btp = new BookTourPackagePage(driver);
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
		wLib.scrollByXAxisToWebElement(driver, ele);
		ele.sendKeys(comment);
		btp.getBookBtn().click();	
		String text = btp.getBookingRequestConfMeg().getText();
		if(text.contains("SUCCESS:Booked Successfully"))
			System.out.println("**********The package booking request generated successfully**********");
		else
			System.out.println("*********The package booking is not generated successfully**********");
		udb.getUserLogoutLink().click();


		//Admin login script
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.getAdminUnTbx().sendKeys(adminUN);
		alp.getAdminPwdTbx().sendKeys(adminPwd);
		alp.getAdminLoginBtn().click();
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.getTourPackagesDropMenu().click();
		adb.getCreatTourPack().click();

		//to read the data from the excel file to create tour packages	
		HashMap<String, String> map= eLib.getExcelDataByMap("Create_Package");		
		for(java.util.Map.Entry<String, String> set:map.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		File f=new File(IPathConstants1.packageImage);
		String imgPath = f.getAbsolutePath();
		CreateTourPackagePage ctp = new CreateTourPackagePage(driver);
		ctp.getPackageImg().sendKeys(imgPath);
		
		WebElement createBtn = ctp.getCreateTourPackageBtn();
		wLib.scrollToElement(driver, createBtn);
		createBtn.click();
		String expectedRes="SUCCESS:Package Created Successfully";
		String actualRes=ctp.getPackageCreationOrBookingOrCancelConfMsg().getText();
		if(actualRes.contains(expectedRes))
			System.out.println("***********The Package is created successfully************");
		else
			System.out.println("*********The Package is not created unsucessful***********");
		
		// to modify the exisiting tour package
		
		adb.getTourPackagesDropMenu().click();
		adb.getManageTourPack().click();
		String modifyPackName=jLib.readDataFromJson("modifyPackName");
		WebElement modificationPack = driver.findElement(By.xpath("//span[@class='bt-content' and text()='"+modifyPackName+"']"));
		wLib.scrollToElement(driver, modificationPack);
		driver.findElement(By.xpath("//span[@class='bt-content' and text()='"+modifyPackName+"']/ancestor::tr/descendant::button[text()='View Details']")).click();
		String modificationFieldName=jLib.readDataFromJson("modificationFieldName");
		String modificationValue=jLib.readDataFromJson("modificationValue");
		driver.findElement(By.xpath("//input[@name='"+modificationFieldName+"']")).clear();
		driver.findElement(By.xpath("//input[@name='"+modificationFieldName+"']")).sendKeys(modificationValue);
		ManageTourPackagePage mtp = new ManageTourPackagePage(driver);
		WebElement updateBtn = mtp.getUpdateBtn();
		wLib.scrollUsingActionsClass(driver, updateBtn);
		updateBtn.click();
		String expectedUpdateValue="SUCCESS:Package Updated Successfully";
		String actualUpdateValue=mtp.getConfMsg().getText();
		if(actualUpdateValue.contains(expectedUpdateValue))
			System.out.println("***********The package details are updated successfully**********");
		else
			System.out.println("************Unable to update the package details***********");

		//to confirm the booking request
		String bookingId="#BK-15";	
		adb.getManageBookings().click();
		WebElement ele1 = driver.findElement(By.xpath("//table/tbody/tr/td[1]/span[text()='"+bookingId+"']"));
		wLib.scrollToElement(driver, ele1);
		ManageBookingsPage mb = new ManageBookingsPage(driver);
		List<WebElement> allelem =mb.getBookingIds();
		wLib.clickUsingActionsClass(driver,driver.findElement(By.xpath("//span[text()='"+bookingId+"']/../following-sibling::td[8]/descendant::a[2]")));
		driver.switchTo().alert().accept();
		String expectdBookingValue="SUCCESS:Booking Confirm successfully";
		String actualBookingValue=mb.getBookingConfMsg().getText();
		if(actualBookingValue.contains(expectdBookingValue))
			System.out.println("**********The Booking confirmed successfully**********");
		else
			System.out.println("**********Unable to confirm the booking*******");
		adb.getWelcomeDropDown().click();
		adb.getAdminSignOutLink().click();
		alp.getBackToHomeLink().click();

		//user login
		hp.getUserSignInLink().click();
		hp.getUserSignInEmailTbx().sendKeys(username);
		hp.getUserSignInPasswordTbx().sendKeys(passWord);
		hp.getUserSignInButton().click();

		//to verify booking confirmation in "my tour history" model of user
		udb.getMyTourHistorylink().click();
		String userBookingId="#BK15";
		wLib.scrollToElement(driver,driver.findElement(By.xpath("//table/tbody/tr/td[text()='"+userBookingId+"']")));
		String bookingRes = driver.findElement(By.xpath("//tbody/descendant::td[text()='"+userBookingId+"']/following::td[5]")).getText();
		boolean flag=false;
		if(bookingRes.contains("Confirmed"))
			flag=true;
		if(flag)
			System.out.println("*******The admin confirmed booking is updating in 'My tour histroy' model of user and test case pass********");
		else
			System.out.println("*********The admin confirmed booking is not get updating in 'My tour history' of  user model test case fail*******");

		//to raise an issue from user end
		udb.getWriteUsLink().click();
		WebElement issueDropDown = udb.getIssueDropDown();
		wLib.selectElementByVisibleText(driver, issueDropDown, "Booking Issues");
		String description="Unable to book tour package";
		udb.getIssueDescriptionTextArea().sendKeys(description);
		udb.getIssueSubmitBtn().click();
		Thread.sleep(2000);
		String expIssueRes="Info successfully submited";
		String actualIssueRes=udb.getIssueUpdateConfMsg().getText();
		if(actualIssueRes.contains(expIssueRes))
			System.out.println("*********The issue have been raised succesfully*******");
		else
			System.out.println("********Unable to raise the issue******");

		//to logout and close the browser
		udb.getUserLogoutLink().click();
		driver.quit();
	}

}

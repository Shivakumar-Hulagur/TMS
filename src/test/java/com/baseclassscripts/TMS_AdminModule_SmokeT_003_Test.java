package com.baseclassscripts;

import java.io.IOException;
import java.time.Duration;

import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.tms.genericutils.BaseClass;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.HomePage;

public class TMS_AdminModule_SmokeT_003_Test extends BaseClass{
	
	//@Parameters("BROWSER")
	@Test(groups = {"smoke","regression"})
	public void adminSmokeTest() throws IOException
	{
		//to get the title of the page and validate login
		HomePage hp = new HomePage(driver);
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.getAdminUnTbx().sendKeys(pLib.getPropertyKeyValue("adminusername"));
		alp.getAdminPwdTbx().sendKeys(pLib.getPropertyKeyValue("adminpassword"));
		alp.getAdminLoginBtn().click();
		String expectedTitle="TMS | Admin Dashboard";
		String actualTitle = driver.getTitle();
		if(actualTitle.equalsIgnoreCase(expectedTitle))
			System.out.println("Login successful and passed");
		else
			System.out.println("Login unsuccessful and failed");
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		WebElement ele = adb.getWelcomeDropDown();
		wLib.scrollUsingActionsClass(driver, ele);
		ele.click();
		adb.getAdminSignOutLink().click();
	}
}
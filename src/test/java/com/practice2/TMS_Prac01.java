//Write a script to update complaint from admin end, and verify whether the same is updating in user end.
package com.practice2;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.HomePage;
import com.tms.objectrepo.ManageIssuesPage;

public class TMS_Prac01 {
	public static void main(String[] args) throws IOException {
		WebDriverUtility1 wLib=new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		String url=pLib.getPropertyKeyValue("url");
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");
		
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		HomePage hp = new HomePage(driver);
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.getAdminUnTbx().sendKeys(adminUN);
		alp.getAdminPwdTbx().sendKeys(adminPwd);
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.getManageIssues().click();
		String issueId="#0098";
		ManageIssuesPage mngiss = new ManageIssuesPage(driver);
		
		
		
		
	}
}

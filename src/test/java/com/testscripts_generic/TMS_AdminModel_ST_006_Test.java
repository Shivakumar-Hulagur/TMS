package com.testscripts_generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.tms.genericutils.ExcelFileUtilites1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;

public class TMS_AdminModel_ST_006_Test {
	static String adminUN;
	static String adminPwd;
	static WebDriverUtility1 wLib;

	public static void main(String[] args)throws Throwable {

		 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		ExcelFileUtilites1 eLib = new ExcelFileUtilites1();
		
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);

		//to enter the url
		String url=pLib.getPropertyKeyValue("url");
		 adminUN=pLib.getPropertyKeyValue("adminusername");
		 adminPwd=pLib.getPropertyKeyValue("adminpassword");
		String username=pLib.getPropertyKeyValue("username");
		String password=pLib.getPropertyKeyValue("password");
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System/");

		
		//to signup as a new user
		driver.findElement(By.xpath("//a[text()='Sign Up']")).click();

		//to read the data from excel
		HashMap<String, String> map = eLib.getExcelDataByMap("SignUPDetails");
		for( Entry<String, String> set:map.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		driver.findElement(By.xpath("//input[@value='CREATE ACCOUNT']")).click();
		Thread.sleep(2000);
		String expSignUpRes="Info successfully submited";
		String actSignUpRes = driver.findElement(By.xpath("//div[@class='col-md-10 contact-left']/descendant::h4")).getText();
		if(actSignUpRes.contains(actSignUpRes))
			System.out.println("*****Signp successful");
		else
			System.out.println("Signup unsuccessful");

		//to login as admin
		adminLogin(driver);
		
		//to fetch the user login details from execel
		String userEmail = eLib.getExcelData("SignUPDetails", 3, 1);
		String userPassword = eLib.getExcelData("SignUPDetails", 4, 1);		
		String userName = eLib.getExcelData("SignUPDetails", 1, 1);	

		//to verify newly signedup user
		driver.findElement(By.xpath("//span[text()='Manage Users']")).click();
		String expectedUserName=userName;
		WebElement expuser = driver.findElement(By.xpath("//tbody/tr/descendant::td/span[text()='"+expectedUserName+"']"));
		wLib.scrollToElement(driver, expuser);
		String actUserName=expuser.getText();
		if(actUserName.contains(expectedUserName))
			System.out.println("*****The  newly signed in user details are present in manageuser details page*****");
		else
			System.out.println("*****The newly signed user details are not present in mangeuser page****");

		//to logout as admin
		adminLogout(driver);

	
		
		//to sign in as user
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter your Email']")).sendKeys(userEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(userPassword);
		driver.findElement(By.xpath("//input[@value='SIGNIN']")).click();

		//to change the profile details
		String newUserName=userName+userName;
		driver.findElement(By.xpath("//a[text()='My Profile']")).click();
		driver.findElement(By.xpath("//form[@name='chngpwd']/descendant::input[@id='name'][1]")).clear();
		driver.findElement(By.xpath("//form[@name='chngpwd']/descendant::input[@id='name'][1]")).sendKeys(newUserName);
		driver.findElement(By.xpath("//form[@name='chngpwd']/descendant::button[@type='submit']")).click();
		String expProfileRes="SUCCESS:Profile Updated Successfully";
		String actProfileRes = driver.findElement(By.xpath("//div[@class='succWrap']")).getText();
		if(actProfileRes.contains(expProfileRes))
			System.out.println("****The profile details are updated successfully*****");
		else
			System.out.println("****Unable to update profile details*****");
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		
		//to login as admin
		adminLogin(driver);

		//to verify updation of user details
		driver.findElement(By.xpath("//span[text()='Manage Users']")).click();
		String expeUpdateRes=newUserName;
		WebElement ele1 = driver.findElement(By.xpath("//tbody/tr/descendant::td/span[text()='"+expeUpdateRes+"']"));
		wLib.scrollToElement(driver, ele1);
		String actualUpdateRes = ele1.getText();
		if(actualUpdateRes.contains(expeUpdateRes))
			System.out.println("*******The profile details are updated in Manageuser detaials****");
		else
			System.out.println("******The profile details are not updated in ManageUser details******");

		//to logout as admin
		adminLogout(driver);
		driver.quit();

	}
	public static void adminLogin(WebDriver driver)
	{
		//to login as admin
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(adminUN);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(adminPwd);
		driver.findElement(By.xpath("//input[@name='login']")).click();
	}
	public static void adminLogout(WebDriver driver)
	{
		//to logout as admin
		WebElement adminLogout = driver.findElement(By.xpath("//i[@class='fa fa-angle-down']"));
		wLib.scrollToElement(driver, adminLogout);
		wLib.clickUsingActionsClass(driver, adminLogout);
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();
	}
}

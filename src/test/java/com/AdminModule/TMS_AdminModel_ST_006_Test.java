package com.AdminModule;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TMS_AdminModel_ST_006_Test {
	public static void main(String[] args) throws Throwable {
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//to enter the url
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System/");

		Actions a=new Actions(driver);
		//to signup as a new user
			driver.findElement(By.xpath("//a[text()='Sign Up']")).click();

		//to read the data from excel
		String path="src\\test\\resources\\TourPackages_TestScriptData.xlsx";
		FileInputStream fis=new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("SignUPDetails");
		int rowCount=sh.getLastRowNum();
		HashMap<String, String> map = new HashMap();
		for(int i=1;i<=rowCount;i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);

		}
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
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();

		//to verify newly signedup user
		driver.findElement(By.xpath("//span[text()='Manage Users']")).click();
		String expectedUserName="Mahesh";
		WebElement expuser = driver.findElement(By.xpath("//tbody/tr/descendant::td/span[text()='"+expectedUserName+"']"));
		a.scrollToElement(expuser).perform();
		String actUserName=expuser.getText();
		if(actUserName.contains(expectedUserName))
			System.out.println("*****The  newly signed in user details are present in manageuser details page*****");
		else
			System.out.println("*****The newly signed user details are not present in mangeuser page****");

		//to logout as admin
		WebElement adminLogout = driver.findElement(By.xpath("//i[@class='fa fa-angle-down']"));
		a.scrollToElement(adminLogout).perform();
		a.click(adminLogout).perform();
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();

		//to sign in as user
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter your Email']")).sendKeys("Mahi@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Mahi@123");
		driver.findElement(By.xpath("//input[@value='SIGNIN']")).click();

		//to change the profile details
		String newUserName="MaheshMahi";
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
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();

		//to verify updation of user details
		driver.findElement(By.xpath("//span[text()='Manage Users']")).click();
		String expeUpdateRes="MaheshMahi";
		WebElement ele1 = driver.findElement(By.xpath("//tbody/tr/descendant::td/span[text()='"+expeUpdateRes+"']"));
		a.scrollToElement(ele1).perform();
		String actualUpdateRes = ele1.getText();
		if(actualUpdateRes.contains(expeUpdateRes))
			System.out.println("*******The profile details are updated in Manageuser detaials****");
		else
			System.out.println("******The profiel details are not updated in ManageUser details******");

		//to logout as admin
		WebElement adminLogout1 = driver.findElement(By.xpath("//i[@class='fa fa-angle-down']"));
		a.scrollToElement(adminLogout1).perform();
		a.click(adminLogout1).perform();
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();
		driver.quit();

	}
}

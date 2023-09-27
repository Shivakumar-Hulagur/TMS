package com.tms_testng;

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


public class TMS_AdminModel_ST_001_Test1 {

	@Test
	public void tms_AdminModel_Test() throws Throwable {
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
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
		String comment="Hello";
		WebElement ele=driver.findElement(By.xpath("//input[@name='comment']"));
		a.scrollToElement(ele).perform();
		ele.sendKeys(comment);
		driver.findElement(By.xpath("//button[text()='Book']")).click();	
		String text = driver.findElement(By.xpath("//div[@class='succWrap']")).getText();
		if(text.contains("SUCCESS:Booked Successfully"))
			System.out.println("**********The package booking request generated successfully**********");
		else
			System.out.println("*********The package booking is not generated successfully**********");
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();


		//Admin login script
		driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		driver.findElement(By.xpath("//span[text()=' Tour Packages']")).click();
		driver.findElement(By.xpath("//a[text()='Create']")).click();

		//to read the data from the excel file to create tour packages	
		String path="src\\test\\resources\\TourPackages_TestScriptData.xlsx";
		FileInputStream fis=new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet("Create_Package");
		int row_count=sh.getLastRowNum();
		HashMap<String,String> map = new HashMap();
		for(int i=1;i<=row_count;i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value=sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}
		for(java.util.Map.Entry<String, String> set:map.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		File f=new File("C:\\Users\\Dell Inspiron 15\\Documents\\TY\\Admin module\\Tour images\\South_India_Tour.jpg");
		String imgPath = f.getAbsolutePath();
		driver.findElement(By.xpath("//input[@name='packageimage']")).sendKeys(imgPath); 
		WebElement createBtn = driver.findElement(By.xpath("//button[text()='Create']"));
		a.scrollToElement(createBtn).perform();
		a.click(createBtn).perform();
		String expectedRes="SUCCESS:Package Created Successfully";
		String actualRes=driver.findElement(By.xpath("//div[@class='succWrap']")).getText();
		if(actualRes.contains(expectedRes))
			System.out.println("***********The Package is created successfully************");
		else
			System.out.println("*********The Package is not created unsucessful***********");
		driver.findElement(By.xpath("//span[text()=' Tour Packages']")).click();
		driver.findElement(By.xpath("//a[text()='Manage']")).click();
		String modifyPackName="South India Tour";
		WebElement modificationPack = driver.findElement(By.xpath("//span[@class='bt-content' and text()='"+modifyPackName+"']"));
		a.scrollToElement(modificationPack).perform();
		driver.findElement(By.xpath("//span[@class='bt-content' and text()='"+modifyPackName+"']/ancestor::tr/descendant::button[text()='View Details']")).click();
		String modificationFieldName="packageprice";
		String modificationValue="500";
		driver.findElement(By.xpath("//input[@name='"+modificationFieldName+"']")).clear();
		driver.findElement(By.xpath("//input[@name='"+modificationFieldName+"']")).sendKeys(modificationValue);
		WebElement updateBtn = driver.findElement(By.xpath("//button[text()='Update']"));
		a.scrollToElement(updateBtn).perform();
		a.click(updateBtn).perform();
		String expectedUpdateValue="SUCCESS:Package Updated Successfully";
		String actualUpdateValue=driver.findElement(By.xpath("//div[@class='succWrap']")).getText();
		if(actualUpdateValue.contains(expectedUpdateValue))
			System.out.println("***********The package details are updated successfully**********");
		else
			System.out.println("************Unable to update the package details***********");

		//to confirm the booking request
		String bookingId="#BK-15";	
		driver.findElement(By.xpath("//span[text()='Manage Booking']")).click();
		WebElement ele1 = driver.findElement(By.xpath("//table/tbody/tr/td[1]/span[text()='"+bookingId+"']"));
		a.moveToElement(ele1).perform();
	//	List<WebElement> allelem = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		a.click(driver.findElement(By.xpath("//span[text()='"+bookingId+"']/../following-sibling::td[8]/descendant::a[2]"))).perform();
		driver.switchTo().alert().accept();
		String expectdBookingValue="SUCCESS:Booking Confirm successfully";
		String actualBookingValue=driver.findElement(By.xpath("//div[@class='succWrap']")).getText();
		if(actualBookingValue.contains(expectdBookingValue))
			System.out.println("**********The Booking confirmed successfully**********");
		else
			System.out.println("**********Unable to confirm the booking*******");
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.findElement(By.xpath("//a[text()='Back to home']")).click();

		//user login
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		//to read the data from property file
		String path1="src\\test\\resources\\CommonData.property";
		FileInputStream fis1=new FileInputStream(path1);
		Properties p=new Properties();
		p.load(fis1);
		String userUN=p.getProperty("username");
		String userPwd=p.getProperty("password");
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@id='email']")).sendKeys(userUN);
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@id='password']")).sendKeys(userPwd);
		driver.findElement(By.xpath("//h3[text()='Signin with your account ']/following-sibling::input[@name='signin']")).click();

		//to verify booking confirmation in "my tour history" model of user
		driver.findElement(By.xpath("//a[text()='My Tour History']")).click();
		String userBookingId="#BK15";
		a.moveToElement(driver.findElement(By.xpath("//table/tbody/tr/td[text()='"+userBookingId+"']"))).perform();
		String bookingRes = driver.findElement(By.xpath("//tbody/descendant::td[text()='"+userBookingId+"']/following::td[5]")).getText();
		boolean flag=false;
		if(bookingRes.contains("Confirmed"))
			flag=true;
		if(flag)
			System.out.println("*******The admin confirmed booking is updating in 'My tour histroy' model of user and test case pass********");
		else
			System.out.println("*********The admin confirmed booking is not get updating in 'My tour history' of  user model test case fail*******");

		//to raise an issue from user end
		driver.findElement(By.xpath("//a[contains(text(),'Write Us ')]")).click();
		WebElement issueDropDown = driver.findElement(By.xpath("//select[@name='issue']"));
		Select s=new Select(issueDropDown);
		s.selectByValue("Booking Issues");
		String description="Unable to book tour package";
		driver.findElement(By.xpath("//input[@name='description']")).sendKeys(description);
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		Thread.sleep(2000);
		String expIssueRes="Info successfully submited";
		String actualIssueRes=driver.findElement(By.xpath("//div[@class='con-top animated wow fadeInUp animated animated']")).getText();
		if(actualIssueRes.contains(expIssueRes))
			System.out.println("*********The issue have been raised succesfully*******");
		else
			System.out.println("********Unable to raise the issue******");

		//to logout and close the browser
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		driver.quit();
	}

}

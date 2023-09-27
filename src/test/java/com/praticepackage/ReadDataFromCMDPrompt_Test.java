package com.praticepackage;

/*import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/*public class ReadDataFromCMDPrompt_Test {
 @Test
   public void readTest()
   {
	 System.out.println("Reading Data from Command Prompt");
   }
 	@Test
    public void gettingProperty()
    {
    	String url=System.getProperty("url");
    	String un=System.getProperty("username");
    	String pwd=System.getProperty("password");
    	
    	//to launch the browser
    	WebDriver driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	String expectedTitle="TMS | Admin Dashboard";
    	driver.get(url);
    	driver.findElement(By.xpath("//a[text()='Admin Login']")).click();
    	driver.findElement(By.xpath("//input[@name='username']")).sendKeys(un);
    	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
    	driver.findElement(By.xpath("//input[@name='login']")).click();
    	String actualTitle=driver.getTitle();
    	/*SoftAssert s=new SoftAssert();
    	s.assertEquals(actualTitle, expectedTitle);
    	s.assertAll();*/
    //	Assert.assertEquals(actualTitle, expectedTitle);
    //	driver.quit();
 //   }
//}

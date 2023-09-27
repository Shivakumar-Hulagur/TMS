package com.testscripts_generic;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.tms.genericutils.ExcelFileUtilites1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.genericutils2.ExcelFileUtilities2;





public class IT_Enquiry_03_Test {
	public static void main (String[] args) throws IOException
	{
		WebDriverUtility1 wLib = new WebDriverUtility1();	 
		ExcelFileUtilites1 eLib = new ExcelFileUtilites1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();

		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.deleteAllCookies(driver);
		wLib.implicitWait(driver, 10);

		String url = pLib.getPropertyKeyValue("url");
		driver.get(url);
		String expectedValue=null;

		// to generate a enquiry
		driver.findElement(By.xpath("//a[normalize-space(text())='Enquiry']")).click();
		HashMap<String, String> res = eLib.getExcelDataByMap("Enquiry");
		int j=0;
		for(Entry<String, String> s:res.entrySet())
		{
			if(!s.getKey().contains("description"))
			{
				try {
					driver.findElement(By.xpath("//form[@name='enquiry']/descendant::input[@name='"+s.getKey()+"']")).sendKeys(s.getValue());
					if(j==0)
						expectedValue=s.getValue();
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
				driver.findElement(By.xpath("//form[@name='enquiry']/descendant::textarea[@name='"+s.getKey()+"']")).sendKeys(s.getValue());
			j++;

		}		
		driver.findElement(By.xpath("//form[@name='enquiry']/descendant::button[@name='submit1']")).click();

		//to verify whether the enquiry is properly generated or not
		driver.findElement(By.linkText("Admin Login")).click();
		String adminUN = pLib.getPropertyKeyValue("adminusername");
		String adminPwd = pLib.getPropertyKeyValue("adminpassword");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(adminUN);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(adminPwd);
		driver.findElement(By.xpath("//input[@name='login']")).click();
		driver.findElement(By.xpath("//span[text()='Manage Enquiries']")).click();
		driver.findElement(By.xpath("//span[@class='fa fa-bars']")).click();
		WebElement btmElement = driver.findElement(By.xpath("//div[@class='copyrights']"));
		wLib.scrollToElement(driver, btmElement);
		boolean flag=false;
		List<WebElement> alle = driver.findElements(By.xpath("//tbody/tr/td[2]/span"));
		// System.out.print(alle.size());
		for(int i=0;i<alle.size();i++)
		{
			if(i==alle.size()-1)
			{
				//System.out.println(alle.get(i).getText());
				if(alle.get(i).getText().contains(expectedValue))
				{

					flag=true;
					break;
				}
			}
		}
		if(flag)
			System.out.println("The enquiry generated successfully and pass");
		else
			System.out.println("Unable to genrate enquiry and fail");
		WebElement logoutEle = driver.findElement(By.xpath("//i[@class='fa fa-angle-down']"));
		wLib.scrollToElement(driver, logoutEle);
		wLib.clickUsingActionsClass(driver, logoutEle);
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();
		driver.quit();
	}
}

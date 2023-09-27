package com.tms.testscripts_pom;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.tms.genericutils.ExcelFileUtilites1;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.EnquiryPage;
import com.tms.objectrepo.HomePage;
import com.tms.objectrepo.ManageEnquiriesPage;
import com.tms.objectrepo.UserDashBoardPage;

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
		HomePage hp = new HomePage(driver);
		hp.getEnquiryLink().click();
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
		EnquiryPage enqp = new EnquiryPage(driver);
		enqp.getEnquirySubmitBtn().click();

		//to verify whether the enquiry is properly generated or not
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		String adminUN = pLib.getPropertyKeyValue("adminusername");
		String adminPwd = pLib.getPropertyKeyValue("adminpassword");
		alp.getAdminUnTbx().sendKeys(adminUN);
		alp.getAdminPwdTbx().sendKeys(adminPwd);
		alp.getAdminLoginBtn().click();
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.getManageEnquiriesLink().click();
		adb.getOptionsHamburger().click();
		ManageEnquiriesPage menq = new ManageEnquiriesPage(driver);
		wLib.scrollToElement(driver, menq.getCopyRightsTab());
		boolean flag=false;
		List<WebElement> alle =menq.getAllEnquiriesList();
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
		wLib.scrollToElement(driver, adb.getWelcomeDropDown());
		wLib.clickUsingActionsClass(driver, adb.getWelcomeDropDown());
		adb.getAdminSignOutLink().click();
		driver.quit();
	}
}

/*1.Login as admin
 * 2.Create a tour package
 * 3.Modify same tour package 
 * 4.Verify the tour package is modified or not.
 */
package com.baseclassscripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.tms.genericutils.BaseClass;
import com.tms.genericutils.ExcelFileUtilites1;
import com.tms.genericutils.IPathConstants1;
import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.CreateTourPackagePage;
import com.tms.objectrepo.ManageTourPackagePage;

import testngpractice.DPFromExcelUtility;

public class VerifyModificationOfTourPackage extends BaseClass {

	/*@Test
	public void verifyTourPackage() throws EncryptedDocumentException, IOException
	{
		//to create a new tour package
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.getTourPackagesDropMenu().click();
		adb.getCreatTourPack().click();
		ExcelFileUtilites1 eLIb = new ExcelFileUtilites1();
		HashMap<String, String> map = eLib.getExcelDataByMap("Create_Package");
		String packageName=null;
		for(Entry<String, String> s:map.entrySet())
		{
			if(s.getKey().contains("packagedetails"))
				driver.findElement(By.xpath("//textarea[@name='packagedetails']")).sendKeys(s.getValue());
			else {
			driver.findElement(By.xpath("//input[@name='"+s.getKey()+"']")).sendKeys(s.getValue());
			if(s.getKey().contains("packagename"))
				packageName=s.getValue();
			}
			
		}
		CreateTourPackagePage ctp = new CreateTourPackagePage(driver);
		ctp.uploadPicture();
		wLib.scrollUsingActionsClass(driver, ctp.getCreateTourPackageBtn());
		ctp.getCreateTourPackageBtn().click();
		String res = ctp.getPackageCreationOrBookingOrCancelConfMsg().getText();
		if(res.contains("Created Success"))
			System.out.println("The package is cerated successfully");
		else
			System.out.println("The package is not created successfully");
		
		//to modify the same packaage 
		
		//to modify the same tour package
		adb.getTourPackagesDropMenu().click();
		adb.getManageTourPack().click();
		ManageTourPackagePage mtp = new ManageTourPackagePage(driver);
		mtp.clickOnParticularPackage(driver, packageName);
		
	}*/
}

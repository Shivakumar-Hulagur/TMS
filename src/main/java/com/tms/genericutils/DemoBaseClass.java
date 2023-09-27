package com.tms.genericutils;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.tms.objectrepo.AdminDashBoardPage;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.HomePage;

public class DemoBaseClass {


	public WebDriverUtility1 wLib = new WebDriverUtility1();
	public DatabaseUtilities dLib = new DatabaseUtilities();
	public PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
	public ExcelFileUtilites1 eLib = new ExcelFileUtilites1();
	public  static WebDriver driver=null;
	public static WebDriver sDriver;

	@BeforeSuite(alwaysRun = true)
	public void configBS() throws SQLException
	{
		dLib.getDBConnect();
		System.out.println("--Connection to DB--");
	}
	//@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true)
	public void configBc() throws IOException
	{
		String BROWSER = pLib.getPropertyKeyValue("browser");
		if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else
			System.out.println("--Invalid Browser--");
		sDriver=driver;
	}
	@BeforeMethod(alwaysRun = true)
	public void configBM() throws IOException
	{
		String URL= pLib.getPropertyKeyValue("url");
		String adminUN = pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");
		driver.get(URL);
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);
		HomePage hp = new HomePage(driver);
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.setLogin(adminUN, adminPwd);
	}
	@AfterMethod(alwaysRun = true)
	public void configAM()
	{
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.setSignout(driver);
	}
	@AfterClass(alwaysRun = true)
	public void configAC()
	{
		driver.quit();
	}
	@AfterSuite(alwaysRun = true)
	public void configAS() throws SQLException
	{
		dLib.closeDBConnection();
	}
}

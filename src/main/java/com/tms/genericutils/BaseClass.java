package com.tms.genericutils;


import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v113.browser.Browser;
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

public class BaseClass {

	public WebDriverUtility1 wLib=new WebDriverUtility1();
	public ExcelFileUtilites1 eLib=new ExcelFileUtilites1();
	public PropertyFileUtilites1 pLib=new PropertyFileUtilites1();
	public DatabaseUtilities dLib=new DatabaseUtilities();
	public WebDriver driver;
	public static WebDriver sDriver;

	@BeforeSuite(alwaysRun = true)
	public void configBS() throws SQLException
	{
		dLib.getDBConnect();
		System.out.println("---Connect to DB---");	
	}
	public BaseClass() {
		// TODO Auto-generated constructor stub
	}

	//@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void configBC() throws IOException
	{
		String BROWSER = pLib.getPropertyKeyValue("browser");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			sDriver=driver;
			System.out.println("Launching Chrome browser");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			sDriver=driver;
			System.out.println("Launching Fire fox browser");
		}
		else if(BROWSER.equalsIgnoreCase("edge")) 
		{
			driver=new EdgeDriver();
			sDriver=driver;
			System.out.println("Launching edge browser");
		}

		else
			System.out.println("--Invalid Browser----");
		String URL = pLib.getPropertyKeyValue("url");
		driver.get(URL);
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);
		System.out.println("--Browser opened");
	}
	/*@BeforeMethod
	public void configBM() throws IOException
	{
		String adminUN = pLib.getPropertyKeyValue("adminusername");
		String adminPwd = pLib.getPropertyKeyValue("adminpassword");
		AdminLoginPage alp = new AdminLoginPage(driver);
		HomePage hp = new HomePage(driver);
		hp.getAdminLoginLink().click();
		alp.setLogin(adminUN, adminPwd);
		System.out.println("--Admin Login done--");
	}
	@AfterMethod
	public void configAM()
	{
		AdminDashBoardPage adb = new AdminDashBoardPage(driver);
		adb.setSignout(driver);
		System.out.println("--Logout Done---");
	}*/
	@AfterClass(alwaysRun = true)
	public void configAC()
	{
		driver.quit();
		System.out.println("--Browser closed--");
	}
	@AfterSuite(alwaysRun = true)
	public void configAS() throws SQLException
	{
		dLib.closeDBConnection();
		System.out.println("--Disconnected DB--");
	}
}

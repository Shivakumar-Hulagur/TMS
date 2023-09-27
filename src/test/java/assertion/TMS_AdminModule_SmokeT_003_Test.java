package assertion;


import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tms.genericutils.BaseClass;
import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.AdminLoginPage;
import com.tms.objectrepo.HomePage;
@Listeners(com.tms.genericutils.ListenerImplimentation.class)
public class TMS_AdminModule_SmokeT_003_Test extends BaseClass {
	@Test
	public void verifyAdminLogin() throws IOException
	{
		WebDriverUtility1 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		//to launch the browser
//		WebDriver driver=new ChromeDriver();
//		wLib.maximizeBrowser(driver);
//		wLib.implicitWait(driver, 10);

		//to enter the url
		String url=pLib.getPropertyKeyValue("url");
		driver.get(url);
		String adminUN=pLib.getPropertyKeyValue("adminusername");
		String adminPwd=pLib.getPropertyKeyValue("adminpassword");
		//to click on admin login link
		HomePage hp = new HomePage(driver);
		hp.getAdminLoginLink().click();
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.getAdminUnTbx().sendKeys(adminUN);
		alp.getAdminPwdTbx().sendKeys(adminPwd);
		alp.getAdminLoginBtn().click();

		//to get the title of the page and validate login
		String expectedTitle="TMS | Admin Dashboard";
		Assert.fail();
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
//		driver.quit();
	}
}
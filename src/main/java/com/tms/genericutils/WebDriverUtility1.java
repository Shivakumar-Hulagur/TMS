package com.tms.genericutils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class provides the generic methods for WebDriver methods
 * @author Shivakumar
 *
 */
public class WebDriverUtility1 {
	/** 
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * This method is used to minimize the browser
	 * @param driver
	 */
	public void minimizeBrowser(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	/**
	 * This methos is used to delete all the cockies
	 * @param driver
	 */
	public void deleteAllCookies(WebDriver driver)
	{
		driver.manage().deleteAllCookies();
	}
	/**
	 *  This method is used to synchronize the script using implicit wait method
	 * @param driver
	 * @param duration
	 */
	public void implicitWait(WebDriver driver,int duration)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}
	/**
	 * This method is used to synchronize the script based on presence of title
	 * @param driver
	 * @param duration
	 * @param expectedTitle
	 */
	public void explictWaitBasedOnTitle(WebDriver driver,int duration,String expectedTitle)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.titleContains(expectedTitle));
	}
	/** 
	 * This method is used to synchronize the script based on presence of url 
	 * @param driver
	 * @param duration
	 * @param element
	 */
	public void explictWaitBasedOnPresenceOfURL(WebDriver driver,int duration,String expectedUrl)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.urlContains(expectedUrl));
	}
	/** 
	 * This is method is used to synchronize the script until the presence of alert popup
	 * @param driver
	 * @param duration
	 */
	public void explictWaitBasedOnPresenceOfAlertPopUp(WebDriver driver,int duration)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	/**
	 * This method is used to switch the control to other window based on url
	 * @param driver
	 * @param expecteUrl
	 */
	public void switchToWindowBasedOnUrl(WebDriver driver,String expecteUrl)
	{
		Set<String> allWind = driver.getWindowHandles();
		Iterator<String> it = allWind.iterator();
		while(it.hasNext())
		{
			String wind = it.next();
			driver.switchTo().window(wind);
			String currentUrl=driver.getCurrentUrl();
			if(currentUrl.contains(expecteUrl))
			{
				break;
			}
		}
	}
	
	/**
	 * This method is used to accept the alert popup
	 * @param driver
	 * @param expectedMsg
	 */
	public void acceptAlertPopUp(WebDriver driver,String expectedMsg)
	{
		Alert alt = driver.switchTo().alert();
		String actualMsg = alt.getText();
		if(actualMsg.contains(expectedMsg))
			System.out.println("Alert is verified");
		else
			System.out.println("Alert is not verified");
		alt.accept();
	}
	/**
	 * This method is used to dismiss the alert pop up
	 * @param driver
	 * @param expectedMsg
	 */
	public void dismissPopUp(WebDriver driver,String expectedMsg)
	{
		Alert alt = driver.switchTo().alert();
		String actualMsg = alt.getText();
		if(actualMsg.contains(expectedMsg))
			System.out.println("Alert is verified");
		else
			System.out.println("Alert is not verified");
		alt.dismiss();
	}
	/**
	 * This method returns the text present in alert pop up
	 * @param driver
	 * @return
	 */
	public String getTextOfAlertPopUp(WebDriver driver)
	{
		return 	driver.switchTo().alert().getText();
	}
	/**
	 * This method is used to send the values to the alert pop up
	 * @param driver
	 * @param value
	 */
	public void sendTextToAlertPopUp(WebDriver driver,String value)
	{
		driver.switchTo().alert().sendKeys(value);
	}
	/**
	 * This method is used to select the element based on index
	 * @param driver
	 * @param element
	 * @param index
	 */
	public void selectElementByIndex(WebDriver driver,WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * This method is used to select the element based on value
	 * @param driver
	 * @param element
	 * @param value
	 */
	public void selectElementByValue(WebDriver driver,WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	/**
	 * This method is used to select the element based on visible text
	 * @param driver
	 * @param element
	 * @param text
	 */
	public void selectElementByVisibleText(WebDriver driver,WebElement element,String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	/**
	 * This method is used to perform the mouseover action on passed element
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * This method is used to perform the contextclick action on passed element
	 * @param driver
	 * @param element
	 */
	public void contextClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver); 
		act.contextClick(element).perform();;		
	}
	/**
	 * This method is used to perform the drag and drop operation
	 * @param driver
	 * @param source
	 * @param destination
	 */
	public void dragAndDrop(WebDriver driver,WebElement source,WebElement destination)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(source, destination).perform();
	}
	/**
	 * This method is used to peroform the doubleclick actions
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * This method is used to scroll to particular element
	 * @param driver
	 * @param element
	 */
	public void scrollToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	/**
	 * This method is used to take the screenshot of webpage
	 * @param driver
	 * @param screenShotName
	 * @return
	 */
	public String takeScreenShot(WebDriver driver,String screenShotName)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = src.getAbsolutePath();
		File Dest=new File("./ScreenShot/"+screenShotName+".png");
		try {
			Files.copy(src, Dest);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return path;
	}
	/**
	 * This method is used to switch the driver control into the frame based on index  
	 * @param driver
	 * @param index
	 */
	public void switchToFrameBasedOnIndex(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to switch the driver control into frame based on name or id. 
	 * @param driver
	 * @param nameOrId
	 */
	public void swicthToFrameBasedOnNameOrId(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	/**
	 * This method is used to switch the control into frame based on webelement
	 * @param driver
	 * @param element
	 */
	public void switchToFrameBasedOnElement(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	/**
	 * This method is used to switch the control to parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	/**
	 * This method is used to switch the control to default frame
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is used to ignore the NoSuchElementException 
	 * @param driver
	 * @param duration
	 */
	public void ignoreNoSuchElementException(WebDriver driver,int duration)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.ignoring(NoSuchElementException.class);
	}
	/**
	 * This methos is used to ignore the ElementNotInteractableException
	 * @param driver
	 * @param duration
	 */
	public void ignoreElementNotInteractableException(WebDriver driver,int duration)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.ignoring(ElementNotInteractableException.class);
	}
	/**
	 * This method helps to perform custom wait type synchronization
	 * @param element
	 * @throws InterruptedException
	 */
	public void customWait(WebElement element) throws InterruptedException
	{
		int count=0;
		while(count<20)
		{
			try {
				element.click();
			} catch (Exception e) {
				// TODO: handle exception
				Thread.sleep(1000);
				count++;
			}
		}
	}
	/**
	 * This method is used to send the value to hidden element
	 * @param driver
	 * @param id
	 * @param value
	 */
	public void handleHiddenElementById(WebDriver driver,String id,String value)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("document.getElementById('"+id+"').value='"+value+"'");
	}
	/**
	 * This method is used to press the enter after sending values using sendkeys to given webelement
	 * @param value
	 * @param element
	 */
	public void enterAfetrSendKeys(String value,WebElement element)
	{
		element.sendKeys(value+Keys.ENTER);
	}
	/**
	 * This method is used to press the Enter key
	 * @param driver
	 * @throws AWTException
	 */
	public void enterKey(WebDriver driver) throws AWTException
	{
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);
	}
	/**
	 * This methos is used to press Control key
	 * @param driver
	 * @throws AWTException
	 */
	public void controlKey(WebDriver driver) throws AWTException
	{
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
	}
	/**
	 * This method is used to scroll the control to 
	 * @param driver
	 * @param xAxis
	 * @param yAxis
	 */
	public void scrollByXAndYAxis(WebDriver driver,int xAxis,int yAxis)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+xAxis+","+yAxis+")", "");
	}
	public void scrollByXAxisToWebElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y=element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")",element);
	}
	public void clickUsingActionsClass(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.click(element).perform();
	}
	public void scrollUsingActionsClass(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}
	/** 
	 * This method is used to switch the driver control to another window based on title
	 * @param driver
	 * @param expTitle
	 */
	public void switchToWindBasedOnTitle(WebDriver driver,String expTitle)
	{
		Set<String> allWind = driver.getWindowHandles();
		for(String wind:allWind)
		{
			driver.switchTo().window(wind);
			String actTitle = driver.getTitle();
			if(actTitle.contains(expTitle))
				break;
			
		}
	}
}

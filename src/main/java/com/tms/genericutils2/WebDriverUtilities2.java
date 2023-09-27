package com.tms.genericutils2;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.mysql.cj.exceptions.StreamingNotifiable;

public class WebDriverUtilities2 {

	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public void maximize(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	public void minimize(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	public void deleteAllCockies(WebDriver driver)
	{
		driver.manage().deleteAllCookies();
	}
	public void implicitWait(WebDriver driver, int duration)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}
	public void explicitWaitBasedOnPresenceOfTitle(WebDriver driver,int duration,String expTitle)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.titleContains(expTitle));
	}
	public void explicitWaitBasedOnVisibilityOfElement(WebDriver driver,int duration,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}	
	public void explicitWaitBasedOnPresenceOfElementLocatedByid(WebDriver driver,String id,int duartion)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duartion));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	}
	public void explicitWaitBasedOnPresenceOfElementLocatedByName(WebDriver driver,String name,int duration)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
	}
	public void explicitWaitBasedOnPresenceOfElementLocatedByXpath(WebDriver driver,String xpath,int duration)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	public void selectByIndex(WebDriver driver,WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void selectByVisibleText(WebDriver driver,WebElement element,String visibleText)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	public void selectByValue(WebDriver driver,WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	public void deselectByIndex(WebDriver driver,WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.deselectByIndex(index);
	}
	public void deselectByVisibleText(WebDriver driver,WebElement element,String visibleText)
	{
		Select sel = new Select(element);
		sel.deselectByVisibleText(visibleText);
	}
	public void deselectByValue(WebDriver driver,WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.deselectByValue(value);
	}
	public void mouseHover(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void contextClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	public void dragAndDrop(WebDriver driver,WebElement source,WebElement target)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(source,target).perform();
	}
	public void switchToWindowBasedOnUrl(WebDriver driver,String url)
	{
		Set<String> allWind = driver.getWindowHandles();
		Iterator<String> it = allWind.iterator();
		driver.switchTo().window(url);
		while(it.hasNext())
		{
			String wind = it.next();
			if(wind.contains(url))
				break;
		}
	}
	public void switchToWindowBasedOnTitle(WebDriver driver,String expTitle)
	{
		Set<String> allWind = driver.getWindowHandles();
		Iterator<String> it = allWind.iterator();
		while(it.hasNext())
		{
			String wind = it.next();
			String actTitle = driver.switchTo().window(wind).getTitle();
			if(actTitle.equalsIgnoreCase(expTitle))
				break;
		}
	}
	public void switchToFrameBasedOnIndex(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrameBasedOnNameOrId(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	public void switchToFrameBasedOnWebElement(WebDriver driver,WebElement element) 
	{
		driver.switchTo().frame(element);
	}
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	public void switchToDefaultContet(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	public void aceptAlert(WebDriver driver,String expText)
	{
		Alert alt = driver.switchTo().alert();
		String actText = alt.getText();
		if(actText.contains(expText))
			System.out.println("Alert is verified");
		else
			System.out.println("Alert is not verified");
		alt.accept();
	}
	public void dismissAlert(WebDriver driver,String expText)
	{
		Alert alt = driver.switchTo().alert();
		String actText = alt.getText();
		if(actText.contains(expText))
			System.out.println("Alert is verified");
		else
			System.out.println("Alert is not verified");
		alt.dismiss();
	}
	public void sendTextToAlert(WebDriver driver,String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	public void customWait(WebDriver driver,WebElement element) throws InterruptedException
	{
		int count=0;
		while(count<20)
		{
			try {
				element.click();
			} catch (Exception e) {
				Thread.sleep(1000);
				count++;
			}
		}	
	}
	public void moveToParticularElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	public void toTakeScreenShot(WebDriver driver,String fileName)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/"+fileName+".png");
		try {
			Files.copy(src, dest);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

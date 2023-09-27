package com.tms.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tms.genericutils.WebDriverUtility1;
import com.tms.genericutils2.WebDriverUtilities2;

public class ManageTourPackagePage {

	@FindBy(xpath="//button[text()='Update']")
	private WebElement updateBtn;
	
	@FindBy(xpath="//div[@class='succWrap']")
	private WebElement confMsg;
	
	public ManageTourPackagePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getUpdateBtn() {
		return updateBtn;
	}

	public WebElement getConfMsg() {
		return confMsg;
	}
	public void clickOnParticularPackage(WebDriver driver,String  packageName)
	{
		WebElement ele = driver.findElement(By.xpath("//span[normalize-space(text())='"+packageName+"']/following::td[5]/descendant::a"));
		 WebDriverUtility1 wLib = new WebDriverUtility1();
		wLib.scrollUsingActionsClass(driver, ele);
		ele.click();
	}
}

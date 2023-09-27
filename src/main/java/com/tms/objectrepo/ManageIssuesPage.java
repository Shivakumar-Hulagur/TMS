package com.tms.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tms.genericutils.WebDriverUtility1;

public class ManageIssuesPage extends WebDriverUtility1{

	@FindBy(xpath="//span[text()='#0098']/../following::td[7]/descendant::a")
	private WebElement issueViewLink;
	
	public ManageIssuesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//bussiness libraries
	public void setUpdateComplaint(WebDriver driver,String issueId,String childWindUrl) {
		WebElement issueEle = driver.findElement(By.xpath("//span[text()='"+issueId+"']/../following::td[7]/descendant::a"));
		scrollUsingActionsClass(driver, issueEle);
		issueEle.click();
		}

}

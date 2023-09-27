package com.tms.objectrepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageEnquiriesPage {

	//Declaration
	@FindBy(xpath="//div[@class='copyrights']")
	private WebElement copyRightsTab;
	
	@FindBy(xpath="//tbody/tr/td[2]/span")
	private List<WebElement> allEnquiriesList;
	
	public ManageEnquiriesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCopyRightsTab() {
		return copyRightsTab;
	}

	public List<WebElement> getAllEnquiriesList() {
		return allEnquiriesList;
	}
	
}

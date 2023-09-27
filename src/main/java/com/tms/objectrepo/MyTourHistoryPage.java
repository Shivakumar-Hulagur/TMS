package com.tms.objectrepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyTourHistoryPage {

	
	@FindBy(xpath="//div[@class='copy-right']")
	private WebElement copyRightTab;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	private List<WebElement> userBookingId;
	
	public MyTourHistoryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getCopyRightTab() {
		return copyRightTab;
	}

	public  List<WebElement> getUserBookingId() {
		return  userBookingId;
	}
	
}

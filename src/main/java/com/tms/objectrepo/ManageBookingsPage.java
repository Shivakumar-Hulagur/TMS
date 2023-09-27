package com.tms.objectrepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageBookingsPage {

	@FindBy(xpath="//div[@class='succWrap' and contains(text(),':Booking Confirm successfully') ]")
	private WebElement bookingConfMsg;
	
	@FindBy(xpath="//table/tbody/tr/td[1]")
	private List<WebElement> bookingIds;

	public ManageBookingsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getBookingConfMsg() {
		return bookingConfMsg;
	}

	public List<WebElement> getBookingIds() {
		return bookingIds;
	}
	
	
}


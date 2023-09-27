package com.tms.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookTourPackagePage {

	@FindBy(id="datepicker")
	private WebElement fromDateCalenderPopUp;
	
	@FindBy(xpath="//a[@data-handler='next']")
	private WebElement nextMonthSelector;
	
	@FindBy(id="datepicker1")
	private WebElement toDateCalendarPopUp;
	
	@FindBy(xpath="//button[text()='Book']")
	private WebElement commentTextArea;
	
	@FindBy(xpath="//button[text()='Book']")
	private WebElement bookBtn;
	
	@FindBy(xpath="//div[@class='succWrap']")
	private WebElement bookingRequestConfMeg;
	
	public BookTourPackagePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getFromDateCalenderPopUp() {
		return fromDateCalenderPopUp;
	}

	public WebElement getNextMonthSelector() {
		return nextMonthSelector;
	}

	public WebElement getToDateCalendarPopUp() {
		return toDateCalendarPopUp;
	}

	public WebElement getCommentTextArea() {
		return commentTextArea;
	}

	public WebElement getBookBtn() {
		return bookBtn;
	}

	public WebElement getBookingRequestConfMeg() {
		return bookingRequestConfMeg;
	}

	public WebElement getCreateTourPackageBtn() {
		return createTourPackageBtn;
	}

	@FindBy(xpath="//a[text()='Create']")
	private WebElement createTourPackageBtn;
	
	
	
}

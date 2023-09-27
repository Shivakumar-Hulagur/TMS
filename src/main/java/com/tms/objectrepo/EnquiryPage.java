package com.tms.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnquiryPage {

	@FindBy(xpath="//form[@name='enquiry']/descendant::input[@name='fname']")
	private WebElement enquiryFirstName;
	
	@FindBy(xpath="//form[@name='enquiry']/descendant::input[@name='email']")
	private WebElement enquiryEmailId;
	
	@FindBy(xpath="//form[@name='enquiry']/descendant::input[@name='mobileno']")
	private WebElement enquiryMobileNum;
	
	@FindBy(xpath="//form[@name='enquiry']/descendant::input[@name='subject']")
	private WebElement enquirySubject;
	
	@FindBy(xpath="//form[@name='enquiry']/descendant::textarea[@name='description']")
	private WebElement enquiryDescription;
	
	@FindBy(xpath="//form[@name='enquiry']/descendant::button[@name='submit1']")
	private WebElement enquirySubmitBtn;
	
	public EnquiryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getEnquiryFirstName() {
		return enquiryFirstName;
	}

	public WebElement getEnquiryEmailId() {
		return enquiryEmailId;
	}

	public WebElement getEnquiryMobileNum() {
		return enquiryMobileNum;
	}

	public WebElement getEnquirySubject() {
		return enquirySubject;
	}

	public WebElement getEnquiryDescription() {
		return enquiryDescription;
	}

	public WebElement getEnquirySubmitBtn() {
		return enquirySubmitBtn;
	}
	
}

package com.tms.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDashBoardPage {

	@FindBy(xpath="//a[contains(text(),'Logout')]")
	private WebElement userLogoutLink;
	
	@FindBy(xpath="//a[text()='Tour Packages']")
	private WebElement tourPackagesLink;
	
	@FindBy(xpath="//a[contains(text(),'Write Us ')]")
	private WebElement writeUsLink;
	
	@FindBy(xpath="//select[@name='issue']")
	private WebElement issueDropDown;
	
	@FindBy(xpath="//input[@name='description']")
	private WebElement issueDescriptionTextArea;
	
	@FindBy(xpath = "//button[@name='submit']")
	private WebElement issueSubmitBtn;
	
	@FindBy(xpath="//div[@class='con-top animated wow fadeInUp animated animated']")
	private WebElement issueUpdateConfMsg;
	
	@FindBy(xpath ="//div[@class='succWrap']")
	private WebElement profileUpdateConfMsg;
	
	public WebElement getIssueUpdateConfMsg() {
		return issueUpdateConfMsg;
	}

	public WebElement getProfileUpdateConfMsg() {
		return profileUpdateConfMsg;
	}

	@FindBy(xpath="//a[text()='My Profile']")
	private WebElement myProfilelink;
	
	@FindBy(xpath="//form[@name='chngpwd']/descendant::input[@id='name'][1]")
	private WebElement profileFirstNameTbx;
	
	@FindBy(xpath="//form[@name='chngpwd']/descendant::button[@type='submit']")
	private WebElement  updatedProfileSubmitBtn;
	
	@FindBy(xpath="//a[text()='My Tour History']")
	private WebElement myTourHistorylink;
	
	
	public WebElement getMyTourHistorylink() {
		return myTourHistorylink;
	}

	public UserDashBoardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getUserLogoutLink() {
		return userLogoutLink;
	}

	public WebElement getTourPackagesLink() {
		return tourPackagesLink;
	}

	public WebElement getWriteUsLink() {
		return writeUsLink;
	}

	public WebElement getIssueDropDown() {
		return issueDropDown;
	}

	public WebElement getIssueDescriptionTextArea() {
		return issueDescriptionTextArea;
	}

	public WebElement getIssueSubmitBtn() {
		return issueSubmitBtn;
	}

	public WebElement getMyProfilelink() {
		return myProfilelink;
	}

	public WebElement getProfileFirstNameTbx() {
		return profileFirstNameTbx;
	}

	public WebElement getUpdatedProfileSubmitBtn() {
		return updatedProfileSubmitBtn;
	}
	
	
}

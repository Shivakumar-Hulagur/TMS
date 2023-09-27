package com.tms.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tms.genericutils.WebDriverUtility1;

public class AdminDashBoardPage {

	//Declaration
	@FindBy(xpath ="//span[text()='Manage Enquiries']")
	private WebElement manageEnquiriesLink;
	
	@FindBy(xpath="//span[@class='fa fa-bars']")
	private WebElement optionsHamburger;
	
	@FindBy(xpath="//i[@class='fa fa-angle-down']")
	private WebElement welcomeDropDown;
	
	@FindBy(xpath="//i[@class='fa fa-sign-out']")
	private WebElement adminSignOutLink;
	
	
	@FindBy(xpath="//span[text()='Manage Booking']")
	private WebElement manageBookings;
	
	@FindBy(xpath="//span[text()=' Tour Packages']")
	private WebElement  tourPackagesDropMenu;
	
	@FindBy(xpath="//span[text()='Manage Users']")
	private WebElement manageUsersLink;

	@FindBy(xpath="//a[text()='Create']")
	private WebElement creatTourPack;
	

	@FindBy(xpath="//a[text()='Manage']")
	private WebElement manageTourPack;
	
	@FindBy(xpath="//span[text()='Manage Issues']")
	private WebElement manageIssues; 
	
	
	public WebElement getManageIssues() {
		return manageIssues;
	}


	
	public WebElement getCreatTourPack() {
		return creatTourPack;
	}

	
	public WebElement getManageTourPack() {
		return manageTourPack;
	}

	public AdminDashBoardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getManageEnquiriesLink() {
		return manageEnquiriesLink;
	}


	public WebElement getOptionsHamburger() {
		return optionsHamburger;
	}


	public WebElement getWelcomeDropDown() {
		return welcomeDropDown;
	}


	public WebElement getAdminSignOutLink() {
		return adminSignOutLink;
	}


	public WebElement getManageBookings() {
		return manageBookings;
	}


	public WebElement getTourPackagesDropMenu() {
		return tourPackagesDropMenu;
	}


	public WebElement getManageUsersLink() {
		return manageUsersLink;
	}
	public void setSignout(WebDriver driver)
	{
		WebDriverUtility1 wLib = new WebDriverUtility1();
		wLib.scrollUsingActionsClass(driver, getWelcomeDropDown());
		getWelcomeDropDown().click();
		getAdminSignOutLink().click();
	}
	
}

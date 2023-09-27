package com.tms.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	//Declaration
	@FindBy(xpath="//a[normalize-space(text())='Enquiry']")
	private WebElement enquiryLink;
	
	@FindBy(linkText = "Admin Login")
	private WebElement adminLoginLink;
	
	@FindBy(xpath="//a[contains(text(),'Sign In')]")
	private WebElement userSignInLink;
	
	@FindBy(xpath="//h3[text()='Signin with your account ']/following-sibling::input[@name='email']")
	private WebElement userSignInEmailTbx;
	
	
	@FindBy(xpath="//h3[text()='Signin with your account ']/following-sibling::input[@name='password']")
	private WebElement userSignInPasswordTbx;
	
	@FindBy(xpath="//input[@name='signin']")
	private WebElement userSignInButton;
	
	@FindBy(xpath="//input[@value='CREATE ACCOUNT']")
	private WebElement createAccoutnBtn;
	
	@FindBy(xpath="//div[@class='col-md-10 contact-left']/descendant::h4")
	private WebElement signUpConfMsg;
	
	@FindBy(xpath="//a[text()='Sign Up']")
	private WebElement signUpLink;
	
	public WebElement getSignUpLink() {
		return signUpLink;
	}

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getEnquiryLink() {
		return enquiryLink;
	}

	public WebElement getAdminLoginLink() {
		return adminLoginLink;
	}

	public WebElement getUserSignInLink() {
		return userSignInLink;
	}

	public WebElement getUserSignInEmailTbx() {
		return userSignInEmailTbx;
	}

	public WebElement getUserSignInPasswordTbx() {
		return userSignInPasswordTbx;
	}

	public WebElement getUserSignInButton() {
		return userSignInButton;
	}

	public WebElement getCreateAccoutnBtn() {
		return createAccoutnBtn;
	}

	public WebElement getSignUpConfMsg() {
		return signUpConfMsg;
	}
	
	

	
	
	
}
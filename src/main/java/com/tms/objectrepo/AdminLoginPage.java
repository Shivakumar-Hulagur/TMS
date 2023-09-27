package com.tms.objectrepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {

	//Declaration
	@FindBy(xpath="//input[@name='username']")
	private WebElement adminUnTbx;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement adminPwdTbx;
	
	@FindBy(xpath="//input[@name='login']")
	private WebElement adminLoginBtn;

	
	@FindBy(xpath="//a[text()='Back to home']")
	private WebElement backToHomeLink;
	
	public WebElement getAdminUnTbx() {
		return adminUnTbx;
	}

	public WebElement getAdminPwdTbx() {
		return adminPwdTbx;
	}

	public WebElement getAdminLoginBtn() {
		return adminLoginBtn;
	}

	public WebElement getBackToHomeLink() {
		return backToHomeLink;
	}

	public AdminLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void setLogin(String adminUN,String adminPwd)
	{
		getAdminUnTbx().sendKeys(adminUN);
		getAdminPwdTbx().sendKeys(adminPwd);
		getAdminLoginBtn().click();
	}
	
	
}

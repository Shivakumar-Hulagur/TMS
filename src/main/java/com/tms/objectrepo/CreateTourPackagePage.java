package com.tms.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tms.genericutils.IPathConstants1;

public class CreateTourPackagePage {

	@FindBy(xpath="//input[@name='packageimage']")
	private WebElement uploadTourPackageBtn;

	@FindBy(xpath="//button[text()='Create']")
	private WebElement createTourPackageBtn;

	@FindBy(xpath="//div[@class='succWrap']")
	private WebElement packageCreationOrBookingOrCancelConfMsg;

	@FindBy(xpath="//input[@name='packageimage']")
	private WebElement packageImg;

	public WebElement getPackageImg() {
		return packageImg;
	}

	public CreateTourPackagePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getUploadTourPackageBtn() {
		return uploadTourPackageBtn;
	}

	public WebElement getCreateTourPackageBtn() {
		return createTourPackageBtn;
	}

	public WebElement getPackageCreationOrBookingOrCancelConfMsg() {
		return packageCreationOrBookingOrCancelConfMsg;
	}
	public void uploadPicture()
	{
		getPackageImg().sendKeys(IPathConstants1.packageImage);
	}
	
}

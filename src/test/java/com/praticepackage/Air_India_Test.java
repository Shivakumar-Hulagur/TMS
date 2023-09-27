package com.praticepackage;

import java.time.Duration;
import java.util.List;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Air_India_Test {
	public static void main(String[] args) {
		String from="BLR";
		String to="Mumbai";
		String departureMonthAndYear="October 2023";
		int departureDate=10;
		String returnMonthAndYear="November 2023";
		int returnDate=10;
		int noOfAdultPass=5;
		int noOfChildranPass=2;
		int noOfInfantPass=1;
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.airindia.com/");
		Actions a=new Actions(driver);
		a.scrollByAmount(0, 500).perform();
		driver.findElement(By.id("From")).sendKeys(from);
		driver.findElement(By.xpath("//span[@class='airport-code-detail' and text()='"+from+"']")).click();
		driver.findElement(By.id("To")).sendKeys(to);
		driver.findElement(By.xpath("//input[@id='To']/following-sibling::ngb-typeahead-window/descendant::div[@class='row']")).click();
		driver.findElement(By.xpath("//input[@class='btn bi bi-calendar3']")).click();
		String departureMonthAndDate="//div[@class='ngb-dp-content ngb-dp-months']/descendant::div[contains(text(),'"+departureMonthAndYear+"')]/parent::div[@class='ngb-dp-month ng-star-inserted']/descendant::span[normalize-space(text())='"+departureDate+"']";
		String departureNextMonth="//button[@aria-label='Next month']";
		for(;;){
			try {

				driver.findElement(By.xpath(departureMonthAndDate)).click();
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath(departureNextMonth)).click();
			}
		}
		driver.findElement(By.xpath("//app-datepicker-range-popup/descendant::label[text()='Return']/following-sibling::div/input[@class='btn bi bi-calendar3']")).click();
		String returnMonthAndDate="//div[@class='ngb-dp-content ngb-dp-months']/descendant::div[contains(text(),'"+departureMonthAndYear+"')]/parent::div[@class='ngb-dp-month ng-star-inserted']/descendant::span[normalize-space(text())='"+departureDate+"']";
		String returnNextMonth="//button[@aria-label='Next month']";
		for(;;){
			try {

				driver.findElement(By.xpath(returnMonthAndDate)).click();
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath(returnNextMonth)).click();
			}
		}
		driver.findElement(By.id("dropdownForm1")).click();
		for(int i=0;i<noOfAdultPass;i++);
		{
			driver.findElement(By.xpath("//p[text()='Adult']/ancestor::div[@class='row d-flex align-items-center justify-content-center pb-3']/descendant::span[@class='col d-flex align-items-center justify-content-center']/following-sibling::button")).click();
		}
		for(int i=0;i<noOfChildranPass;i++)
		{
			driver.findElement(By.xpath("//p[text()='Children']/ancestor::div[@class='row d-flex align-items-center justify-content-center pb-3']/descendant::span[@class='col d-flex align-items-center justify-content-center']/following-sibling::button")).click();
		}
		for(int i=0;i<noOfInfantPass;i++)
		{
			driver.findElement(By.xpath("//p[text()='Infant']/ancestor::div[@class='row d-flex align-items-center justify-content-center']/descendant::span[@class='col d-flex align-items-center justify-content-center']/following-sibling::button")).click();
		}
		driver.findElement(By.id("mat-select-value-1")).click();
		driver.findElement(By.xpath("//span[text()='Business']")).click();
		//driver.findElement(By.id("mat-select-value-3")).click();
		//driver.findElement(By.xpath("//span[text()='Reserve Engineer']")).click();
		driver.findElement(By.xpath("//button[text()=' SHOW FLIGHTS ']")).click();
		List<WebElement> allFlights = driver.findElements(By.xpath("//span[@class='refx-caption operating-airline-multiline ng-star-inserted']"));
		List<WebElement> allFlightPrices = driver.findElements(By.xpath("//span[@class='refx-caption operating-airline-multiline ng-star-inserted']/ancestor::div[@class='basic-flight-card-layout-top-section-container row']/descendant::refx-price-cont[@class='price-cont-discounted-price ng-star-inserted']/descendant::span[@class='price-amount price-1-6-digits-display']"));
		System.out.println("The below flights are available for the given crteria");
		for(int i=0;i<allFlights.size();i++)
		{
			System.out.println("The Flight name: "+allFlights.get(i).getText());
			System.out.println("Ticket price: "+allFlightPrices.get(i).getText());
		}

	}
}

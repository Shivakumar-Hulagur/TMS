package com.praticepackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KSRTC_CalendarPopUpTest {
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.ksrtc.in/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.findElement(By.id("txtJourneyDate")).click();
		driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/descendant::a[text()='31']")).click();
	}
}

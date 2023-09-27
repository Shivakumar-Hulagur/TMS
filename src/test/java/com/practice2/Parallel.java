package com.practice2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tms.genericutils.WebDriverUtility1;

public class Parallel {

	public static void main(String[] args) {
		WebDriver driver=new FirefoxDriver();
		driver.get("https://amazon.in");
	}
}

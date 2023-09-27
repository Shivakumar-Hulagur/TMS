package com.practice2;

import org.apache.poi.xddf.usermodel.BlackWhiteMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewWindowWithoutUsingRobotClass {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://amazon.in");
		driver.switchTo().newWindow(WindowType.TAB).get("https://facebook.com");
		driver.switchTo().newWindow(WindowType.WINDOW).navigate().to("https://myntra.com");
		driver.quit();
	}
}

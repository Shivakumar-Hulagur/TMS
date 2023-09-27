package com.praticepackage;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IrctcTest {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.irctc.co.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		 List<WebElement> listlinks = driver.findElements(By.xpath("//a"));
		 System.out.println(listlinks.size());
		 ArrayList<String> link = new ArrayList<String>();
		 for(int i=0; i<listlinks.size(); i++)
		 {
			 String eachlink = listlinks.get(i).getAttribute("href");
			 URL url = null;
			 int statusCode = 0;
			 try {
				 url = new URL(eachlink);
				 HttpsURLConnection httpConn =(HttpsURLConnection) url.openConnection();
				 statusCode = httpConn.getResponseCode();
				 if(statusCode>=400)
				 {
					 link.add(eachlink);
					 System.out.println(eachlink+"=="+statusCode);
				 }
			 }
			 catch(Exception e)
			 {
				link.add(eachlink); 
			 }
			 
		 }
		
	}
}

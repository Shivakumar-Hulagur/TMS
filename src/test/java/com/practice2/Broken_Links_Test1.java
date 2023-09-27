//Write a script to verify broken links in a given website
package com.practice2;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Broken_Links_Test1 {
	public static void main(String[] args) {


		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//to open enter the url
		driver.get("https://www.indianrail.gov.in/");
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		ArrayList<String> ls = new ArrayList();
		URL url=null;
		String eachLink=null;
		int statusCode=0;
		for(WebElement e:allLinks)
		{
			try {
				eachLink=e.getAttribute("href");
				url=new URL(eachLink);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				statusCode=conn.getResponseCode();
				if(statusCode>=400)
					ls.add(eachLink);
			} catch (Exception e2) {
				ls.add(eachLink);
			}
		}
		System.out.println("The number of links which are not working is "+ls.size()+" and those links are given below");
		for(String res:ls)
		{
			System.out.println(res);
		}

	}
}

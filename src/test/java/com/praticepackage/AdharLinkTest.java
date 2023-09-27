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

public class AdharLinkTest {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://uidai.gov.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//button[@type='button' and @class='btn btn1 en' ]")).click();
		List<WebElement> listlinks = driver.findElements(By.xpath("//a"));
		System.out.println(listlinks.size());
		URL url = null;
		int statuscode =0;
		ArrayList ls = new ArrayList();
		for(int i = 0; i<listlinks.size(); i++)
		{
			String eachlink = listlinks.get(i).getAttribute("href");
			try {
				url = new URL(eachlink);
				HttpsURLConnection httpconn=(HttpsURLConnection)url.openConnection();
				statuscode = httpconn.getResponseCode();
				if(statuscode>=400)
				{
					ls.add(eachlink);
					System.out.println(eachlink);
				//	break;
				}
			}
			catch(Exception e)	
			{	
				ls.add(eachlink);
			}

		}
		driver.close();

	}
}

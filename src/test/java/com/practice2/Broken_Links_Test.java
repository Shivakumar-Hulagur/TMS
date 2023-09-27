//To verfiy the borken links present in any webapplication
package com.practice2;

import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleAction;
import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;

public class Broken_Links_Test {
	public static void main(String[] args) {
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		//to enter the url
		driver.get("https://pib.gov.in/PressReleaseIframePage.aspx?PRID=1885962");

		//to verify the brokern links code
		ArrayList ls=new ArrayList();
		ArrayList ls1=new ArrayList();
		int statusCode=0;
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		for (int i = 0; i <allLinks.size(); i++) {
			URL url=null;
			String eachLink=null;
			String responseMessage=null;
			try {
				//to open the connection on each link
				eachLink=allLinks.get(i).getAttribute("href");
				url=new URL(eachLink);
				URLConnection openConnection = url.openConnection();
				HttpsURLConnection conn=(HttpsURLConnection) openConnection;

				//to get the response code
				statusCode=conn.getResponseCode();
				responseMessage = conn.getResponseMessage();
				if(statusCode>=400)
				{
					ls.add(eachLink);
					ls.add(statusCode);
					ls.add(responseMessage);
				}
			}
			catch (Exception e) {
				ls1.add(eachLink);
				ls1.add(statusCode);
				ls1.add(responseMessage);
			}
		}
		//to print the url which staus are equal or above 400
		for(int i=0;i<ls.size();i++)
		{
			System.out.println(ls.get(i));
		}
         System.out.println("*****************************************************");
		//to print the url which generated exception while testing the response code
		for(int i=0;i<ls1.size();i++)
		{
			System.out.println(ls1.get(i));
		}
	}
}

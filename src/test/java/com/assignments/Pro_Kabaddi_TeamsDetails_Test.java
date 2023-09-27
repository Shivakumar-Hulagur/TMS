package com.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Pro_Kabaddi_TeamsDetails_Test {
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		String teamName="Bengaluru Bulls";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.prokabaddi.com/standings");
		String s[]=new String[9];
		boolean flag=false;
		int j=0;
		for(int i=2;i<=9;i++)
		{
			try {
				WebElement element = driver.findElement(By.xpath("//div[@class='table standings-table']/descendant::p[text()='"+teamName+"']/ancestor::div[@class='row-head']/div["+i+"]"));
				s[j]=element.getText();
				flag=true;
				j++;
			}
			catch(Exception e)
			{
				System.out.println("The team you have enterd is not available in the list");
				break;
			}
		}
		if(flag)
		{
			System.out.println("The team name: "+teamName);
			System.out.println("The points scored: "+s[1]);
			System.out.println("The number of matches won by team: "+s[2]);
			System.out.println("The number of matches loss by team: "+s[3]);
			System.out.println("The number of tied matches: "+s[4]);
			System.out.println("The score difference: "+s[5]);
			System.out.println("The last 5 match results: "+s[6]);
			System.out.println("The total points of the team: "+s[7]);
		}
		driver.quit();
	}
}

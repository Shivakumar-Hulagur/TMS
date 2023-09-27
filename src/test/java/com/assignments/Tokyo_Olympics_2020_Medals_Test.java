package com.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tokyo_Olympics_2020_Medals_Test {
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String teamName="Netherlands";
		String s[]=new String[4];
		driver.get("https://olympics.com/en/olympic-games/tokyo-2020/medals");
		int j=0;
		boolean flag=false;
		for(int i=1;i<=4;i++)
		{
			try {
				WebElement element=driver.findElement(By.xpath("//span[text()='"+teamName+"']/ancestor::div[@class='styles__CountryWrapper-sc-fehzzg-4 hjfJyH']/following-sibling::div["+i+"]"));
				s[j]=element.getText();
				j++;
				flag=true;
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
			System.out.println("The number of gold medels won: "+s[0]);
			System.out.println("The number of silver medals won: "+s[1]);
			System.out.println("The number of bronze medals won: "+s[2]);
			System.out.println("The total medals won by team: "+s[3]);
		}
		driver.quit();

	}
}

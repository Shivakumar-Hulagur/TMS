package com.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class SpiceJet_Test {
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions ch=new ChromeOptions();
		ch.addArguments("--disable-notifications");
		
		//to launch the browser
		WebDriver driver=new ChromeDriver(ch);
		
		//to maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//intialization values
		String from="Del";
		String to="BLR";
		String departureMonth="Sep";
		int departureYear=2023;
		String returnMonth="Dec";
		int returnYear=2023;
		int departureDate=25;
		int returnDate=1;
		
		//to open the url
		driver.get("https://www.spicejet.com/");
		
		//to click on round trip radio button
		driver.findElement(By.xpath("//div[text()='round trip']")).click();
		
		//to enter from place
		driver.findElement(By.xpath("//div[.='From']//input[@class='css-1cwyjr8 r-homxoj r-ubezar r-10paoce r-13qz1uu']")).sendKeys(from);
		Thread.sleep(2000);
		
		//to enter to/destination place
		driver.findElement(By.xpath("//div[.='To']//input[@class='css-1cwyjr8 r-homxoj r-ubezar r-10paoce r-13qz1uu']")).sendKeys(to);
		String fromDate = "//div[contains(text(),'"+departureMonth+"') and text()='"+departureYear+"']/ancestor::div[@class='css-1dbjc4n r-1mdbw0j r-ymttw5 r-b2dl2 r-mzjzbw r-wk8lta r-tvv088']/descendant::div[text()='"+departureDate+"']";
		String toDate = "//div[contains(text(),'"+returnMonth+"') and text()='"+returnYear+"']/ancestor::div[@class='css-1dbjc4n r-1mdbw0j r-ymttw5 r-b2dl2 r-mzjzbw r-wk8lta r-tvv088']/descendant::div[text()='"+returnDate+"']";
	
		//to enter departure date
		for(;;)
		{
			try {
				driver.findElement(By.xpath(fromDate)).click();
				break;
			}
			catch (Exception e1) {
				driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1loqt21 r-u8s1d r-11xbo3g r-1v2oles r-1otgn73 r-16zfatd r-eafdt9 r-1i6wzkk r-lrvibr r-184en5c']//*[name()='svg' and @data-testid='svg-img']")).click();
			}
		}
		
		//to enter return date
		for(;;)
		{
			try {
				driver.findElement(By.xpath(toDate)).click();
				break;
			}
			catch (Exception e1) {
				driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1loqt21 r-u8s1d r-11xbo3g r-1v2oles r-1otgn73 r-16zfatd r-eafdt9 r-1i6wzkk r-lrvibr r-184en5c']//*[name()='svg' and @data-testid='svg-img']")).click();
			}
		}
		
		//to select 5 adults
		driver.findElement(By.xpath("//div[.='Passengers']")).click();
		for(int i=0;i<4;i++)
		{
			driver.findElement(By.xpath("//div[.='Adult']/ancestor::div[@class='css-1dbjc4n r-18u37iz r-1wtj0ep r-1x0uki6']/descendant::div[@data-testid='Adult-testID-plus-one-cta']")).click();
		}
		
		//to select usd currency
		driver.findElement(By.xpath("//div[.='Currency']")).click();
		driver.findElement(By.xpath("//div[.='USD']")).click();
		
		//to click on search flights button
		Actions a=new Actions(driver);
		a.doubleClick(driver.findElement(By.xpath("//div[text()='Search Flight']"))).perform();
		
		//to fetch list of departure flights and print it
		List<WebElement> departureList = driver.findElements(By.xpath("//div[text()='Select your' and text()='Departure to']/ancestor::div[@id='list-results-section-0']/descendant::div[@class='css-1dbjc4n r-13awgt0 r-18u37iz r-b5h31w r-1ah4tor r-tvv088']/descendant::div[@class='css-76zvg2 r-homxoj r-1i10wst']"));
		if(departureList.size()>0)
		{
			System.out.println("List of departuring flights");
			for(WebElement e2:departureList)
			{
				System.out.println(e2.getText());
			}
		}
		else
			System.out.println("Currently departure flights are not available for the given date");
		
		//to fetch return flights and print the list
		List<WebElement> returnList = driver.findElements(By.xpath("//div[text()='Select your' and text()='Return Flight to']/ancestor::div[@id='list-results-section-1']/descendant::div[@class='css-1dbjc4n r-13awgt0 r-18u37iz r-b5h31w r-1ah4tor r-tvv088']/descendant::div[@class='css-76zvg2 r-homxoj r-1i10wst']"));
		if(returnList.size()>0)
		{
			System.out.println("List of return flights");
			for(WebElement e2:returnList)
			{
				System.out.println(e2.getText());
			}
		}
		else
			System.out.println("Currently return flights are not avaialble for the given date");
		driver.quit();
	}
}

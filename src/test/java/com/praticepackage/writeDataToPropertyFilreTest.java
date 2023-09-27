package com.praticepackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class writeDataToPropertyFilreTest {
	public static void main(String[] args) throws IOException {

		//to write the data to property file
		Properties p=new Properties();
		p.setProperty("browser", "chrome");
		p.setProperty("url", "http://rmgtestingserver/domain/Online_Tourism_Management_System/admin/index.php");
		p.setProperty("username", "admin");
		p.setProperty("password", "Test@123");

		//to save the data in property file
		String path="./src/test/resources/writeData.properties";
		FileOutputStream fout=new FileOutputStream(path);
		p.store(fout, "write data");

		//to read the data from property file
		FileInputStream fin=new FileInputStream(path);
		p.load(fin);
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String un = p.getProperty("username");
		String pwd = p.getProperty("password");

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@name='login']")).click();

	}
}

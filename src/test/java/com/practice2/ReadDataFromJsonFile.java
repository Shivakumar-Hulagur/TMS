package com.practice2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadDataFromJsonFile {
public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
	JSONParser jn = new JSONParser();
	Object obj = jn.parse(new FileReader("./src/test/resources/readJson.json"));
	
	JSONObject map=(JSONObject) obj;
	String url=(String) map.get("url");
	String un=(String) map.get("username");
	String pwd=(String) map.get("password");
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(url);
	driver.findElement(By.id("username")).sendKeys(un);
	driver.findElement(By.name("pwd")).sendKeys(pwd);
	driver.findElement(By.xpath("//div[normalize-space(text())='Login']")).click();
}
}

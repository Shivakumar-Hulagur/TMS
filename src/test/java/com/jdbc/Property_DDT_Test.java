package com.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property_DDT_Test {
	public static void main(String[] args) throws IOException {
		//Step 1: Create object for representation of physical file 
		String path="src\\test\\resources\\CommonData.property";
		FileInputStream fis=new FileInputStream(path);

		//Step 2: Create object for properties class
		Properties p=new Properties();

		//Step 3: to load the property file to runtime environment
		p.load(fis);

		//Step 4: use the getProperty() to read the data from property file
		String browser=p.getProperty("browser");
		String url=p.getProperty("url");
		String un=p.getProperty("username");
		String pwd=p.getProperty("password");

		System.out.println("The borwser name: "+browser);
		System.out.println("Url: "+url);
		System.out.println("User name: "+un);
		System.out.println("Password: "+pwd);
	}
}
//Write a script to read the data from property file
package com.practice2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadingTheDataFromPropertyFile {
	public static void main(String[] args) throws IOException {
		String path="src/test/resources/PracticeCommonData.property";
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fis);
		String url = prop.getProperty("url");
		String un = prop.getProperty("username");
		String pwd = prop.getProperty("password");

		System.out.println(url);
		System.out.println(un);
		System.out.println(pwd);
	}
}

package com.practice2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteDataIntoPropertyFile {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		prop.setProperty("url", "https://www.amazon.in/");
		prop.setProperty("username", "admin");
		prop.setProperty("password", "admin@123");
		
		FileOutputStream fout = new FileOutputStream("src/test/resources/PracticeCommonData.property");
		prop.store(fout, "write data");
		fout.close();
	}
}

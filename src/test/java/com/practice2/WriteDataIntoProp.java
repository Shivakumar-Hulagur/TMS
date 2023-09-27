package com.practice2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteDataIntoProp {

	public static void main(String[] args) throws IOException {
		Properties prop=new Properties();
		prop.setProperty("url", "Hello");
		String value = System.getProperty("url");
		Object res = prop.setProperty("url",value);
		System.out.println(res);
		FileOutputStream fout=new FileOutputStream("./src/test/resources/Practicecommondata.property");
		prop.store(fout, "Write Key only");
	}
}

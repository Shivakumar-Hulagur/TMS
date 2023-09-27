package com.practice2;

import java.util.Date;

import com.tms.genericutils2.JavaUtilities2;


public class SystemDate {
public static void main(String[] args) {
	Date date = new Date();
	System.out.println(date.toString());
	JavaUtilities2 j=new JavaUtilities2();
	System.out.print(j.getSystemDataBySpecificFormat());
	
}
}

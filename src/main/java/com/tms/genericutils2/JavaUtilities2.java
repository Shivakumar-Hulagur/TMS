package com.tms.genericutils2;

import java.util.Date;
import java.util.Random;

public class JavaUtilities2 {

	public int getRandom()
	{
		Random ran = new Random();
		int randomNumber = ran.nextInt(1000);
		return randomNumber;
	}
	public String getSystemDate()
	{
		Date dateTime = new Date();
		return dateTime.toString();
	}
	public String getSystemDataBySpecificFormat()
	{
		Date dateTime = new Date();
		String[] str = dateTime.toString().split(" ");
		String day =str[0];
		String month = str[1];
		String date = str[2];
		String year = str[5];
		String finalFormat = "Date: "+date+", Day: "+day+" Month: "+month+" year: "+year;
		return finalFormat;
		
	}

}

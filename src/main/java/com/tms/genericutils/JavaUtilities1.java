package com.tms.genericutils;

import java.util.Date;
import java.util.Random;

import io.opentelemetry.sdk.metrics.data.Data;
import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;
import net.bytebuddy.asm.Advice.Return;

/**
 * This class a generic class of java methods
 * @author Dell Inspiron 15
 *
 */
public class JavaUtilities1 {
	/** 
	 * This method returns the random number
	 * @return
	 */
	public int getRandomNum()
	{
		Random ran = new Random();
		int ranNumber = ran.nextInt(1000);
		return ranNumber;
	}
	/**
	 * This method returns the system date and time
	 * @return
	 */
	public String getSystemDataTime()
	{
		Date dateTime = new Date();
		return dateTime.toString();
	}
	/**
	 * This method is used to get the system date in day,month,date,year format
	 * @return
	 */
	public String getSystemDateFormat()
	{
		Date dateTime = new Date();
		String[] dateRes = dateTime.toString().split("");
		
		String day=dateRes[0];
		String month=dateRes[1];
		String date=dateRes[2];
		String year=dateRes[4];
		
		String finalDate="Day: "+day+" Month: "+month+" Date: "+date+" Year: "+year;
		return finalDate;
	}
}

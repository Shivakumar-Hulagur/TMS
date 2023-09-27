package testngpractice;

import org.testng.annotations.Test;
import org.testng.annotations.*;

public class DataProviderClass {

	@DataProvider
	public Object[] getDataMobile()
	{
		Object[][] obj = new Object[2][2];
		
		obj[0][0]="Iphone 15 pro";
		obj[0][1]=159999.00;
		
		obj[1][0]="Redmi Note 9 pro";
		obj[1][1]=18999.00;
		
		return obj;
	}
	
	
	@DataProvider
	public Object[][] getDataTv()
	{
		Object[][] obj = new Object[3][3];
		
		obj[0][0]="Samsung";
		obj[0][1]=25000;
		obj[0][2]="43 inch";
		
		obj[1][0]="xioami";
		obj[1][1]=35000;
		obj[1][2]="55 inch";
		
		obj[2][0]="LG";
		obj[2][1]=45000;
		obj[2][2]="43 inch";
		
		return obj;
		}
	@Test(dataProvider = "getDataMobile")
	public void dataProviderTest(Object productName,Object price)
	{
		System.out.println(productName+">>>>>>>>"+price);
	}
	
	@Test(dataProvider = "getDataTv")
	public void dataProvidesTest1(String productName,int price,String size)
	{
		System.out.println(productName+">>>>"+price+">>>>>>>"+size+">>>>");
	}
}

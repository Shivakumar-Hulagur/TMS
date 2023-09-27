package testngpractice;

import org.testng.annotations.Test;

public class DataProvidesFromExcelTest {

	@Test(dataProviderClass = DataProviderFromExcel.class,dataProvider = "getDataFromExcel")
	public void dataProviderTest(String productName,String price,String size)
	{
		System.out.println(productName+">>>>>"+price+">>>>>>"+size+">>>> ");
	}
}

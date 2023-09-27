package testngpractice;

import org.testng.annotations.DataProvider;

public class DataProvidesExcnClass {

	@DataProvider
	public Object[][]getStudentData()
	{
		Object obj[][] = new Object[2][2];
		return obj;
	}
}

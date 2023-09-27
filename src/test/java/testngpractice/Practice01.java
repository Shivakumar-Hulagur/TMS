package testngpractice;

import org.testng.annotations.Test;

public class Practice01 {

	@Test(priority = 0)
	public void createTest()
	{
		//int a=10/0;
		System.out.println("******Created Test is done******");
	}
	@Test(dependsOnMethods = "createTest")
	public void editTest()
	{
		System.out.println("***** modification in done******");
	}
	@Test(dependsOnMethods  = "createTest",priority = 0)
	public void deleteTest()
	{
	System.out.println("*****Delete test is done****");	
	}
}

package testngpractice;


import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AllAnnotation {

	
	
	@BeforeSuite
	public void bs()
	{
		System.out.println("------Connection to DB-----------");
	}
	@BeforeClass
	public void bc()
	{
		System.out.println("--------Open the browser---------");
	}
	@BeforeMethod
	public void bm()
	{
		System.out.println("-----Login to the application-----");
	}
	@Test( )
	public void test1()
	{
		System.out.println("--Test Script 1-----");
	}
	
	
	
	@AfterMethod
	public void am()
	{
		System.out.println("-----Logout from the application-------");
	}
	@AfterClass
	public void ac()
	{
		System.out.println("----Close the browser---");
	}
	@AfterSuite
	public void as() {
		System.out.println("---Close the DB connection----");
	}
	@Ignore
	public void test2()
	{
		int[]a= {1,2,3};
		System.out.println(a[4]);
		System.out.println("-Test script 2-----");
	}
	
	@Test()
	public void test4()
	{
		System.out.println("------Test script 4----------");
	}
	
	@Test()
	public void test3()
	{
		System.out.println("------Test script 3----------");
	}
	
}

package assertion;

import org.testng.annotations.Test;

public class SoftAssert {

	static org.testng.asserts.SoftAssert sa = new org.testng.asserts.SoftAssert();
	//soft assert using assetEquals()
	@Test
	public void softAssertEquals()
	{
		System.out.println("-- Step 1--");
		System.out.println("-- Step 2--");
		sa.assertEquals("A", "B");
		System.out.println("-- Step 3--");
		System.out.println("-- Step 4--");
		sa.assertAll();
	}
	//soft assert with assertEquals()
	@Test
	public void softAssertTest2()
	{
		System.out.println("--Step 5--");
		System.out.println("--STep 6--");
	sa.assertNotEquals("A", 65);
	System.out.println("--Step 7--");
	System.out.println("--STep 8--");
	sa.assertAll();
	}
}

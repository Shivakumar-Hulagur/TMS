package assertion;

import static org.junit.Assume.assumeNoException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import com.tms.genericutils.WebDriverUtility1;

public class HardAssert {

	//hard Assert with  normal assertEquals() 
 @Test
 public void hardAssertTest1()
 {
	 System.out.println("--Step 1--");
	 System.out.println("--Step 2--");
	assertEquals("A","B");
	System.out.println("--Step 3--");
	System.out.println("--Step 4--");
 }
 
 //hard Assert with customized message assertEquals()\
 @Test
 public void hardAssertTest2()
 {
	 System.out.println("--Step 4--");
	 System.out.println("-- Step 5--");
	 assertEquals("X", "y","Expected result should be match with actual result");
	 System.out.println("--Step 6--");
	 System.out.println("--Step 7--");
 }
 
 //hard assert with asserNotEquals()
 @Test
 public void hardAssertTest3()
 {
	 System.out.println("--Step 8--");
	 System.out.println("--Step 9--");
	 assertNotEquals("A", "B");
	 System.out.println("--Step 10--");
	 System.out.println("Step 11--");
 }
 //hard assert with assertSame()
 @Test
 public void hardAssertTest4()
 {
	 System.out.println("--Step 12--");
	 System.out.println("-- Step 13--");
	 assertSame("A","B");
	 System.out.println("--Step 14--");
	 System.out.println("--Step 15--");
 }
 //hard assert with assertNotSame()
 @Test
public void hardAssertTest5()
{
	 System.out.println("--Step 16--");
	 System.out.println("--Step 17--");
	 assertNotSame("A", "A");
	 System.out.println("--Step 18--");
	 System.out.println("--Step 19--");
}
//hard assert with assertNull()
 @Test
 public void hardAssertTest6()
 {
	 System.out.println("--Step 20--");
	 System.out.println("--Step 21--");
	 int a=10;
	 assertNull(a);
	 System.out.println("--Step 21--");
	 System.out.println("--Step 22--");
 }
 //hard assert with assertNotNull()
 @Test
 public void hardAssertTest7()
 {
	 System.out.println("-- Step 23--");
	 System.out.println("--Step 24--");
	 WebDriverUtility1 wLib = new WebDriverUtility1();
	 assertNotNull(wLib);
	 System.out.println("--Step 25--");
	 System.out.println("--Step 26--");
 }
 //hard assert with assertTrue()
 @Test
 public void hardAssertTest8() {
	 System.out.println("--Step 27--");
	 System.out.println("--Step 28--");
	 assertTrue(false);
	 System.out.println("--Step 29--");
	 System.out.println("-- Step 30--");
 }
 //hard assert with assertEquals() but comparing character with ascii value
 @Test
 public void hardAssertTest9(){
	 System.out.println("--Step 31--");
	 System.out.println("--Step 32--");
	 assertEquals(65, 'A');
	 System.out.println("--Step 33--");
	 System.out.println("--Step 34--");
 }
 
 //hard assert with assertFalse()
 @Test
 public void hardAssertTest10()
 {
	 System.out.println("--Step 35--");
	 System.out.println("-- Step 36--");
	 assertFalse(true);
	 System.out.println("--Step 37--");
	 System.out.println("--Step 38--");
 }
 
 //hard assert with fail()
 @Test
 public void hardAssertTest11()
 {
	 System.out.println("-- Step 39--");
	 System.out.println("-- Step 40--");
	 fail("From fail which print message");
	 System.out.println("-- Step 41--");
	 System.out.println("-- Step 42--");
 }
}

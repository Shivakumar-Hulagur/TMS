package xmltestngpractice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tms.genericutils.BaseClass;
import com.tms.genericutils.DemoBaseClass;
//@Listeners(com.tms.genericutils.ListenerImplimentation.class)
public class BookTourPackageModel extends DemoBaseClass{

	@Test(groups = {"smoke","regression"},retryAnalyzer = com.tms.genericutils.RetryAnalyzerClass.class)
	public void bookTourPackage()
	{
		Assert.fail();
		System.out.println("--From Book tour package--");
	}
	@Test(groups = "regression")
	public void cancelTourPackage()
	{
		System.out.println("--From cancel tour package--");
	}
}

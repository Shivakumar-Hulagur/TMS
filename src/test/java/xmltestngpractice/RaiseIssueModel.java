package xmltestngpractice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tms.genericutils.BaseClass;
import com.tms.genericutils.DemoBaseClass;

//@Listeners(com.tms.genericutils.ListenerImplimentation.class)
public class RaiseIssueModel extends DemoBaseClass {

	@Test(groups = {"smoke","regression"})
	public void raiseIssue()
	{
		Assert.fail();
		System.out.println("-- From raise issue--");
	}
	@Test(groups = "regression")
	public void resolveIssue()
	{
		Assert.fail();
		System.out.println("--From resolve issue--");
	}
}

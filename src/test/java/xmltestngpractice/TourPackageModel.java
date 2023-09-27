package xmltestngpractice;

import org.testng.annotations.Test;

import com.tms.genericutils.BaseClass;
import com.tms.genericutils.DemoBaseClass;

public class TourPackageModel extends DemoBaseClass{

	@Test(groups = { "smoke","regression"})
	public void createTourPackage()
	{
		System.out.println("--From Create Tour package--");
	}
	@Test(groups = "regression")
	public void modifyTourPackage()
	{
		System.out.println("--From modify tour package");
	}
	@Test(groups = "regression")
	public void deleteTourPackage()
	{
		System.out.println("--From delete Tour package--");
	}
}

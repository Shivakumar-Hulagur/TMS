package testngpractice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tms.genericutils.ExcelFileUtilites1;

public class DPFromExcelUtility {

	@DataProvider
	public Object[][] getExcelData() throws EncryptedDocumentException, IOException
	{
		ExcelFileUtilites1 eLib = new ExcelFileUtilites1();
		Object[][] res = eLib.getExcelDataProvider("DP");
		return res;
	}
	
	@Test(dataProvider ="getExcelData" )
	public void dataProviderFromExcelTest(String productName,String price,String size)
	{
		System.out.println(productName+">>>>"+price+">>>>>"+size+">>>>>>>");
	}
}

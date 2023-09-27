package testngpractice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class DataProviderFromExcel {

	@DataProvider
	public Object[][] getDataFromExcel() throws EncryptedDocumentException, IOException
	{
		String path="src/test/resources/DataProvider.xlsx";
		FileInputStream fis = new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("DP");
		int lastRowNum = sh.getLastRowNum()+1;
		System.out.println("lastRow "+lastRowNum);
		int lastCellNum=sh.getRow(0).getLastCellNum();
		System.out.println("Cell Value"+lastCellNum);
		Object obj[][]=new Object[lastRowNum][lastCellNum];
		for(int i=0;i<lastRowNum;i++)
		{
			//short lastCellNum = sh.getRow(i).getLastCellNum();
		//	obj=new Object[lastRowNum][lastCellNum];
			for(int j=0;j<lastCellNum;j++)
			{
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
			
		}
		return obj;
		
	}
}

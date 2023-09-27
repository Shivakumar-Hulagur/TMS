package com.practice2;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvider1 {

	public Object[][] getDataProvider() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("src/test/resources/DataProviderPractice.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("DP1");
		int lastRow = sh.getLastRowNum()+1;
		int lastCell = sh.getRow(0).getLastCellNum();
		Object	obj[][]=new Object[lastRow][lastCell];
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();		
			}
		}
		return obj;
	}
	@DataProvider
	public Object[][] dp1() throws EncryptedDocumentException, IOException
	{
		Object obj[][]=getDataProvider();
		return obj;
	}

	@Test(dataProvider = "dp1")
	public void DataProviderTest1(String un,String pwd)
	{
		System.out.println("Username: "+un+" Password: "+pwd);
	}
}

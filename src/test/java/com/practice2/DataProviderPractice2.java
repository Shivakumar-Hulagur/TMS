package com.practice2;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice2 {
	public Object[][] getExcelData() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("src/test/resources/DataProviderPractice.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("DP2");
		int lastRow=sh.getLastRowNum();
		int lastCell=sh.getRow(0).getLastCellNum();
		Object obj[][]=new Object[lastRow][lastCell];
		for(int i=1;i<=lastRow;i++)
		{
			int k=i-1;
			for(int j=0;j<lastCell;j++)
			{
				obj[k][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
	@DataProvider
	public Object[][] dp2() throws EncryptedDocumentException, IOException
	{
		Object[][] obj=getExcelData();
		return obj;
	}
	@Test(dataProvider = "dp2")
	public void readExcelData(String tpId,String tpName,String tpPrice,String tpType)
	{
		System.out.println("Tour package id: "+tpId+" Tour Package Name: "+tpName+" Tour Package Price: "+tpPrice+" Tour Package Type: "+tpType);
	}
}

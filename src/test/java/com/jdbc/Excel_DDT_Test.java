package com.jdbc;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

public class Excel_DDT_Test {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		String path="src\\test\\resources\\TestData.xlsx";

		//Step 1: Create object for representation of physical file
		FileInputStream fis=new FileInputStream(path);

		//Step 2: Load the excel file to run time environment
		Workbook wb = WorkbookFactory.create(fis);

		//Step 3: Read the excel data by calling the necesarry methods
		Sheet sh = wb.getSheet("Sheet1");

		int row_Count=sh.getLastRowNum();
		for(int i=1;i<=row_Count;i++)
		{
			int col_Count=sh.getRow(i).getLastCellNum();
			for(int j=0;j<col_Count;j++)
			{
				String value=sh.getRow(i).getCell(j).getStringCellValue();
				System.out.print(value+"  ");
			}
			System.out.println();
		}
		//Step 4: Close the workbook
		wb.close();
	}
}

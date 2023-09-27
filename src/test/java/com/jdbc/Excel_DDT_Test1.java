//WASS to write the date into excel file
package com.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_DDT_Test1 {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		String path="src\\test\\resources\\TestData.xlsx";

		//Step 1: Create object for represenatation of physical file
		FileInputStream fis=new FileInputStream(path);

		//Step 2: Load the excel file to runtime environment
		Workbook wb = WorkbookFactory.create(fis);

		//Step 3: Write the date to excel file by invoking the necessary methods
		Sheet sh = wb.getSheet("Sheet1");
		int row_Count=sh.getLastRowNum();
		boolean flag=false;
		for(int i=12;i<row_Count;i++)
		{

			sh.getRow(i).getCell(0).setCellValue("Google");
			sh.getRow(i).getCell(1).setCellValue("California");
			if(i==row_Count-1) 
				flag=true;

		}

		//Step 4: to save the written data in excel file

		FileOutputStream fos=new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		if(flag)
			System.out.println("The data written to file successfully");
		else
			System.out.println("The data not written to file");
	}
}

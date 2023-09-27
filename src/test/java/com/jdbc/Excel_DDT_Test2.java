package com.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_DDT_Test2 {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of row do you want to enter the data");
		int row_Count=sc.nextInt();
		System.out.println("Enter the coloumn number do you want to enter the data");
		int col_Count=sc.nextInt();
		String path="src\\test\\resources\\TestDataWrite.xlsx";
		
		//Step 1: create object for Represenatation of Physical file
		FileInputStream fis=new FileInputStream(path);

		//Load Excel to run time 
		Workbook wb = WorkbookFactory.create(fis);

		Sheet sh = wb.getSheet("Sheet1");
		for(int i=0;i<row_Count;i++)
		{
			sh.createRow(i);
			for(int j=0;j<col_Count;j++)
			{
				System.out.println("Enter the date for "+i+" row and "+j+" cell");
				String cellData=sc.next();
				sh.getRow(i).createCell(j).setCellValue(cellData);
			}
		}

		FileOutputStream fos=new FileOutputStream("src\\test\\resources\\TestDataWrite.xlsx");
		wb.write(fos);
		System.out.println("The data written to the file successfully");
		wb.close();

	}
}

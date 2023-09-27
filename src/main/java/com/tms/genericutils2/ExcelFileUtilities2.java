package com.tms.genericutils2;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtilities2 {

	public String getExcelData(String sheet,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fin = new FileInputStream(IPathConstants2.excelFilePath);
		Workbook wb = WorkbookFactory.create(fin);
		String data=wb.getSheet(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}

	public void writeDateIntoExcel(String sheet,int rowNum,int cellNum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fin=new FileInputStream(IPathConstants2.excelFilePath);
		Workbook wb = WorkbookFactory.create(fin);
		wb.getSheet(sheet).createRow(rowNum).createCell(cellNum).setCellValue(data);

		FileOutputStream fout=new FileOutputStream(IPathConstants2.excelFilePath);
		wb.write(fout);
		wb.close();
	}
	public void writeMultipleData(String sheet,int rowCount,int columnCount,String str[]) throws EncryptedDocumentException, IOException
	{
		FileInputStream fin=new FileInputStream(IPathConstants2.excelFilePath);
		Workbook wb = WorkbookFactory.create(fin);
		Sheet sh = wb.getSheet(sheet);
		int value=0;
		boolean flag=false;
		for(int i=0;i<rowCount;i++)
		{
			sh.createRow(i);
			for(int j=0;j<columnCount;j++)
			{
				String data=str[value];
				sh.getRow(i).createCell(j).setCellValue(data);
				value++;
				if(i==rowCount-1)
					flag=true;
			}
		}
		FileOutputStream fout=new FileOutputStream(IPathConstants2.excelFilePath);
		wb.write(fout);
		if(flag)
			System.out.println("The data is written into excel file successfully");
		else
			System.out.println("Unable to write the data");

	}
}

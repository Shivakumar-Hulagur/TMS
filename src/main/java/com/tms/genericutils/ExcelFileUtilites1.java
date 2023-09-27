package com.tms.genericutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.math3.ode.AbstractParameterizable;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
/**
 * This class provide the methods to perform the action on excel file
 * @author Dell Inspiron 15
 *
 */
public class ExcelFileUtilites1 {
	/**
	 * This methos is used to fecth the data from the excel file
	 * @param Sheet
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getExcelData(String Sheet,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream(IPathConstants1.excelFilePath);
		Workbook wb = WorkbookFactory.create(fileInputStream);
		String value = wb.getSheet(Sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return value;
	}
	/**
	 * This method is used to write the data into excel file
	 * @param Sheet
	 * @param rowNum
	 * @param CellNum
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setExcelData(String Sheet,int rowNum,int CellNum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream(IPathConstants1.excelFilePath);
		Workbook wb = WorkbookFactory.create(fileInputStream);
		Sheet sheet = wb.getSheet(Sheet);
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(CellNum);
		cell.setCellValue(data);

		FileOutputStream fileOutputStream = new FileOutputStream(IPathConstants1.excelFilePath);
		wb.write(fileOutputStream);
		wb.close();
	}
	/**
	 * This method return the hashmap which contains the the excel data in key value pair.
	 * @param sheet
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public HashMap<String, String> getExcelDataByMap(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IPathConstants1.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		int rowCount = sh.getLastRowNum();
		HashMap<String, String> map = new HashMap();
		for(int i=1;i<=rowCount;i++)
		{
			String key=sh.getRow(i).getCell(0).getStringCellValue();
			String value=sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);			
		}
		return map;
		
	}
	
	public Object[][] getExcelDataProvider(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream  fis=new FileInputStream(IPathConstants1.dataProviderPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		int rowCount = sh.getLastRowNum()+1;
		short colCount = sh.getRow(0).getLastCellNum();
		Object obj[][]=new Object[rowCount][colCount];
		for(int i=0;i<rowCount;i++)
		{
			for(int j=0;j<colCount;j++)
			{
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
	
	
	
	
	
}

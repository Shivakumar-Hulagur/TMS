package com.tms.genericutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class provide the method for performing the property file related operations
 * @author Dell Inspiron 15
 *
 */
public class PropertyFileUtilites1 {
	/**
	 * This method is used to get the data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getPropertyKeyValue(String key) throws IOException
	{
		FileInputStream fin=new FileInputStream(IPathConstants1.propertyFilePath);
		Properties prop=new Properties();
		prop.load(fin);
		return prop.getProperty(key);
	}
	/**
	 *  This method is used to write the date into property file
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void writePropertyKeyValue(String key,String value) throws IOException
	{
		Properties p=new Properties();
		p.setProperty(key, value);
		
		 FileOutputStream fileOutputStream = new FileOutputStream(IPathConstants1.propertyFilePath);
		 p.store(fileOutputStream, "Write date");
		 fileOutputStream.close();
	}
}

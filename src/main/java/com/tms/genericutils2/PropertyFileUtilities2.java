package com.tms.genericutils2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtilities2 {

	public String getPropertyKeyValue(String key) throws IOException
	{
		 FileInputStream fin = new FileInputStream(IPathConstants2.propertyFilePath);
		 Properties p=new Properties();
		 p.load(fin);
		 
		 String value = p.getProperty(key);
		 return value;
		 
	}
	public void putPropertyKeyValue(String key,String value) throws IOException
	{
		Properties p=new Properties();
		p.setProperty(key, value);
		
		FileOutputStream fout = new FileOutputStream(IPathConstants2.propertyFilePath);
		p.store(fout, "Write date");
		fout.close();
	}
}

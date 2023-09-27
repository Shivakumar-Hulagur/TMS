package com.tms.genericutils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtilities1 {

	public  String readDataFromJson(String key) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser jp = new JSONParser();	 
		Object obj = jp.parse(new FileReader(IPathConstants1.jsonFilePath));

		JSONObject map=(JSONObject) obj;
		return (String)map.get(key);


	}

}

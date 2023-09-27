package com.praticepackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJson {
	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
		//to parse the data in json file
		JSONParser jsonp = new JSONParser();
		Object obj = jsonp.parse(new FileReader("./src/test/resources/readJson.json"));

		//to read the data from json file
		JSONObject map=(JSONObject) obj;
		System.out.println(map.get("browser"));
		System.out.println(map.get("url"));
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));

		
		    
	}
}

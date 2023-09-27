package com.tms.genericutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerClass implements IRetryAnalyzer {

	int count=0;
	int upperLimit=3;
	public boolean retry(ITestResult result) {
		
		if(count<upperLimit)
		{
			count++;
			return true;
		}
		return false;
		
	}

}

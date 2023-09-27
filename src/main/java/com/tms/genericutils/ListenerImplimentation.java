package com.tms.genericutils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplimentation implements ITestListener{

	
	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		//Actual test scripts execution starts from here
		String methodName = result.getMethod().getMethodName();
		test=report.createTest(methodName);
		Reporter.log(methodName+">>>>>>>>>>Execution Starts");
	}

	
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+">>>>>>>>>Passed");
		Reporter.log(methodName+">>>>>>>>> TestScript executed Successfully");
	}

	
	public void onTestFailure(ITestResult result) {

		String methodName=result.getName();
		TakesScreenshot ts=(TakesScreenshot)DemoBaseClass.sDriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshots/FailedTestScripts/"+methodName+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.FAIL, result.getThrowable());
		test.log(Status.FAIL, methodName+">>>>>>>Failed");
		Reporter.log(methodName+">>>>>>Failed");
		}

	
	public void onTestSkipped(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.SKIP, methodName+">>>>>Skipped");
		Reporter.log(methodName+">>>>>>>>>Skipped");
		
	}

	
	public void onStart(ITestContext context) {
		//Create Report
		
		ExtentSparkReporter htmlReport=new ExtentSparkReporter("./ExtentReports/report.html");
		
		htmlReport.config().setDocumentTitle("TMS-01");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setReportName("TMS-Application");
		
		//to add the system details in report
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("Base-Browser","Chrome Browser");
		report.setSystemInfo("Base-url", "http://rmgtestingserver/domain/Online_Tourism_Management_System");
		report.setSystemInfo("ReporterName", "Shivakumar H");
	}

	
	public void onFinish(ITestContext context) {
		report.flush();
	}
	


}

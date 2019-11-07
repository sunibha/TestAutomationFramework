package com.phptravels.qa.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.phptravels.qa.testCases.BaseTest;


//creates an extent report
public class ExtentReportListener extends BaseTest implements ITestListener, ISuiteListener {
	
	public static ExtentHtmlReporter reporter;
	
	public static ExtentReports extent;
	
	public static ExtentTest parentTest;
	public static ExtentTest childTest;


	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("------------------On test start--------------------------");
		System.out.println(result);
		parentTest = extent.createTest(result.getInstance().getClass().getSimpleName());
		parentTest.assignCategory(result.getInstance().getClass().getSimpleName());
		parentTest.info(result.getMethod().getMethodName() +  " validation");
		System.out.println(result);
		childTest = parentTest.createNode(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("------------------On test success---------------------------");
		System.out.println("Starting method---> " + result.getMethod().getMethodName());	
		childTest.pass(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("---------------------On test failure-----------------------");
		System.out.println(result);	
		System.out.println("------         Failed Test             ------");
		System.out.println("Method Name---> " + result.getMethod().getMethodName());
		System.out.println("Error   ---> " + result.getThrowable());	
		
		childTest.fail(result.getThrowable());
		
		try {
			childTest.info("Refer screenshot below");
			String screenshotPath = TestUtil.captureScreenShot(driver, result.getMethod().getMethodName());
			childTest.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("-----------------On test skipped----------------------------");
		childTest.skip(result.getMethod().getMethodName()  + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("---------------------On test failed but within success range-----------------------");
		System.out.println("------Failed Test within success range------");
		System.out.println(result.getTestName());
		System.out.println(result.getMethod().getMethodName());
		System.out.println(result.getThrowable());	
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("----------------------On Start-----------------------");
		System.out.println("Test ---" + context.getName() + " ---- starting");

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("-------------------On Finish--------------------------");
		
	}

	@Override
	public void onStart(ISuite suite) {
		System.out.println("----------------------Before Suite Start-----------------------");
		System.out.println("Test ---" + suite.getName() + " ---- starting");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ssss");
		String currDate = dateFormat.format(new Date());
		
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/custom-reports/ExtentReport" + currDate + ".html");
	
		reporter.config().setDocumentTitle(suite.getName());	
		reporter.config().setReportName(suite.getName());
		extent = new ExtentReports();
		
		ReadConfig configFile = new ReadConfig();
        extent.setSystemInfo("Browser", configFile.getBrowserName());
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Operating System Version", System.getProperty("os.version"));
        extent.setSystemInfo("URL ", configFile.getBaseUrl());
		extent.attachReporter(reporter);
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		extent.flush();	
	}

	
	
}

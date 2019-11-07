package com.phptravels.qa.util;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class ReporterTest implements IReporter{

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
		for (ISuite suite: suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			
			for (ISuiteResult suiteResult: suiteResults.values()) {
				ITestContext testContext = suiteResult.getTestContext();
				System.out.println("Total Number of Test Cases  ---> " + testContext.getAllTestMethods().length );
				System.out.println("Number of Test Cases passed ---> " + testContext.getPassedTests().getAllMethods().size());
				System.out.println("Number of Test Cases failed ---> " + testContext.getFailedTests().getAllMethods().size());
				System.out.println("Number of Test Cases skipped ---> " + testContext.getSkippedTests().getAllMethods().size());
			}
		}
		
		
	}

}

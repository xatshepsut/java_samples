package com.samples.testng.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class SampleTestListener implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		System.out.println("Test started running: "  + result.getMethod().getMethodName() + ";\n start time: " + result.getStartMillis());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test succeded...");
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed...");
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped...");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		System.out.println("Test started...");
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("Test finished...");
		System.out.println("Passed: " + context.getPassedTests());  
		System.out.println("Failed: " + context.getFailedTests()); 
		
	}


}
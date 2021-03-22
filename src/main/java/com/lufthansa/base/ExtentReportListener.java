package com.lufthansa.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.lufthansa.base.ExtentManager;


public class ExtentReportListener implements ITestListener{
	

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		String fileSeperator = System.getProperty("file.separator");
		File testDirectory = new File(System.getProperty("user.dir") +fileSeperator+ "TestReport" +fileSeperator+ "Screenshots");
		File[] fileList = testDirectory.listFiles((d,f)-> f.toLowerCase().endsWith(".png"));
        for(File f : fileList) {
          System.out.println(f.getAbsolutePath());
          f.delete();              
        } 
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		endTest();
		ExtentManager.createInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		getTest().log(Status.PASS, "Test passed");
		
		//capture screenshot, save it in required path
		Object currentClass = result.getInstance();
		WebDriver driver = ((LufthansaTestBase)currentClass).getDriver();
		String testMethodName = result.getName().toString().trim();
		String fileSeperator = System.getProperty("file.separator");
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshotFile, new File(System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("An exception occurred while taking screenshot " + e.getCause());
		}
		
		try {
			getTest().pass("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png").build());
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("An exception occured while taking screenshot " + e.getCause());
		}
		String screenshotPath = System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png";
		
		//attach screen shot to extent report
		try {
			getTest().addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		getTest().log(Status.FAIL, "Test Case Failed");
		
		//capture Screenshot and Attach report to extent report
		
		Object currentClass = result.getInstance();
		WebDriver driver = ((LufthansaTestBase)currentClass).getDriver();
		String testMethodName = result.getName().toString().trim();
		String fileSeperator = System.getProperty("file.separator");
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshotFile, new File(System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("An exception occurred while taking screenshot " + e.getCause());
		}
		
		try {
			getTest().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png").build());
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("An exception occured while taking screenshot " + e.getCause());
		}
			
			String screenshotPath = System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png"; 
			try {
				getTest().addScreenCaptureFromPath(screenshotPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		getTest().log(Status.SKIP, "Test Skipped");
		
		Object currentClass = result.getInstance();
		WebDriver driver = ((LufthansaTestBase)currentClass).getDriver();
		String testMethodName = result.getName().toString().trim();
		String fileSeperator = System.getProperty("file.separator");
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshotFile, new File(System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("An exception occurred while taking screenshot " + e.getCause());
		}
		
		try {
			getTest().skip("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png").build());
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("An exception occured while taking screenshot " + e.getCause());
		}
			
			String screenshotPath = System.getProperty("user.dir") +fileSeperator+"TestReport" + fileSeperator+ "Screenshots"+fileSeperator+testMethodName+".png"; 
			try {
				getTest().addScreenCaptureFromPath(screenshotPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
	
	
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.createInstance();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
	
	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

	/*---------Prints current date time stamp---------*/
	public String getCurrentTimeStamp(String format){
		Date currentDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat(format);
		String currentDateString = df.format(currentDate);
		return currentDateString;
	}



}

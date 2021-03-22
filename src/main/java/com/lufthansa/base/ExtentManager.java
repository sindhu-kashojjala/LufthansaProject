package com.lufthansa.base;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	

	public static ExtentReports extent;
	public static String reportFileName = "Extent-Report"+".html";
	public static String fileSeperator = System.getProperty("file.separator");
    public static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    public static String screenshotFilePath = reportFilepath +fileSeperator+ "Screenshots";
    public static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
	
	//Create an extent report instance
   public static ExtentReports createInstance() {
       String fileName = getReportPath(reportFilepath);
       String screenshotsfilename = getReportPath(screenshotFilePath);
       System.out.println(screenshotsfilename);
       
       ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
       
       htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
       htmlReporter.config().setChartVisibilityOnOpen(true);
       htmlReporter.config().setTheme(Theme.DARK);
       htmlReporter.config().setDocumentTitle(reportFileName);
       htmlReporter.config().setEncoding("utf-8");
       htmlReporter.config().setReportName(reportFileName);
       htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

       extent = new ExtentReports();
       extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows");
       return extent;
   }
    
   //Create the report path
   private static String getReportPath (String path) {    	
   	File testDirectory = new File(path);
       if (!testDirectory.exists()) {
       	if (testDirectory.mkdir()) {
               System.out.println("Directory: " + path + " is created!" );
               return reportFileLocation;
           } else {
               System.out.println("Failed to create directory: " + path);
               return System.getProperty("user.dir");
           }
       } else {     
           System.out.println("Directory already exists: " + path);
       }
		return reportFileLocation;
   }



}

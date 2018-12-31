package com.crm.qa.testcases;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.ExcelAPI;

public class TestReportListener extends TestBase implements ITestListener {

	public static ExtentReports reports;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;
	ExcelAPI apitest;
	XSSFCell cell;
	String imglink;
	ContactsPageTest objContacts= new ContactsPageTest();

	static Logger 	log= Logger.getLogger(TestReportListener.class);
	
	 
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("on test start");
		
		test = reports.createTest(result.getName());
		test.log(Status.INFO, result.getName() + " test is started");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("on test success");
		test.log(Status.PASS, result.getName() + " test is passed");
	
		if(result.getName().contentEquals("validateCreateNewCOntactTest"))
		{
			try {
				 apitest=new ExcelAPI(prop.getProperty("filepath"));
				 objContacts=(ContactsPageTest)result.getInstance();
				 	cell= apitest.setCellData(prop.getProperty("sheetname"), "status",objContacts.row, "Pass");
					apitest.setupFont(cell,HSSFColorPredefined.GREEN.getIndex());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		log.error("on test failure");
		test.log(Status.FAIL,result.getName() + " test failed");
		
				try {
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					File destFile= new File(".\\screenshots\\" + result.getName() + ".png");
					FileUtils.copyFile(scrFile,destFile);
					 imglink=destFile.getAbsolutePath();
					test.addScreenCaptureFromPath(destFile.getAbsolutePath());	
					
					test.log(Status.FAIL, result.getThrowable().getMessage());
					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				if(result.getName().contentEquals("validateCreateNewCOntactTest"))
				{
					try {
						 apitest=new ExcelAPI(prop.getProperty("filepath"));
						 objContacts=(ContactsPageTest)result.getInstance();
						 cell=apitest.setCellData(prop.getProperty("sheetname"), "status",objContacts.row, "Fail");
						 apitest.createHyperlink(prop.getProperty("sheetname"), cell, imglink);
						 apitest.setupFont(cell,HSSFColorPredefined.RED.getIndex());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("on test skipped");
		test.log(Status.SKIP, result.getName()+ " test is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("on test sucess within percentage " + result.SUCCESS_PERCENTAGE_FAILURE);
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/MyOwnReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	
		  reports.flush();
	}

}

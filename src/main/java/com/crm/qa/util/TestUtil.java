package com.crm.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	@DataProvider(name="FreeCRMContactsData")		
		public static Object[][] gettestData(String filepath, String sheetName) throws Exception
		{
			Object[][] excelData=null;
			ExcelAPI apitest=new ExcelAPI(filepath);
			int rows=apitest.getRowCount(sheetName);
			int columns=apitest.getColCount(sheetName);
			excelData= new Object[rows-1][columns];
			for (int i=2; i<=rows; i++)
			{
				for (int j=0; j<columns; j++)
				{
					excelData[i-2][j]=apitest.getCellData(sheetName, j, i).toString();
					
				}
			}
			return excelData;
		
		}
	public void writeData(String filepath, String sheetName,String cloumn,String result)
	{
		
	}
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshots\\" + System.currentTimeMillis() + ".png"));
	}
	}


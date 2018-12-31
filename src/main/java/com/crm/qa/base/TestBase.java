package com.crm.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.crm.qa.util.BrowserSelection;
import com.crm.qa.util.WebEventListener;

	public class TestBase {
		
		public static WebDriver driver;
		public static Properties prop;
		public static WebDriverEventListener eventListener;
		public static EventFiringWebDriver edriver;
		
		public TestBase()
		{
			try
			{
				prop= new Properties();
				FileInputStream istream= new FileInputStream(".\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
				prop.load(istream);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void initialization()
		
		{
			
				 driver=BrowserSelection.SelectBrowser(prop.getProperty("browser"));
				 edriver= new EventFiringWebDriver(driver);
				 eventListener =new WebEventListener();
				edriver.register(eventListener);
				driver=edriver;	
				driver.get(prop.getProperty("url"));
		}
}

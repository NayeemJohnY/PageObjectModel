package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	Logger log=Logger.getLogger(LoginPageTest.class);
	
	public LoginPageTest()
	{
		super();
		
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage= new LoginPage();
		
	}
	
	@Test(priority=1)
	public void validateLoginPageTitleTest()
	
	{
		String actualTitle=loginPage.validateLoginPageTitle();
		log.info("Login  Page Title is"+actualTitle);
		Assert.assertEquals("#1 Free CRM software in the cloud for sales and service", actualTitle);
		log.info("Login Page Title is verified");
	}
	
	@Test(priority=2)
	public void validateCRMlogoTest()
	{
		Assert.assertTrue(loginPage.validateCRMlogo());
		log.info("CRM Logo is verified");
	}
	
	@Test(priority=3)
	public void LoginTest()
	{	log.info("Login using credentails username="+prop.getProperty("username")+"password="+prop.getProperty("password"));
		homePage= loginPage.Login(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	
	@AfterMethod
	public void  teardown()
	{
		driver.quit();
	}

}


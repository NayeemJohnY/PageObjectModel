package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	Logger log=Logger.getLogger(HomePageTest.class);
	public HomePageTest()
	{
		super();
	}
	@BeforeMethod
	public void setup()
	{
		initialization();
		loginPage= new LoginPage();
		homePage=loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));		
		driver.switchTo().frame("mainpanel");
	}
	
	@Test (priority =1)
	public void validateHomePageTitleTest()
	{ 	
		String titleHomePage=homePage.validateHomePageTitle();
		log.info("Homepage title is "+titleHomePage);
		Assert.assertEquals(titleHomePage, "CRMPRO", "HomePage Title is Not Matching with CRMPRO");
		log.info("Homepage title is verified");
	}
	@Test(priority =2)
	public void validateUserNameTest()
	{ 
		Assert.assertTrue(homePage.validateUserName());
		log.info("Homepage username  is diaplayed");
	}
	@Test(priority =3)
	public void verifyContactsLinkTest()
	{	
		contactsPage=homePage.clickOnContacts();
		log.info("Contacts link clicked");
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}

}

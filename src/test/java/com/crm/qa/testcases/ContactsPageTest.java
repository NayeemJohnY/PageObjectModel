package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.ExcelAPI;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	ExcelAPI excelapi;
	public  int row=1;
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage= new LoginPage();
		homePage=loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));	
		driver.switchTo().frame("mainpanel");
		contactsPage=homePage.clickOnContacts();
	}
	
	@Test(priority=1)
	public void validateContactsLabelTest()
	{
		Assert.assertTrue(contactsPage.validateContactsLabel(),"Contacts Label is Not displayed");
	}
	
	@Test(priority=2)
	public void validateselectContacts()
	{ 
		contactsPage.selectContacts("john");
	}
	
	@Test(priority=3 ,dataProvider="ContactsData")
	public void validateCreateNewCOntactTest(String title,String firstname,String lastname,String company,String result)
	{	row++;
		homePage.clickOnNewcontact();
		contactsPage.createNewContact(title,firstname,lastname,company);
		
		
	}
	@DataProvider(name="ContactsData")
	public Object[][] getContactsdata() throws Exception
	{	
		Object[][] data=TestUtil.gettestData(prop.getProperty("filepath"), prop.getProperty("sheetname"));
	 return data;
	}
	@AfterMethod
	public void  teardown()
	{ 	
		driver.quit();
		
	}

}


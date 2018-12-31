package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(how=How.XPATH, using ="//td[contains(text(),'User: nayeem')]")
	WebElement userLabel;

	@FindBy(how=How.XPATH, using ="//a[contains(text(),'Contacts')]")
	WebElement linkContacts;
	
	@FindBy(how=How.XPATH, using ="//a[contains(text(),'New Contact')]")
	WebElement linkNewContact;
	
	@FindBy(how=How.XPATH, using ="//a[contains(text(),'Deals')]")
	WebElement linkDeals;
	
	@FindBy(how=How.XPATH, using ="//a[contains(text(),'Tasks')]")
	WebElement linkTasks;
	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public  String validateHomePageTitle()
	{
		return driver.getTitle();
	}
	
	public  boolean validateUserName()
	{  	
		return userLabel.isDisplayed();
	}
	public ContactsPage clickOnContacts()
	{
		linkContacts.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDeals()
	{
		linkDeals.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasks()
	{
		linkDeals.click();
		return new TasksPage();
	}
	public void clickOnNewcontact()
	{
		Actions action= new Actions(driver);
		action.moveToElement(linkContacts).build().perform();
		linkNewContact.click();
		
	}
}
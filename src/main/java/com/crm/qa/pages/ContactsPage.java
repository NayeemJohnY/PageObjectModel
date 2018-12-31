package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsTable;
	
	@FindBy(id="first_name")
	WebElement txtFirstName;
	
	@FindBy(id="surname")
	WebElement txtLastName;
	
	@FindBy(xpath="//*[@name='client_lookup']")
	WebElement txtcompany;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement	BtnSave;
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	public boolean validateContactsLabel()
	{		
		return contactsTable.isDisplayed();
	}
	
	public void selectContacts(String name)
	{
		WebElement checkBx=driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]//parent::td//preceding-sibling::td"));
		checkBx.click();
	}
	public void createNewContact(String tiltle,String firstname,String lastname,String company)
	{
		Select select=new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(tiltle);
		txtFirstName.sendKeys(firstname);
		txtLastName.sendKeys(lastname);
		txtcompany.sendKeys(company);
		BtnSave.click();
	}
	
	
}

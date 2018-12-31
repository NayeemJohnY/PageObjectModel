package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(name="username")
	WebElement txtUsername;
	
	@FindBy(xpath="//*[@type='password']")
	WebElement txtPassword;
	
	@FindBy(css="input[value='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath="//*[@id='navbar-collapse']//font")
	WebElement linkSignUp;
	
	@FindBy(xpath="//img[contains(@alt,'logo')]")
	WebElement imgLogo;
	
	@FindBy(xpath="//span[text()='Cogmento']")
	WebElement chatPopup;
	
	@FindBy(xpath="//*[@role='button']")
	WebElement btnclosePopup;
	
	@FindBy(xpath="//iframe[contains(@name, 'intercom-borderless-frame')]")
	WebElement frameChatPopup;
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public  String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	public boolean validateCRMlogo()
	{
		return imgLogo.isDisplayed();
	}
	
	public HomePage Login(String username, String password)
	{ 	WebDriverWait wait= new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameChatPopup));
		Actions action= new Actions (driver);
		action.moveToElement(chatPopup).build().perform();
	  	btnclosePopup.click();
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		return new HomePage();
	}
}


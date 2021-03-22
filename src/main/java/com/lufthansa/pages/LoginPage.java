package com.lufthansa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.lufthansa.base.LufthansaTestBase;

public class LoginPage extends LufthansaTestBase{
	
	@FindBy(xpath="//button[@id='cm-acceptAll']")
	WebElement AcceptBtn;
	
	@FindBy(xpath ="//input[@placeholder='E-Mail address']")
	WebElement UserName;
	
	@FindBy(xpath ="//input[@placeholder='Password']")
	WebElement Password;
	
	@FindBy(xpath ="//button[@class='btn btn-primary mb-5']/span[contains(text(),'Login')]")
	WebElement LoginBtn;
	
	@FindBy(xpath ="//div[contains(text(),'You are not authorized to access this resource.')]")
	WebElement ErrorMsg;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public boolean EnterLoginDetails(String username, String password) {
		AcceptBtn.click();
		UserName.sendKeys(username);
		Password.sendKeys(password);
		LoginBtn.click();
		return ErrorMsg.isDisplayed();
	}

}

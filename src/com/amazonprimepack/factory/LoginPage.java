package com.amazonprimepack.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;
	@FindBy(id="ap_email")
	WebElement appEmail;
	
	@FindBy(id="ap_password")
	WebElement appPassword;
	
	@FindBy(id="signInSubmit")
	WebElement signInSubmit;
	
	@FindBy(id="WLHUC_viewlist")
	WebElement viewList;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void performLogin(String userName,String password) {
		appEmail.sendKeys(userName);
		appPassword.sendKeys(password);
		signInSubmit.click();
		viewList.click();
		}

}

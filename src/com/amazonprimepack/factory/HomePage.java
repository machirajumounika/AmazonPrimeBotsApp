package com.amazonprimepack.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	private WebDriver driver;
	private String searchTextBox;
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitButton;
	
	@FindBy(id="twotabsearchtextbox")
	WebElement wishListButton;
	
	private Actions actions;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.driver = driver;
		this.actions=new Actions(driver);
		PageFactory.initElements(driver, this);
		
	}
	
	public String getSearchTextBox() {
		return wishListButton.getTagName();
	}

	public void testSearchTextBox(String text) {
		wishListButton.sendKeys(text);
		submitButton.click();
	}

	public void testPerformSlowness() {
		Action sendEsc = actions.sendKeys(Keys.ESCAPE).build();
		sendEsc.perform();
	}

	public void testPageDown() {
		Action sendPagedown = actions.sendKeys(Keys.PAGE_DOWN).build();
		sendPagedown.perform();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

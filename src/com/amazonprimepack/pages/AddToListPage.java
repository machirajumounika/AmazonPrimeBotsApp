package com.amazonprimepack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToListPage {
	WebDriver driver;
	@FindBy(className="s-image")
	WebElement clickOnImage;
	
	@FindBy(id="add-to-wishlist-button-submit")
	WebElement wishListButton;
	
	@FindBy(linkText="Create a List")
	WebElement createAList;
	
	@FindBy(xpath="//*[@id='WLNEW_create']/span/span/input")
	WebElement create;
	
	@FindBy(partialLinkText="Your List")
	WebElement navLinkAccountList;
	
	@FindBy(xpath="//*[@id=\"nav-link-accountList\"]")
	WebElement accounts;
	
	//*[@id="nav-link-accountList"]/span[2]
	
	//header[@class='nav-opt-sprite nav-locale-us nav-lang-en nav-ssl nav-rec'/following::div[@id='nav-tools]/following::a[@id='nav-link-accountList']/span[2]
	
	 /*Neelima:-------------------------------------
		
	.//header[@class='nav-opt-sprite nav-locale-us nav-lang-en nav-ssl nav-rec']/div[@id='navbar']/div[2]/div[2]/div[1]/a[2]/span[contains(@class, 'nav-line-2')]
	
	.//header[@class='nav-opt-sprite nav-locale-us nav-lang-en nav-ssl nav-rec']/div[@id='navbar']/div[2]/div[2]/div[1]/a[2]/span[text()= 'Account & Lists']
	
	//*[@id="nav-link-accountList"]/span[4]
--------------------------------------------------------*/	
	
	
	private Actions actions;
	
	public String getWish() {
		return wishListButton.getText();
	}
	
	
	public String getCreatList() {
		return createAList.getText();
	}
	public AddToListPage(WebDriver driver){
		this.driver = driver;
		//This initElements method will create  all WebElements
		this.actions=new Actions(driver);
		PageFactory.initElements(driver, this);
	}


	public void clickOnImage() {

		clickOnImage.click();
	}

	public void saveToWatchList() {
		Action sendPagedown1 = actions.sendKeys(Keys.PAGE_DOWN).build();
		sendPagedown1.perform();
		wishListButton.click();
	}

	public void createList() throws InterruptedException {
		createAList.click();
		Thread.sleep(2000);
		create.click();
		Thread.sleep(1000);
	}
	
	public void mouseHover() throws InterruptedException {
		Thread.sleep(500);
		actions.moveToElement(accounts).build().perform();
		Thread.sleep(500);
	}
	
	public void clickOnYourOrders() throws InterruptedException {
		Thread.sleep(500);
		navLinkAccountList.click();
		actions.moveToElement(navLinkAccountList).build().perform();
		Thread.sleep(500);
	}
}

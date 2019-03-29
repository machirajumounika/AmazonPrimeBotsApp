package com.amazonprimepages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToListPage {
	private WebDriver driver;
	private Actions actions;
	
	@FindBy(xpath="//*[@id='nav-al-your-account']/a[3]")
	WebElement viewList;

	
	public AddToListPage(WebDriver driver){
		this.driver = driver;
		this.actions=new Actions(driver);
	}


	public void clickOnImage() {

		driver.findElement(By.className("s-image")).click();
	}

	public void saveToWatchList() {
		Action sendPagedown1 = actions.sendKeys(Keys.PAGE_DOWN).build();
		sendPagedown1.perform();
		driver.findElement(By.id("add-to-wishlist-button-submit")).click();
	}

	public void createList() throws InterruptedException {
		driver.findElement(By.linkText("Create a List")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("WLNEW_create")).click();
		Thread.sleep(1000);
	}
	
	public void mouseHover() throws InterruptedException {
		Thread.sleep(500);
	//	WebElement accountList = driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));
		WebElement accountList = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[2]"));
		actions.moveToElement(accountList).build().perform();
		Thread.sleep(500);
		
	}
}

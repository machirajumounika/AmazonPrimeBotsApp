package com.amazonprimepages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
	private WebDriver driver;
	private Actions actions;
	public LoginPage(WebDriver driver){
		this.driver = driver;
		this.actions=new Actions(driver);
	}
	public void performLogin() {
		driver.findElement(By.id("ap_email")).sendKeys("mmounika92@gmail.com");
		driver.findElement(By.id("ap_password")).sendKeys("Qweasdzxc87");
		driver.findElement(By.id("signInSubmit")).click();
		driver.findElement(By.id("WLHUC_viewlist")).click();


	}

}

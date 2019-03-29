package com.amazonprimepack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazonprimepack.pages.AddToListPage;
import com.amazonprimepack.pages.HomePage;
import com.amazonprimepack.pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class TestBase {

	private WebDriver driver;
	private AddToListPage addToList;
	private HomePage homePage;
	private LoginPage loginPage;
	private ExtentHtmlReporter htmlReporter;
	private ExtentReports extent;
	private ExtentTest logger;

	@BeforeTest
	public void beforeTest() {
		try {
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
			// Create an object of Extent Reports
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "SoftwareTesting");
			extent.setSystemInfo("Environment", "Test");
			extent.setSystemInfo("User Name", "Mounika Machiraju");
			htmlReporter.config().setDocumentTitle("Title");
			// Name of the report
			htmlReporter.config().setReportName("AmazonPrimeBots");
			// Dark Theme
			htmlReporter.config().setTheme(Theme.STANDARD);
			logger = extent.createTest("To verify Amazon WebSite");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Owner\\Desktop\\SELENIUM  FILES\\Exe files\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.amazon.com");
		driver.manage().window().maximize();
	}

	@Test
	public void test_Home_Page() throws InterruptedException {
		addToList = new AddToListPage(driver);
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		homePage.testSearchTextBox("mobile phone");
		Thread.sleep(5000);
		Assert.assertTrue(homePage.getSearchTextBox().toLowerCase().contains("input"));

		//homePage.testPerformSlowness();
		homePage.testPageDown();
		addToList.clickOnImage();

		addToList.saveToWatchList();
		Thread.sleep(5000);
		loginPage.performLogin("mmounika92@gmail.com", "Qweasdzxc87");
		Assert.assertTrue(addToList.getCreatList().contains("Create a List"));
		addToList.createList();
		Thread.sleep(5000);
		addToList.mouseHover();
		addToList.clickOnYourOrders();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = getScreenShot(driver, result.getName());
			// To add it in the extent report
			logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
		driver.quit();
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}
}

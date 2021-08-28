package com.vtiger.comcast.genericutility.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.vtiger.comcast.pomrepository.library.HomePage;
import com.vtiger.comcast.pomrepository.library.LoginPage;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver webDriver;
	public WebDriverUtility wL = new WebDriverUtility();
	public JavaUtility jL = new JavaUtility();
	public FileUtility fL = new FileUtility();
	public ExcelUtility eL = new ExcelUtility();
	@BeforeClass(groups = {"smoke-testing","regression-testing"})
	public void beforeClassConfiguration() throws Throwable {
		//launching the browser
		String browser=fL.getPropertyKeyValue("browser");
		if(browser.equals("chrome")) {
			
			driver= new ChromeDriver();
			webDriver= driver;
		}
		else if(browser.equals("firefox")) {
			
			driver= new FirefoxDriver();
		}
		else if(browser.equals("ineternetExplorer")) {
			
			driver= new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		wL.waitUntilPageLoad(driver);
		String url=fL.getPropertyKeyValue("url");
		driver.get(url);
	}
	
	@AfterClass(groups = {"smoke-testing","regression-testing"})
public void afterClassConfiguration() {
		driver.quit();
	}
	
	@BeforeMethod(groups = {"smoke-testing","regression-testing"})
	public void beforeMethodConfiguration() throws Throwable {
		System.out.println("..............Test Starts...........");
		String userName=fL.getPropertyKeyValue("userName");
		String password=fL.getPropertyKeyValue("password");
		LoginPage loginPage = new LoginPage(driver);
		
		//to verify login page is displayed
		loginPage.isLoginPageDisplayed();
		
		// login to application and verify home page is displayed
		HomePage homePage = loginPage.login(userName, password);
		homePage.isHomePageDisplayed();
	}
	
	@AfterMethod(groups = {"smoke-testing","regression-testing"})
	public void afterMethodConfiguration() {
	
		new HomePage(driver).logOut();
		System.out.println("..............Test Ends...........");
	}
	

}

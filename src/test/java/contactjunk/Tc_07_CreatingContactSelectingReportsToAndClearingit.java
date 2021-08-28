package contactjunk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vtiger.comcast.genericutility.library.ExcelUtility;
import com.vtiger.comcast.genericutility.library.FileUtility;
import com.vtiger.comcast.genericutility.library.JavaUtility;
import com.vtiger.comcast.genericutility.library.WebDriverUtility;
import com.vtiger.comcast.pomrepository.library.ContactInformationPage;
import com.vtiger.comcast.pomrepository.library.ContactPage;
import com.vtiger.comcast.pomrepository.library.CreateContactPage;
import com.vtiger.comcast.pomrepository.library.HomePage;
import com.vtiger.comcast.pomrepository.library.LoginPage;

public class Tc_07_CreatingContactSelectingReportsToAndClearingit {
	
public static void main(String[] args) throws Throwable {
		
	WebDriverUtility wL = new WebDriverUtility();
	JavaUtility jL = new JavaUtility();
	FileUtility fL = new FileUtility();
	ExcelUtility eL = new ExcelUtility();

		String userName=fL.getPropertyKeyValue("userName");
		String password=fL.getPropertyKeyValue("password");
		String url=fL.getPropertyKeyValue("url");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		wL.waitUntilPageLoad(driver);
		driver.get(url);
		String contactName=eL.getDataFromExcel("Sheet1", 10, 2)+jL.getRandomNumber();
		
		LoginPage loginPage = new LoginPage(driver);
		
		//to verify login page is displayed
		loginPage.isLoginPageDisplayed();
		
		// login to application and verify home page is displayed
		HomePage homePage = loginPage.login(userName, password);
		homePage.isHomePageDisplayed();
		
		//click on Contacts link and verify contacts page is displayed
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();
		
		//click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();
		
		//creating the contact
		createContact.getLastNameTextField().sendKeys(contactName);
		createContact.getReportsToAddButton().click();
		
		//Transferring the driver control to child window
		wL.switchToWindow(driver, "Contacts&action");
		
		//selecting the contact
		driver.findElement(By.id("1")).click();
		
		// Transferring control back to parent window
		wL.switchToWindow(driver, "Contacts");
		
		//verifying the contact selected
		createContact.isReportsToSelected();
		
		//clearing the reports To and verifying it is cleared
		createContact.getClearReportsToTextField().click();
		createContact.isReportsTocleared();
		
		ContactInformationPage contactInfo = createContact.save();
		
		//verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);
		
		//logout
		homePage.clikOnContact();
		homePage.logOut();
		driver.quit();
	}
}

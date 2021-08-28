package contactjunk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.vtiger.comcast.genericutility.library.ExcelUtility;
import com.vtiger.comcast.genericutility.library.FileUtility;
import com.vtiger.comcast.genericutility.library.JavaUtility;
import com.vtiger.comcast.genericutility.library.WebDriverUtility;
import com.vtiger.comcast.pomrepository.library.ContactInformationPage;
import com.vtiger.comcast.pomrepository.library.ContactPage;
import com.vtiger.comcast.pomrepository.library.CreateContactPage;
import com.vtiger.comcast.pomrepository.library.HomePage;
import com.vtiger.comcast.pomrepository.library.LoginPage;

public class Tc_11_CreatingContactwithAssignedToSupportGroup {
	
	@Test  
	public void CreatingContactwithAssignedToSupportGroupTest() throws Throwable {
		
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
		String assignedGroup=eL.getDataFromExcel("Sheet1", 10, 4);
		
		LoginPage loginPage = new LoginPage(driver);
		
		//to verify login page is displayed
		loginPage.isLoginPageDisplayed();
		
		// login to application and verify home page is displayed
		HomePage homePage = loginPage.login(userName, password);
		homePage.isHomePageDisplayed();
		
		//click on Contacts link and verify contacts page is displayed
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();
		
		//to delete Existing Contact
		//contactPage.deleteExistingContact(contactName);
		
		//click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();
		
		//creating the contact
		ContactInformationPage contactInfo = createContact.createContactWithAssignedToGroup(contactName, assignedGroup);
		
		//verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);
		
		//to verify contact is created and assigned to selected group
		homePage.clikOnContact().isContactCreatedAsignedToGroup(contactName, assignedGroup);
		
		homePage.logOut();
		driver.quit();
	}
}

package contact;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vtiger.comcast.genericutility.library.BaseClass;
import com.vtiger.comcast.pomrepository.library.ContactInformationPage;
import com.vtiger.comcast.pomrepository.library.ContactPage;
import com.vtiger.comcast.pomrepository.library.CreateContactPage;
import com.vtiger.comcast.pomrepository.library.HomePage;
@Listeners(com.vtiger.comcast.genericutility.library.ListnerLib.class)
	public class CreateContactTest extends BaseClass {
	@Test(groups = {"smoke-testing","regression-testing"})
	public void CreatingContactwithLeadSource() throws Throwable{
		// getting test script data
		String contactName=eL.getDataFromExcel("Sheet1", 1, 2)+jL.getRandomNumber();
		String assignedLeadSource=eL.getDataFromExcel("Sheet1", 1, 3);
		String assignedGroup=eL.getDataFromExcel("Sheet1", 1, 4);
		
		//click on Contacts link and verify contacts page is displayed
				HomePage homePage = new HomePage(driver);
				ContactPage contactPage = homePage.clikOnContact();
				contactPage.isContactPageDisplayed();
				
				//click on create contact button and verify create contact page is displayed
				CreateContactPage createContact = contactPage.createContact();
				createContact.isCreateContactPageDisplayed();
				
				//creating the contact
				ContactInformationPage contactInfo = createContact.createContactWithLeadSource(contactName, assignedGroup, assignedLeadSource);
				
				//verifying Header text in contact information page is correct and Lead Source is selected
				contactInfo.isContactInfoHeaderCorrect(contactName);
				boolean lesdSource = contactInfo.getLeadSourceText().getText().contains(assignedLeadSource);
				Assert.assertTrue(lesdSource, "lead source is Not selected");
					System.out.println("lead source is selected");
	}
	
	@Test(groups = {"regression-testing"})
	public void CreatingContactwithAssignedToAdministratorTest() throws Throwable {
		// getting test script data
		String contactName=eL.getDataFromExcel("Sheet1", 4, 2)+jL.getRandomNumber();
		String assignedGroup=eL.getDataFromExcel("Sheet1", 4, 4);
		
		//click on Contacts link and verify contacts page is displayed
		HomePage homePage = new HomePage(driver);
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();
				
		//click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();
				
		//creating the contact
		ContactInformationPage contactInfo = createContact.createContactWithAssignedToUser(contactName, assignedGroup);
				
		//verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);
	}
	
	@Test(groups = {"regression-testing"})
	public void CreatingContactwithAssignedToMarketingGroupTest() throws Throwable{
		// getting test script data
		String contactName=eL.getDataFromExcel("Sheet1", 7, 2)+jL.getRandomNumber();
		String assignedGroup=eL.getDataFromExcel("Sheet1", 7, 4);
		
		//click on Contacts link and verify contacts page is displayed
		HomePage homePage = new HomePage(driver);
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();
				
		//click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();
				
		//creating the contact
		ContactInformationPage contactInfo = createContact.createContactWithAssignedToGroup(contactName, assignedGroup);
				
		//verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);
	}
	
	@Test(groups = {"regression-testing"})
	public void CreatingContactwithAssignedToSupportGroupTest() throws Throwable{
		// getting test script data
		String contactName=eL.getDataFromExcel("Sheet1", 10, 2)+jL.getRandomNumber();
		String assignedGroup=eL.getDataFromExcel("Sheet1", 10, 4);
		
		//click on Contacts link and verify contacts page is displayed
		HomePage homePage = new HomePage(driver);
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();
				
		//click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();
				
		//creating the contact
		ContactInformationPage contactInfo = createContact.createContactWithAssignedToGroup(contactName, assignedGroup);
				
		//verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);
	}
	
	@Test(groups = {"regression-testing"})
	public void CreatingContactwithAssignedToTeamSellingGroupTest() throws Throwable{
		// getting test script data
		String contactName=eL.getDataFromExcel("Sheet1", 13, 2)+jL.getRandomNumber();
		String assignedGroup=eL.getDataFromExcel("Sheet1", 13, 4);
		
		//click on Contacts link and verify contacts page is displayed
		HomePage homePage = new HomePage(driver);
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();
				
		//click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();
				
		//creating the contact
		ContactInformationPage contactInfo = createContact.createContactWithAssignedToGroup(contactName, assignedGroup);
				
		//verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);
	}

}

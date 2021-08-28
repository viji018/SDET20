package com.vtiger.comcast.pomrepository.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.vtiger.comcast.genericutility.library.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility {
	WebDriver driver;
	public CreateContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHederText;
	
	@FindBy(name="lastname")
	private WebElement lastNameTextField;
	
	@FindBy(xpath="//input[@value='T']")
	private WebElement groupRadioButton;
	
	@FindBy(xpath="//input[@value='U']")
	private WebElement userRadioButton;
	
	@FindBy(name="assigned_group_id")
	private WebElement goupAssignedToDropDown;
	
	@FindBy(name="assigned_user_id")
	private WebElement userAssignedToDropDown;

	@FindBy(name="leadsource")
	private WebElement leadSourceDropDown;
	
	@FindBy(xpath="//input[@class='crmbutton small save']")
	private WebElement saveButton;
	
	@FindBy(xpath="//img[contains(@onclick,'selectContact')]")
	private WebElement reportsToAddButton;
	
	@FindBy(xpath="//img[contains(@onclick,'selectContact')]/following-sibling::input[@title='Clear']")
	private WebElement clearReportsToTextField;
	
	@FindBy(name="contact_id")
	private WebElement reportsToContacId;
	
	
	
	public WebElement getGoupAssignedToDropDown() {
		return goupAssignedToDropDown;
	}

	public WebElement getReportsToAddButton() {
		return reportsToAddButton;
	}

	public WebElement getClearReportsToTextField() {
		return clearReportsToTextField;
	}

	public WebElement getReportsToContacId() {
		return reportsToContacId;
	}

	public WebElement getPageHederText() {
		return pageHederText;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getGroupRadioButton() {
		return groupRadioButton;
	}

	public WebElement getGroupssignedToDropDown() {
		return goupAssignedToDropDown;
	}
	
	public WebElement getLeadSourceDropDown() {
		return leadSourceDropDown;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	public WebElement getUserRadioButton() {
		return userRadioButton;
	}

	public WebElement getUserAssignedToDropDown() {
		return userAssignedToDropDown;
	}
	
	// to verify create contact page is displayed
	public void isCreateContactPageDisplayed() {
		boolean contactPage = pageHederText.getText().contains("Creating New Contact");
		Assert.assertTrue(contactPage, "Create contact page is Not displayed");
			System.out.println("Create contact page is displayed");
	}
	
	//method to create contact with Assigned to user
	public ContactInformationPage createContactWithAssignedToUser(String contactName,String AssignedTo) {
		getLastNameTextField().sendKeys(contactName);
		getUserRadioButton().click();
		//select(userAssignedToDropDown, AssignedTo);
		saveButton.click();
		return new ContactInformationPage(driver);
	}
	
	//method to create contact with Assigned to Group
		public ContactInformationPage createContactWithAssignedToGroup(String contactName,String AssignedTo) {
			getLastNameTextField().sendKeys(contactName);
			getGroupRadioButton().click();
			select(goupAssignedToDropDown, AssignedTo);
			saveButton.click();
			return new ContactInformationPage(driver);
		}
		
		//method to create contact with lead Source
		public ContactInformationPage createContactWithLeadSource(String contactName,String AssignedTo,String leadSource) throws InterruptedException {
			getLastNameTextField().sendKeys(contactName);
			select(leadSourceDropDown, leadSource);
			getUserRadioButton().click();
			//select(userAssignedToDropDown, AssignedTo);
			saveButton.click();
			return new ContactInformationPage(driver);
		}
		
		//method to save
		public ContactInformationPage save() {
			saveButton.click();
			return new ContactInformationPage(driver);
		}
		
		// to verify Reports to contact selected
		public void isReportsToSelected() {
			boolean reportSelected = reportsToContacId.getAttribute("value").isEmpty();
			SoftAssert softAssert= new SoftAssert();
			softAssert.assertFalse(reportSelected, "FAIL: Reports To contact not selected");
			System.out.println("PASS: Reports To contact is selected");
		}
		
		// to verify Reports to contact cleared
				public void isReportsTocleared() {
					boolean isCleared = reportsToContacId.getAttribute("value").isEmpty();
					SoftAssert softAssert= new SoftAssert();
					softAssert.assertTrue(isCleared, "FAIL: Reports To contact is Not cleared");
					System.out.println("PASS: Reports To contact is cleared");
				}
	
}

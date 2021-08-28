package com.vtiger.comcast.pomrepository.library;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.vtiger.comcast.genericutility.library.WebDriverUtility;

public class ContactPage extends WebDriverUtility {
	
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createContactButton;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchInDropDown;
	
	@FindBy(name="search_text")
	private WebElement searchForTextField;
	
	@FindBy(name="submit")
	private WebElement searchNowButton;
	
	public WebElement getCreateContactButton() {
		return createContactButton;
	}

	// method to verify Contacts page is displayed
		public void isContactPageDisplayed() {
			
			
			boolean contactPage = driver.getTitle().contains("Contacts");
			Assert.assertTrue(contactPage, "Contacts page is Not dispalyed");
				System.out.println("Contacts page is dispalyed");
		}
	// method to click on create contact
		
	public CreateContactPage createContact() {
		createContactButton.click();
		return new CreateContactPage(driver);
	}
	
	// to verify the contact is created and assigned to selected group
	public void isContactCreatedAsignedToGroup(String contactName, String assignedGroup) {
		
		try
		{
			searchForTextField.sendKeys(contactName);
			select(searchInDropDown, "Last Name");
			searchNowButton.click();
		driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='"+contactName+"']"));
		
		//to verify Assigned To
		String assignedTo= driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='"+contactName+"']/../..//span[@vtfieldname='assigned_user_id']/..")).getText();
		boolean isAssigned = assignedTo.contains(assignedGroup);
		Assert.assertTrue(isAssigned, "FAIL : Contact is succesfully created but Not Assigned to "+assignedGroup+"");
			System.out.println("PASS : Contact is succesfully created and Assigned to "+assignedGroup);
		}
	catch(NoSuchElementException n) 
		{
		System.out.println("FAIL : Contact is Not created");
		}
	}
	
	//to delete Existing Contact
	public void deleteExistingContact(String contactName) {
		try
		{
		driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='"+contactName+"']/../..//input")).click();
		driver.findElement(By.cssSelector("input[value='Delete']")).click();
		driver.switchTo().alert().accept();
		}
		catch(NoSuchElementException n)
		{
			
		}
	}
}

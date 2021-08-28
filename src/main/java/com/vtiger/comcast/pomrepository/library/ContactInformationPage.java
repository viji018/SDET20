package com.vtiger.comcast.pomrepository.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactInformationPage {
	
	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement pageHederText;
	
	@FindBy(id="mouseArea_Lead Source")
	private WebElement leadSourceText;
	
	@FindBy(id="mouseArea_Assigned To")
	private WebElement assignedToText;
	
	public WebElement getAssignedToText() {
		return assignedToText;
	}

	public WebElement getPageHederText() {
		return pageHederText;
	}
	
	public WebElement getLeadSourceText() {
		return leadSourceText;
	}

	//method to verify the header text is correct
	public void isContactInfoHeaderCorrect(String contactName) {
		System.out.println("Header textt is : "+pageHederText.getText());
		boolean hesderText = pageHederText.getText().contains(contactName+" - Contact Information");
		Assert.assertTrue(hesderText, "FAIL : Header message is not correct");
		System.out.println("PASS : Header message is correct");
	}
	
	//method to verify the Assigned To is correct
		public void isAssignedTo(String assignedGroup) {
			boolean assignedText = assignedToText.getText().contains(assignedGroup);
			Assert.assertTrue(assignedText, "FAIL : Not Assigned to selected group");
			System.out.println("PASS : Assigned to selected group");
		}

}

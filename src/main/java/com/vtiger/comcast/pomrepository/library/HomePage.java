package com.vtiger.comcast.pomrepository.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Contacts")
	private WebElement ContactsLink;
	
	@FindBy(xpath="//img[contains(@src,'user')]")
	private WebElement userIcon;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	
	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}

	// method to logout
	public LoginPage logOut() {
		Actions actions = new Actions(driver);
		actions.moveToElement(userIcon).perform();
		signOutLink.click();
		return new LoginPage(driver);
	}
	
	// to verify Home page is displayed
	public void isHomePageDisplayed() {
			boolean homeaPage = userIcon.isDisplayed();
			Assert.assertTrue(homeaPage, "Home page is Not displayed");
			System.out.println("Home page is displayed");
	}
	
	// to click on create Contact
	public ContactPage clikOnContact() {
		ContactsLink.click();
		return new ContactPage(driver);
	}
}

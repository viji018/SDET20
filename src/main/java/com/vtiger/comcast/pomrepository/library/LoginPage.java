package com.vtiger.comcast.pomrepository.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement userNameTextField;
	
	@FindBy(name="user_password")
	private WebElement passwordTextField;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getUserNameTextField() {
		return userNameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	// login method
	public HomePage login(String userName, String password) {
		userNameTextField.sendKeys(userName);
		passwordTextField.sendKeys(password);
		loginButton.click();
		return new HomePage(driver);
	}
	
	//method to verify the login page is displayed
	
	public void isLoginPageDisplayed() {
		
			boolean loginPage = loginButton.isDisplayed();
			Assert.assertTrue(loginPage, "Login page is Not displayed");
			System.out.println("Login page is displayed");
	}
	
}

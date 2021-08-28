package contactjunk;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Test11 {
public static void main(String[] args) throws IOException, Exception {
		
		FileInputStream fin = new FileInputStream("./data/commonData.properties");
		Properties prop = new Properties();
		prop.load(fin);
		String url =prop.getProperty("url");
		String userName =prop.getProperty("userName");
		String password =prop.getProperty("password");
		String browser =prop.getProperty("browser");
		
		//WebDriver Selection
		WebDriver driver = null;
		if(browser.equals("chrome")) {
		
			driver= new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
			
			driver= new FirefoxDriver();
		}
		else if(browser.equals("ineternetExplorer")) {
			
			driver= new InternetExplorerDriver();
		}
		
		// window pre condition
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
		
		//navigating to url
		driver.get(url);
		
		// to verify login page displayed
		if(driver.findElement(By.id("submitButton")).isDisplayed())
			System.out.println("Login Page displayed");
		else
			System.out.println("Login Page Not displayed");
			
		// Login using credentials
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//to verify home page is displayed 
		if(driver.findElement(By.xpath("//img[contains(@src,'user')]")).isDisplayed())
			System.out.println("Home Page displayed");
		else
			System.out.println("Home Page Not displayed");
		
		// to click on contacts module
		driver.findElement(By.xpath("//table[@class='hdrTabBg']//a[text()='Contacts']")).click();
		
		// to verify contacts page is displayed
		if(driver.getTitle().contains("Contacts"))
			System.out.println("contacts Page is displayed");
		else
			System.out.println("contacts Page Not displayed");
		// to delete existing contact for multiple runs of this script 
				try
				{
				driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='piyush1']/../..//input")).click();
				driver.findElement(By.cssSelector("input[value='Delete']")).click();
				driver.switchTo().alert().accept();
				}
				catch(NoSuchElementException n)
				{
					
				}
				
		driver.findElement(By.cssSelector("img[title='Create Contact...']")).click();
		
		// to verify Creating New Contact header is displayed
		if(driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText().contains("Creating New Contact"))
		System.out.println("Creating New Contact Page is displayed");
		else
		System.out.println("Creating New Contact Not displayed");
		
		// creating contact 
		driver.findElement(By.name("lastname")).sendKeys("piyush1");
		driver.findElement(By.cssSelector("input[value='T']")).click();
		Select assignedTo = new Select(driver.findElement(By.name("assigned_group_id")));
		assignedTo.selectByVisibleText("Support Group");
		driver.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
		
		// verifying Header Text
		String actualHeaderText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String expectedHeaderText = "piyush1 - Contact Information";
		System.out.println("Header message: "+actualHeaderText);
		if(actualHeaderText.contains(expectedHeaderText))
			{
			System.out.println("PASS : Header message is correct");
			}
		else 
			{
			System.out.println("FAIL : Header message is not correct");
			}
		
		// to verify the contact created successfully
		driver.findElement(By.cssSelector("a[class='hdrLink']")).click();
		try
		{
		driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='piyush1']"));
		//to verify Assigned To
		String assignedTo1 = driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='piyush1']/../..//span[@vtfieldname='assigned_user_id']/..")).getText();
		if(assignedTo1.contains("Support Group"))
			System.out.println("PASS : Contact is succesfully created and Assigned to Support Group");
		else
			System.out.println("PASS : Contact is succesfully created but Not Assigned to Support Group");
		}
	catch(NoSuchElementException n) 
		{
		System.out.println("FAIL : Contact is Not created");
		}
		
		// Logout
		Actions actions = new Actions(driver);
		WebElement usreIcon = driver.findElement(By.xpath("//img[contains(@src,'user')]"));
		actions.moveToElement(usreIcon).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(3000);
		driver.quit();
	} 
}

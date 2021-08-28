package sampletest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Test1 {

	public static void main(String[] args) throws IOException, Exception {
		
		FileInputStream fin = new FileInputStream("./commonData.properties");
		Properties prop = new Properties();
		prop.load(fin);
		String url =prop.getProperty("url");
		String userName =prop.getProperty("userName");
		String password =prop.getProperty("password");
		String organizationName =prop.getProperty("organizationName");
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
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//table[@class='hdrTabBg']//a[text()='Organizations']")).click();
		
		// to delete existing organization
				try
				{
				driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='"+organizationName+"']/../..//input")).click();
				driver.findElement(By.cssSelector("input[value='Delete']")).click();
				driver.switchTo().alert().accept();
				}
				catch(NoSuchElementException n)
				{
					
				}
				
		// creating a organization		
		driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
		Thread.sleep(2000);
		//driver.navigate().to("http://localhost:8888/index.php?module=Accounts&action=index");
		//driver.findElement(By.cssSelector("a[class='hdrLink']")).click();
		
		
		// verifying 
		try
			{
			driver.findElement(By.xpath("//table[@class='lvt small']//a[text()='"+organizationName+"']")); //"+organizationName+"
			System.out.println("PASS : the Organization "+organizationName+" is succesfully created");
			}
		catch(NoSuchElementException n) 
			{
			System.out.println("FAIL : the Organization "+organizationName+" is Not created");
			}
		Thread.sleep(5000);
		driver.quit();
	}
}


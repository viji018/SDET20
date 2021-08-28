package sampletest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class IOExcelTest {
public static void main(String[] args) throws Exception {
		
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
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//reading data from Excel Sheet
		FileInputStream fin2 = new FileInputStream("./data/TestscriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fin2);
		Sheet sh = wb.getSheet("Sheet1");
		Row tcRow1 = sh.getRow(1);
		String organizationName = tcRow1.getCell(2).getStringCellValue();

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
		String actualHeaderText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		System.out.println("Organization Name: "+organizationName);
		System.out.println("Header message: "+actualHeaderText);
		
		// verifying and updating status
		if(actualHeaderText.contains(organizationName))
			{
			System.out.println("PASS : Header message contains the Organization Name");
			Cell statusCell = tcRow1.createCell(4);
			statusCell.setCellValue("PASS");
			}
		else 
			{
			System.out.println("FAIL : Header message does contains the Organization Name");
			Cell statusCell = tcRow1.createCell(4);
			statusCell.setCellValue("Fail");
			}
		FileOutputStream fout = new FileOutputStream("./data/TestscriptData.xlsx");
		wb.write(fout);
		wb.close();
		System.out.println("TastCase Staus Updated in TestScriptData File");
		
		// Logout
		Actions actions = new Actions(driver);
		WebElement usreIcon = driver.findElement(By.xpath("//img[contains(@src,'user')]"));
		actions.moveToElement(usreIcon).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(3000);
		driver.quit();
	}
}

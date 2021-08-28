package com.vtiger.comcast.genericutility.library;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * this class contains WebDriver Specific methods
 * @author KIRAN S
 *
 */
public class WebDriverUtility {

	/**
	 * this method is used to wait for specified time in seconds until page loads
	 * @param driver
	 */
	   public void waitUntilPageLoad(WebDriver driver)
	   {
		   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		   
	   }
	   /**
	    * this method is used to wait for specified time in seconds until the specified element is vissible
	    * @param driver
	    * @param element
	    */
	   public void waitForElementVisibility(WebDriver driver,WebElement element)
	   {
		  WebDriverWait wait = new WebDriverWait(driver, 20);
		  wait.until(ExpectedConditions.visibilityOf(element));
	   }
	   
	   /**
	    * this method is used to wait until specified element to be click able state
	    * this is the custom wait created to avoid ElementNotInteractableException
	    * @param element
	    * @throws InterruptedException
	    */
	   public void waitAndClick(WebElement element) throws InterruptedException{
		   int count = 0;
	       while(count<20) {
		    	   try {
		    	       element.click();
		    	       break;
		    	   }catch(Throwable e){
		    		   Thread.sleep(1000);
		    		   count++;
		    	   }
	       }
	       
	   }

	   /**
	    * this method enables user to select an option in drop-down list using visible text
	    * @param element
	    * @param option
	    */
	   public void select(WebElement element, String option)
	   {
		   Select select=new Select(element);
		   select.selectByVisibleText(option);  
	   }
	   
	   /**
	    * this method enables user to select an option in drop-down list using index
	    * @param element
	    * @param index
	    */
	   public void select(WebElement element, int index)
	   {
		   Select select=new Select(element);
		   select.selectByIndex(index);   
	   }
	   
	   /**
	    * this method performs mouse hover to specified WebElement
	    * @param driver
	    * @param element
	    */
	   public void mouseOver(WebDriver driver,WebElement element)
	   {
		   Actions act = new Actions(driver);
		   act.moveToElement(element).perform();
		   
	   }
	   
	   /***
	    * this method perform right click on specified WebElement
	    * @param driver
	    * @param element
	    */
	   public void rightClick(WebDriver driver,WebElement element)
	   {
		   Actions act = new Actions(driver);
		   act.contextClick(element).perform();
	   }
	   
	   
	   /**
	    * this method helps to switch from one window to another
	    * @param driver
	    * @param partialWinTitle
	    */
	   public void switchToWindow(WebDriver driver, String partialWinTitle){
		   Set<String> window = driver.getWindowHandles();
		   Iterator<String> it = window.iterator();
		   while(it.hasNext())
		   {
			   String winId=it.next();
			   String title=driver.switchTo().window(winId).getTitle();
	           if(title.contains(partialWinTitle))
	           {
	        	   break;
	           }
			   
		   }
		   
	   }

	   /**
	    * to Accept the alert pop-up
	    * @param driver
	    */
	   public void acceptAlert(WebDriver driver)
	   {
		   driver.switchTo().alert().accept();
	   }
	   
	   /**
	    * to cancel the alert pop-up
	    * @param driver
	    */
	   public void cancelAlert(WebDriver driver)
	   {
		   driver.switchTo().alert().dismiss();
	   }

	   	/**
	   	 * this method is used to scroll on web page to a specified WebElement
	   	 * @param driver
	   	 * @param element
	   	 */
	   public void scrollToWebElement(WebDriver driver, WebElement element) {
		   JavascriptExecutor js=(JavascriptExecutor)driver;
		  int y= element.getLocation().getY();
		   js.executeScript("window.scrollBy(0,"+y+")", element);
	   }
	   
	   /**
	    * Switch to a frame by using index
	    * @param driver
	    * @param index
	    */
	   public void switchFrame(WebDriver driver,int index) {
	    	driver.switchTo().frame(index);
	    }   
	    
	   
	   /**
	    * Switch to a frame by using WebElement
	    * @param driver
	    * @param element
	    */
	    public void switchFrame(WebDriver driver,WebElement element) {
	    	driver.switchTo().frame(element);
	    } 
		
	    /**
	     * Switch to a frame by using id/name
	     * @param driver
	     * @param idOrName
	     */
	    public void switchFrame(WebDriver driver,String idOrName) {
	    	driver.switchTo().frame(idOrName);
	    }
	    
	    public void takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
	    	TakesScreenshot ts=(TakesScreenshot)driver;
	    	File src=ts.getScreenshotAs(OutputType.FILE);
	    	File dest=new File("./screenshot/"+screenshotName+".PNG");
	    	Files.copy(src, dest);
	    }

	    /**
	     * this method to pass Enter key on Web Page
	     * @param driver
	     */
	    public void passEnterKey(WebDriver driver) {
	 	   Actions act = new Actions(driver);
	 	   act.sendKeys(Keys.ENTER).perform();
	    }
}

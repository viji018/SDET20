package popup;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NextDateTest {
public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	LocalDateTime ldt = LocalDateTime.now().plusDays(2);
	int date = ldt.getDayOfMonth();
	String month = ldt.getMonth().name();
	int year = ldt.getYear();
	//Actions actions = new Actions(driver);
	//actions.moveByOffset(100, 100).click().perform();
	
	driver.get("https://www.makemytrip.com/");
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	WebElement departure = driver.findElement(By.cssSelector("p[data-cy='departureDate']"));
	jse.executeScript("arguments[0].click()", departure);
	WebElement date1 = driver.findElement(By.xpath("//div[text()='August "+year+"']/../..//p[text()='"+date+"']"));
	jse.executeScript("arguments[0].click()", date1);
	
}
}

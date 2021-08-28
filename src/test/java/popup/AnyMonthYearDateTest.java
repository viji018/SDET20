package popup;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AnyMonthYearDateTest {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		// getting local date time
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		LocalDateTime ldt = LocalDateTime.now().plusMonths(3);
		int day = ldt.getDayOfMonth();
		String month = ldt.getMonth().name();
		month=month.substring(0, 1)+month.substring(1).toLowerCase();
		int year = ldt.getYear();
		
		driver.get("https://www.makemytrip.com/");
		
		// to close sign in pop up using Action click
		Actions actions = new Actions(driver);
		actions.moveByOffset(200, 500).click().perform();
		
		
		driver.findElement(By.cssSelector("p[data-cy='departureDate']")).click();
		
		String date ="//div[text()='"+month+" "+year+"']/ancestor::div[@class='DayPicker-Month']//p[text()='"+day+"']";
		
		WebElement nextArrow = driver.findElement(By.cssSelector("span[aria-label='Next Month']"));
		
		//looping through the next month
		for(;;) {
		try {
			driver.findElement(By.xpath(date)).click();
			break;
		} catch (Exception e) {
			nextArrow.click();
		}
		
		}
		driver.quit();
	}
}

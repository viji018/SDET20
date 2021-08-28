package contact;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OrganizationPageReports extends ExtendBase {

	@Test
	public void gettingOrganizationPageTitle() {
		test= reports.createTest("gettingOrganizationPageTitle");
		
		driver.findElement(By.xpath("//td[@class='tabUnSelected']/a[text()='Organizations']")).click();
		String OrganizationPageTitle = driver.getTitle();
		System.out.println("Organization page title is: "+OrganizationPageTitle);}
}
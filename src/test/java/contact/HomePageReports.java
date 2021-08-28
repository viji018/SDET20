package contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.vtiger.comcast.genericutility.library.ListnerLib.class)
public class HomePageReports extends ExtendBase {
	
	
	@Test
	public void gettingHomePageTitle() {
		
		test= reports.createTest("gettingHomePageTitle");
		String homePageTitle = driver.getTitle();
		System.out.println("Home page title is: "+homePageTitle);
		Assert.assertEquals(homePageTitle, "- Home - vtiger CRM 5 - Commercial Open Source CRM");
		
	}
	
	@Test
	public void gettingOrganizationPageTitle() {
		test= reports.createTest("gettingOrganizationPageTitle");
		
		driver.findElement(By.xpath("//td[@class='tabUnSelected']/a[text()='Organizations']")).click();
		String OrganizationPageTitle = driver.getTitle();
		System.out.println("Organization page title is: "+OrganizationPageTitle);}
}

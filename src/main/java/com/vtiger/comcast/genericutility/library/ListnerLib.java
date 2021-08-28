package com.vtiger.comcast.genericutility.library;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListnerLib implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		EventFiringWebDriver eDrive = new EventFiringWebDriver(BaseClass.webDriver);
		File scrFile = eDrive.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshot/"+testName+".png");
		try {
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

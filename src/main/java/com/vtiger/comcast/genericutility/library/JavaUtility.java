package com.vtiger.comcast.genericutility.library;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;
/**
 * this class contains java specific libraries
 * @author KIRAN S
 *
 */
public class JavaUtility {

	/**
	 * this method generated Random integer number within the range of 10000
	 * @return intData
	 */
	public int getRandomNumber() {
		Random ranDom = new Random();
		int ranDomNum = ranDom.nextInt(10000);
		return ranDomNum;
	}
	
	/**
	 * this method returns system Date and time
	 * @return StringData
	 */
	public String getSystmeDate() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		return systemDateAndTime;
	}
	
	/**
	 * this method returns the current System date with YYYY-MM-DD format
	 * @return StringData
	 */
	public  String getSystmeDate_YYYY_MM__DD() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		System.out.println(systemDateAndTime);
		String[] arr = systemDateAndTime.split(" ");
        String DD = arr[2];
        String YYYY = arr[5];   
        @SuppressWarnings("deprecation")
		int MM = date.getMonth()+1;
        String finalFromat = YYYY+"-"+MM+"-"+DD;
		return finalFromat;	  
	}
	
	/**
	 * this method is used to pass virtual Enter Key to OS
	 * @throws Throwable
	 */
    public void pressVurtualEnterKey() throws Throwable {
    	Robot rc=new Robot();
    	rc.keyPress(KeyEvent.VK_ENTER);
    	rc.keyRelease(KeyEvent.VK_ENTER);
    }

}

package com.vtiger.comcast.genericutility.library;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * this class contains library to read data from common data properties file
 * @author KIRAN S
 *
 */
public class FileUtility {
	
	/**
	 * this method is used to get data from properties File based on Key you pass as an argument
	 * @param key
	 * @return StringData
	 * @throws Throwable
	 */
	public String getPropertyKeyValue(String key) throws Throwable {
   	 FileInputStream fis = new FileInputStream("./data/commonData.properties");
   	 Properties pobj = new Properties();
   	 pobj.load(fis);
   	 String value = pobj.getProperty(key);
		return value;
   }

}

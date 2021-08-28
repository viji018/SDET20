package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class TryCatchTest {

	public static void main(String[] args) throws Throwable {
		Connection connection=null;
		try {
		// creating driver object
		Driver driver = new Driver();
		
		// registering driver
		 DriverManager.registerDriver(driver);
		 
		 // establish the connection
		 
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
		 
		 // create statement object
		  Statement statement = connection.createStatement();
		  
		  // delete the data
		  
		  int result = statement.executeUpdate("delete from student_info where regno='6';");
		  if(result==1) {
			  System.out.println("data is deleted");
		  }
		  else {
			  System.out.println("data is not deleted");
		  }
			  
		}
		catch(Exception e) {
			
		}
		
		finally {
			connection.close();
		}
	}

}

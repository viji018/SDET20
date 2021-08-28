package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DeleteTheDataTest3 {
	public static void main(String[] args) throws Throwable {
		// creating driver object for MYsql
		Driver driver = new Driver();
		
		// Registering the driver
		DriverManager.registerDriver(driver);
		
		// establishing the connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
		
		// creating the statement object
		Statement statement = connection.createStatement();
		
		// deleting the data
		int result = statement.executeUpdate("delete from student_info where regno='6';");
		if(result==1) {
			System.out.println("data is deleted");
		}
		else {
			System.out.println("data is not deleted");
		}
		connection.close();
		
		
		// practice
		//creating the driver object
		Driver driver1 = new Driver();
		
		
		// registering the driver
		DriverManager.registerDriver(driver1);
		// establishing the connection
		Connection connection1 = DriverManager.getConnection(null, null, null);
		// Creating statement
		Statement statement1 = connection1.createStatement();
		// executing the query
		ResultSet resul = statement1.executeQuery(null);
		
	}
}






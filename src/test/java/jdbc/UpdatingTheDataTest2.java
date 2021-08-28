package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class UpdatingTheDataTest2 {
	public static void main(String[] args) throws Throwable {
		
		// create driver object for MySQL
		Driver driver = new Driver();
		
		// register the driver
		DriverManager.registerDriver(driver);
		
		// Establish connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
		
		// create statement object
		Statement statement = connection.createStatement();
		
		// Adding the data
		int result = statement.executeUpdate("insert into student_info values('6','kiran','s','08');");
		if(result==1)
		{
			System.out.println("data is added");
		}
		else {
			System.out.println("data is not adde");
		}
		
		//closing connection
		connection.close();
		
	}
}

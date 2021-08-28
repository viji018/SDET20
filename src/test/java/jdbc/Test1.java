package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Test1 {

	public static void main(String[] args) throws Throwable {
		// creating Mysql database river
		Driver driver = new Driver();
		
		// registering driver
		DriverManager.registerDriver(driver);
		
		// creating connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
		
		// creating statement 
		Statement statement = connection.createStatement();
		
		// retrieving data
	ResultSet result = statement.executeQuery("select * from student_info;");
	
		while(result.next())
		{
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4));
		}
		connection.close();
	}

}

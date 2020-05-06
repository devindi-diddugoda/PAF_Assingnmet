package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DoctorDB {
	
	
	Connection connection = null; 
	public Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?serverTimezone=UTC","root","");
			
		} catch (ClassNotFoundException e) {
			
			 e.printStackTrace();
			 
		}catch (SQLException e) {	
			
			 e.printStackTrace();
		}
		
	return connection;
		
	}
	
	

}

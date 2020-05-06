package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import util.DoctorDB;

public class Doctor 
{
		DoctorDB db = new DoctorDB();
		Connection connection = db.getConnection();
		 
//		private Connection connect()
//		{
//			Connection con = null;
//			
//			try
//			{
//				Class.forName("com.mysql.jdbc.Driver");
//		    	Provide the correct details: DBServer/DBName, username, password
//				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?serverTimezone=UTC", "root", "");
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			
//			}
//			
//			return con;
//		}
		
public String insertDoctor(String firstname, String lastname, String age, String gender, String email, String phonenumber, String address, String specialization, String password)
{
		String output = "";
			
			try
			{
//				Connection con = connect();
//				
//				if (con == null)
//				{return "Error while connecting to the database for inserting."; }
				
				// create a prepared statement
				
				String query = " insert into doctorDetails (`DOC_id`,`firstname`,`lastname`,`age`,`gender`,`email`,`phonenumber`,`address`,`specialization`,`password`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = connection.prepareStatement(query);
		
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, firstname);
				preparedStmt.setString(3, lastname);
				preparedStmt.setInt(4, Integer.parseInt(age));
				preparedStmt.setString(5, gender);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, phonenumber);
				preparedStmt.setString(8, address);
				preparedStmt.setString(9, specialization);
				preparedStmt.setString(10, password);
				
				// execute the statement
				preparedStmt.execute();
				//con.close();
				
				String newDoctors = readDoctors();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
				System.out.println("Insert Model success Output  ::" + output);
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the doctor details.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
}
		
public String readDoctors()
{
		String output = "";
				
			try
			{
				
//					Connection con = connect();
//					if (con == null)
//					{return "Error while connecting to the database for reading."; }
				
					// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>First Name</th><th>Last Name</th><th>Age</th><th>Gender</th><th>Email</th><th>Phone Number</th><th>Address</th><th>Specialization</th><th>Password</th><th>Update</th><th>Remove</th></tr>";
				
					String query = "select * from doctorDetails";
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(query);
				
				
					// iterate through the rows in the result set
					while (rs.next())
					{
						String DOC_id = Integer.toString(rs.getInt("DOC_id"));
						String firstname = rs.getString("firstname");
						String lastname = rs.getString("lastname");
						String age = Integer.toString(rs.getInt("age"));
						String gender = rs.getString("gender");
						String email = rs.getString("email");
						String phonenumber = rs.getString("phonenumber");
						String address = rs.getString("address");
						String specialization = rs.getString("specialization");
						String password = rs.getString("password");
				
						// Add into the html table
						output += "<tr><td><input id='hidDOC_idUpdate' name='hidDOC_idUpdate' type='hidden' value='" + DOC_id + "'>" 
							   + firstname + "</td>"; 
						output += "<td>" + lastname + "</td>";
						output += "<td>" + age + "</td>";
						output += "<td>" + gender + "</td>";
						output += "<td>" + email + "</td>";
						output += "<td>" + phonenumber + "</td>";
						output += "<td>" + address + "</td>";
						output += "<td>" + specialization + "</td>";
						output += "<td>" + password + "</td>";
						
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-DOC_id='" + DOC_id + "'>" + "</td></tr>";
					}
					
					//con.close();
				
					// Complete the html table
					output += "</table>";
			
			}
			catch (Exception e)
			{
				output = "Error while reading the doctor details.";
				System.err.println(e.getMessage());
			}
				
			return output;
}
		
public String updateDoctor(String DOC_id, String firstname, String lastname, String age, String gender, String email, String phonenumber, String address, String specialization, String password)
{
	String output = "";
			
			try
			{
				/*
				 * Connection con = connect();
				 * 
				 * if (con == null) {return
				 * "Error while connecting to the database for updating."; }
				 */
		
				// create a prepared statement
				String query = "UPDATE doctorDetails SET firstname=?,lastname=?,age=?,gender=?,email=?,phonenumber=?,address=?,specialization=?,password=? WHERE DOC_id=?";
		
				PreparedStatement preparedStmt = connection.prepareStatement(query);
		
				// binding values
				preparedStmt.setString(1, firstname);
				preparedStmt.setString(2, lastname);
				preparedStmt.setInt(3, Integer.parseInt(age));
				preparedStmt.setString(4, gender);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, phonenumber);
				preparedStmt.setString(7, address);
				preparedStmt.setString(8, specialization);
				preparedStmt.setString(9, password);
				preparedStmt.setInt(10, Integer.parseInt(DOC_id));
		
				// execute the statement
				preparedStmt.execute();
				//con.close();
				
				String newDoctors = readDoctors();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
				//System.out.println("Insert Model success Output  ::" + output);

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the doctor details.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
}

public String deleteDoctor(String DOC_id)
{
	String output = "";
		
	try
	{
				/*
				 * Connection con = connect();
				 * 
				 * if (con == null) {return
				 * "Error while connecting to the database for deleting."; }
				 */
		
				// create a prepared statement
				String query = "delete from doctorDetails where DOC_id=?";
		
				PreparedStatement preparedStmt = connection.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(DOC_id));
		
				// execute the statement
				preparedStmt.execute();
				//con.close();
				
				String newDoctors = readDoctors();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the doctor details.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}



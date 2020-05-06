package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import util.DoctorDB;

public class Appointment 
{
			 DoctorDB db = new DoctorDB();
			 Connection connection = db.getConnection();
			 
			
//			private Connection connect()
//			{
//				Connection con = null;
//				
//				try
//				{
//					Class.forName("com.mysql.jdbc.Driver");
//			    Provide the correct details: DBServer/DBName, username, password
//					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?serverTimezone=UTC", "root", "");
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				
//				}
//				
//				return con;
//			}
			
			public String insertAppointment(String Date, String Time, String pat_id, String hospital_id, String DOC_id)
			{
				String output = "";
				
				try
				{
//					Connection con = connect();
//					
//					if (con == null)
//					{return "Error while connecting to the database for inserting."; }
					
					// create a prepared statement
					String query = " insert into appointmentDetails (`appid`,`Date`,`Time`,`pat_id`,`hospital_id`,`DOC_id`)" + " values (?, ?, ?, ?, ?, ?)";
					
					
					PreparedStatement preparedStmt = connection.prepareStatement(query);
			
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, Date);
					preparedStmt.setString(3, Time);
					preparedStmt.setInt(4, Integer.parseInt(pat_id));
					preparedStmt.setInt(5, Integer.parseInt(hospital_id));
					preparedStmt.setInt(6, Integer.parseInt(DOC_id));
					
					// execute the statement
					preparedStmt.execute();
					//con.close();
					
					String newAppointments = readAppointments();
					output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the appointment.\"}";
					System.err.println(e.getMessage());
				}
				
				return output;
			}
			
public String readAppointments()
{
		String output = "";
					
		try
		{
//						Connection con = connect();
//						if (con == null)
//						{return "Error while connecting to the database for reading."; }
					
						// Prepare the html table to be displayed
						output = "<table border=\"1\"><tr><th>Date</th><th>Time</th><th>Patient ID</th><th>Patient First Name</th><th>Patient Last Name</th><th>Hospital ID</th><th>Hospital Name</th><th>Doctor ID</th><th>First Name</th><th>Last Name</th><th>Update</th><th>Remove</th></tr>";
					
						
						String query = "select appid, Date, Time, `healthcaredb`.`appointmentDetails`.pat_id, `healthcaredb`.`patient`.FirstName, `healthcaredb`.`patient`.LastName, `healthcaredb`.`appointmentDetails`.hospital_id, `healthcaredb`.`hospitalDetails`.hospital_name, `healthcaredb`.`appointmentDetails`.DOC_id, `healthcaredb`.`doctorDetails`.firstname, `healthcaredb`.`doctorDetails`.lastname from (`healthcaredb`.`appointmentDetails` left join `healthcaredb`.`doctorDetails` on `healthcaredb`.`appointmentDetails`.DOC_id=`healthcaredb`.`doctorDetails`.DOC_id) left join `healthcaredb`.`patient` on `healthcaredb`.`appointmentDetails`.pat_id=`healthcaredb`.`patient`.pat_id left join `healthcaredb`.`hospitalDetails` on `healthcaredb`.`appointmentDetails`.hospital_id=`healthcaredb`.`hospitalDetails`.hospital_id";
						//String query1 = "SELECT `healthcaredb`.`patient`.FIRSTNAME, `healthcaredb`.`patient`.LASTNAME FROM patient p INNER JOIN `healthcaredb`.`appointmentDetails` ON `healthcaredb`.`patient`.pat_id=`healthcaredb`.`appointmentDetails`.appid";
						Statement stmt = connection.createStatement();
						ResultSet rs = stmt.executeQuery(query);
					
					
						// iterate through the rows in the result set
						while (rs.next())
						{
							String appid = Integer.toString(rs.getInt("appid"));
							String Date = rs.getString("Date");
							String Time = rs.getString("Time");
							String pat_id = Integer.toString(rs.getInt("pat_id"));
							String FirstName = rs.getString("patient.FirstName");
							String LastName = rs.getString("patient.LastName");
							String hospital_id = Integer.toString(rs.getInt("hospital_id"));
							String hospital_name = rs.getString("hospital_name");
							String DOC_id = Integer.toString(rs.getInt("DOC_id"));
							String firstname = rs.getString("doctorDetails.firstname");
							String lastname = rs.getString("doctorDetails.lastname");
					
							// Add into the html table
							output += "<tr><td><input id='hidappidUpdate' name='hidappidUpdate' type='hidden' value='" + appid + "'>" 
									   + Date + "</td>"; 
							output += "<td>" + Time + "</td>";
							output += "<td>" + pat_id + "</td>";
							output += "<td>" + FirstName + "</td>";
							output += "<td>" + LastName + "</td>";
							output += "<td>" + hospital_id + "</td>";
							output += "<td>" + hospital_name + "</td>";
							output += "<td>" + DOC_id + "</td>";
							output += "<td>" + firstname + "</td>";
							output += "<td>" + lastname + "</td>";
							
							// buttons
							output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
									+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-appid='" + appid + "'>" + "</td></tr>";
				}
					
				//	con.close();
					
					// Complete the html table
					output += "</table>";
					
				}
				catch (Exception e)
				{
					output = "Error while reading the appointment details.";
					System.err.println(e.getMessage());
				}
					
					return output;
		}
			
public String updateAppointment(String appid, String Date, String Time, String pat_id, String hospital_id, String DOC_id)
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
					String query = "UPDATE appointmentDetails SET Date=?,Time=?,pat_id=?,hospital_id=?,DOC_id=? WHERE appid=?";
			
					PreparedStatement preparedStmt = connection.prepareStatement(query);
			
					// binding values
					preparedStmt.setString(1, Date);
					preparedStmt.setString(2, Time);
					preparedStmt.setInt(3, Integer.parseInt(pat_id));
					preparedStmt.setInt(4, Integer.parseInt(hospital_id));
					preparedStmt.setInt(5, Integer.parseInt(DOC_id));
					preparedStmt.setInt(6, Integer.parseInt(appid));
					
					
					// execute the statement
					preparedStmt.execute();
					//con.close();
			
					String newAppointments = readAppointments();
					output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the appointment details.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}

public String deleteAppointment(String appid)
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
					String query = "delete from appointmentDetails where appid=?";
			
					PreparedStatement preparedStmt = connection.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(appid));
			
					// execute the statement
					preparedStmt.execute();
					//con.close();
			
					String newAppointments = readAppointments();
					output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the appointment details.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
			
			}

			
		}






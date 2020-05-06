package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import util.DoctorDB;

public class Report 
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
			
			public String insertReport(String hospital_id, String appid, String pat_id, String DOC_id, String Date, String Time, String Description)
			{
				String output = "";
				
				try
				{
//					Connection con = connect();
//					
//					if (con == null)
//					{return "Error while connecting to the database for inserting."; }
					
					// create a prepared statement
					String query = " insert into reportDetails (`REP_id`,`hospital_id`,`appid`,`pat_id`,`DOC_id`,`Date`,`Time`,`Description`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
					
					
					PreparedStatement preparedStmt = connection.prepareStatement(query);
			
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setInt(2, Integer.parseInt(hospital_id));
					preparedStmt.setInt(3, Integer.parseInt(appid));
					preparedStmt.setInt(4, Integer.parseInt(pat_id));
					preparedStmt.setInt(5, Integer.parseInt(DOC_id));
					preparedStmt.setString(6, Date);
					preparedStmt.setString(7, Time);
					preparedStmt.setString(8, Description);
					
					// execute the statement
					preparedStmt.execute();
					//con.close();
					
					String newReports = readReports();
					output = "{\"status\":\"success\", \"data\": \"" + newReports + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the report details.\"}";
					System.err.println(e.getMessage());
				}
				
				return output;
			}
			
public String readReports()
{
					String output = "";
					
					try
					{
//						Connection con = connect();
//						if (con == null)
//						{return "Error while connecting to the database for reading."; }
					
						// Prepare the html table to be displayed
						output = "<table border=\"1\"><tr><th>Hospital ID</th><th>Hospital Name</th><th>Appointment ID</th><th>Patient ID</th><th>Patient First Name</th><th>Patient Last Name</th><th>Doctor ID</th><th>First Name</th><th>Last Name</th><th>Date</th><th>Time</th><th>Description</th><th>Update</th><th>Remove</th></tr>";
					
						
						String query= "select REP_id, `healthcaredb`.`reportDetails`.hospital_id, `healthcaredb`.`hospitalDetails`.hospital_name, `healthcaredb`.`reportDetails`.appid, `healthcaredb`.`reportDetails`.pat_id, `healthcaredb`.`patient`.firstname, `healthcaredb`.`patient`.lastname, `healthcaredb`.`reportDetails`.DOC_id, `healthcaredb`.`doctorDetails`.firstname, `healthcaredb`.`doctorDetails`.lastname,Date,Time,Description from (`healthcaredb`.`reportDetails` left join `healthcaredb`.`doctorDetails` on `healthcaredb`.`reportDetails`.DOC_id=`healthcaredb`.`doctorDetails`.DOC_id) left join `healthcaredb`.`patient` on `healthcaredb`.`reportDetails`.pat_id=`healthcaredb`.`patient`.pat_id left join `healthcaredb`.`hospitalDetails` on `healthcaredb`.`reportDetails`.hospital_id=`healthcaredb`.`hospitalDetails`.hospital_id";
						Statement stmt = connection.createStatement();
						ResultSet rs = stmt.executeQuery(query);
					
					
						// iterate through the rows in the result set
						while (rs.next())
						{
							String REP_id = Integer.toString(rs.getInt("REP_id"));
							String hospital_id = Integer.toString(rs.getInt("hospital_id"));
							String hospital_name = rs.getString("hospital_name");
							String appid = Integer.toString(rs.getInt("appid"));
							String pat_id = Integer.toString(rs.getInt("pat_id"));
							String FirstName = rs.getString("patient.FirstName");
							String LastName = rs.getString("patient.LastName");
							String DOC_id = Integer.toString(rs.getInt("DOC_id"));
							String firstname = rs.getString("doctorDetails.firstname");
							String lastname = rs.getString("doctorDetails.lastname");
							String Date = rs.getString("Date");
							String Time = rs.getString("Time");
							String Description = rs.getString("Description");
					
							// Add into the html table
							output += "<tr><td><input id='hidREP_idUpdate' name='hidREP_idUpdate' type='hidden' value='" + REP_id + "'>" 
									   + hospital_id + "</td>"; 
							output += "<td>" + hospital_name + "</td>";
							output += "<td>" + appid + "</td>";
							output += "<td>" + pat_id + "</td>";
							output += "<td>" + FirstName + "</td>";
							output += "<td>" + LastName + "</td>";
							output += "<td>" + DOC_id + "</td>";
							output += "<td>" + firstname + "</td>";
							output += "<td>" + lastname + "</td>";
							output += "<td>" + Date + "</td>";
							output += "<td>" + Time + "</td>";
							output += "<td>" + Description + "</td>";
							
							// buttons
							output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> <td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-REP_id='" + REP_id + "'>" + "</td></tr>";
				}
				//	con.close();
					
					// Complete the html table
					output += "</table>";
					
				}
				catch (Exception e)
				{
					output = "Error while reading the report details.";
					System.err.println(e.getMessage());
				}
					
					return output;
		}
			
public String updateReport(String REP_id, String hospital_id, String appid, String pat_id, String DOC_id, String Date, String Time, String Description)
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
					String query = "UPDATE reportDetails SET hospital_id=?,appid=?,pat_id=?,DOC_id=?,Date=?,Time=?,Description=? WHERE REP_id=?";
			
					PreparedStatement preparedStmt = connection.prepareStatement(query);
			
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(hospital_id));
					preparedStmt.setInt(2, Integer.parseInt(appid));
					preparedStmt.setInt(3, Integer.parseInt(pat_id));
					preparedStmt.setInt(4, Integer.parseInt(DOC_id));
					preparedStmt.setString(5, Date);
					preparedStmt.setString(6, Time);
					preparedStmt.setString(7, Description);
					preparedStmt.setInt(8, Integer.parseInt(REP_id));
					
					
					// execute the statement
					preparedStmt.execute();
					//con.close();
			
					String newReports = readReports();
					output = "{\"status\":\"success\", \"data\": \"" + newReports + "\"}";
					System.out.println("Insert Model success Output  ::" + output);
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the report details.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}

public String deleteReport(String REP_id)
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
					String query = "delete from reportDetails where REP_id=?";
			
					PreparedStatement preparedStmt = connection.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(REP_id));
			
					// execute the statement
					preparedStmt.execute();
					//con.close();
			
					String newReports = readReports();
					output = "{\"status\":\"success\", \"data\": \"" + newReports + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the report details.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
			
			}

			
		}





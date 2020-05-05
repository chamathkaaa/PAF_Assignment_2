package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctors {

	//A common method to connect to the DB 
		private Connection connect() {
			Connection con = null;
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 //Provide the correct details: DBServer/DBName, username, password 
				 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

				//For testing          
				 System.out.print("Successfully connected");
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return con; 
		}
		
		public String readDoctors() {  
			String output = "";  
			
			try {  
				Connection con = connect();  
				if (con == null)  {   
					return "Error while connecting to the database for reading.";  
				} 

				// Prepare the html table to be displayed   
				output = "<table border='1'><tr><th>Doctor Name</th>"
						+ "<th>NIC</th><th>Address</th>"
						+ "<th>Phone</th><th>Email</th>"
						+ "<th>Specialization</th><th>HospitalName</th>"
						+"<th>DepartmentName</th>"
						+ "<th>Update</th><th>Remove</th></tr>";


				  String query = "select * from doctors";   
				  Statement stmt = con.createStatement();   
				  ResultSet rs = stmt.executeQuery(query); 

				  // iterate through the rows in the result set   
				  while (rs.next())   {  

					  String DoctorID = Integer.toString(rs.getInt("DoctorID"));
					  String DoctorName = rs.getString("DoctorName");
					  String NIC = rs.getString("NIC");
					  String Address = rs.getString("Address");
					  String MobileNo = Integer.toString(rs.getInt("MobileNo"));
					  String Email = rs.getString("Email");
					  String Specialization = rs.getString("Specialization");
					  String HospitalName = rs.getString("HospitalName");
					  String DepartmentName = rs.getString("DepartmentName");
					  // Add into the html table    

					  output += "<tr><td><input id='hidDoctorIDUpdate' name='hidDoctorIDUpdate' type='hidden' value='" + DoctorID + "'>" + DoctorName + "</td>"; 

					  output += "<td>" + NIC + "</td>";
					  output += "<td>" + Address + "</td>";    
					  output += "<td>" + MobileNo + "</td>"; 
					  output += "<td>" + Email + "</td>";    
					  output += "<td>" + Specialization + "</td>";
					  output += "<td>" + HospitalName + "</td>";		  
					  output += "<td>" + DepartmentName + "</td>"; 
					  
					// buttons     
					  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-doctorid='"+ DoctorID +"'>"+"</td></tr>";

					} 
				  
				  con.close(); 

				  // Complete the html table   
				  output += "</table>"; 
				}
				catch (Exception e) {  
					output = "Error while reading the Doctor.";  
					System.err.println(e.getMessage()); 
				}

				return output;
			}
		
		//Insert Hospitals
		public String insertDoctors(String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept ) {
			String output = "";

			try {
				Connection con = connect();  

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement   
				String query = " insert into doctors (`DoctorID`,`DoctorName`,`NIC`,`Address`,`MobileNo`, `Email`,`Specialization`,`HospitalName`,`DepartmentName`)"+" values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values 
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, docName);
				preparedStmt.setString(3, nic);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, mobNo);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, spec);
				preparedStmt.setString(8, hosp);
				preparedStmt.setString(9, dept);
				
				//execute the statement   
				preparedStmt.execute();   
				con.close(); 

				//Create JSON Object to show successful msg.
				String newDoctors = readDoctors();
				output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
			}
			catch (Exception e) {  
				//Create JSON Object to show Error msg.
				output = "{\"status\":\"error\", \"data\": \"Error while Inserting Doctor.\"}";   
				System.err.println(e.getMessage());  
			} 

			 return output; 
		}
		
		//Update hospitals
		public String updateDoctors(String ID, String docName, String nic, String address, String mobNo, String email, String spec, String hosp, String dept )  {   
			String output = ""; 
		 
		  try   {   
			  Connection con = connect();
		 
			  if (con == null)    {
				  return "Error while connecting to the database for updating."; 
			  } 
		 
		   // create a prepared statement    
			   String query = "UPDATE doctors SET DoctorName=?,NIC=?,Address=?,MobileNo=?,Email=?,Specialization=?,HospitalName=?,DepartmentName=? WHERE DoctorID=?";
				 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		   // binding values    
		   preparedStmt.setString(1, docName);    
		   preparedStmt.setString(2, nic);    
		   preparedStmt.setString(3, address);
		   preparedStmt.setInt(4, Integer.parseInt(mobNo));
		   preparedStmt.setString(5, email);
		   preparedStmt.setString(6, spec);
		   preparedStmt.setString(7, hosp);
		   preparedStmt.setString(8, dept);
		   preparedStmt.setInt(9, Integer.parseInt(ID));
		   
		 
		   // execute the statement    
		   preparedStmt.execute();    
		   con.close(); 
		 
		   //create JSON object to show successful msg
		   String newDoctors = readDoctors();
		   output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
		   }   catch (Exception e)   {    
			   output = "{\"status\":\"error\", \"data\": \"Error while Updating Doctor Details.\"}";      
			   System.err.println(e.getMessage());   
		   } 
		 
		  return output;  
		  }
		
		public String deleteDoctors(String DoctorID) {  
			String output = ""; 
		 
		 try  {   
			 Connection con = connect();
		 
		  if (con == null)   {    
			  return "Error while connecting to the database for deleting.";   
		  } 
		 
		  // create a prepared statement   
		  String query = "DELETE FROM doctors WHERE DoctorID=?"; 
		 
		  PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		  // binding values   
		  preparedStmt.setInt(1, Integer.parseInt(DoctorID));       
		  // execute the statement   
		  preparedStmt.execute();   
		  con.close(); 
		 
		  //create JSON Object
		  String newDoctors = readDoctors();
		  output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
		  }  catch (Exception e)  {  
			  //Create JSON object 
			  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Doctor.\"}";
			  System.err.println(e.getMessage());  
			  
		 } 
		 
		 return output; 
		 }
}

<%@ page import="com.DoctorsAPI" %>
<%@ page import="model.Doctor" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="Components/doctor.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-6">
		
				<h2>Doctor Details</h2>
			
				<form id="formDoctor" name="formDoctor">
					First Name:
					<input id="firstname" name="firstname" type="text" class="form-control form-control-sm">
					<br> Last Name:
					<input id="lastname" name="lastname" type="text" class="form-control form-control-sm">
					<br> Age:
					<input id="age" name="age" type="text" class="form-control form-control-sm">
					<br> Gender:
					<input id="gender" name="gender" type="text" class="form-control form-control-sm">
					<br> Email:
					<input id="email" name="email" type="text" class="form-control form-control-sm">
					<br> Phone Number:
					<input id="phonenumber" name="phonenumber" type="text" class="form-control form-control-sm">
					<br> Address:
					<input id="address" name="address" type="text" class="form-control form-control-sm">
					<br> Specialization:
					<input id="specialization" name="specialization" type="text" class="form-control form-control-sm">
					<br> Password:
					<input id="password" name="password" type="text" class="form-control form-control-sm">
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidDOC_idSave" name="hidDOC_idSave" value="">
				</form>
		
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divDoctorsGrid">
					<%
					    Doctor doctorObject = new Doctor();
						out.print(doctorObject.readDoctors());
			 		%>
				</div>
				
			</div>
		</div>
	</div>

</body>
</html>


<%@ page import="com.AppointmentsAPI" %>
<%@ page import="model.Appointment" %>
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
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/appointment.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-6">

				<h2>Appointment Details</h2>
			
				<form id="formAppointment" name="formAppointment">
					Date:
					<input id="Date" name="Date" type="text" class="form-control form-control-sm">
					<br> Time:
					<input id="Time" name="Time" type="text" class="form-control form-control-sm">
					<br> Patient ID:
					<input id="pat_id" name="pat_id" type="text" class="form-control form-control-sm">
					<br> Patient First Name:
					<input id="FirastName" name="FirstName" type="text" class="form-control form-control-sm">
					<br> Patient Last Name:
					<input id="LastName" name="LastName" type="text" class="form-control form-control-sm">
					<br> Hospital ID:
					<input id="hospital_id" name="hospital_id" type="text" class="form-control form-control-sm">
					<br> Hospital Name:
					<input id="hospital_name" name="hospital_name" type="text" class="form-control form-control-sm">
					<br> Doctor ID:
					<input id="DOC_id" name="DOC_id" type="text" class="form-control form-control-sm">
					<br> Doctor First Name:
					<input id="firstname" name="firstname" type="text" class="form-control form-control-sm">
					<br> Doctor Last Name:
					<input id="lastname" name="lastname" type="text" class="form-control form-control-sm">
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidappidSave" name="hidappidSave" value="">
				</form>
		
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="AppointmentsGrid">
					<%
					    Appointment appointmentObject = new Appointment();
						out.print(appointmentObject.readAppointments());
			 		%>
				</div>
							
			</div>
		</div>
	</div>

</body>
</html>


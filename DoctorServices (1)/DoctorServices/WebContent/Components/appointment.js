$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//Save-----------------------------------
$(document).on("click", "#btnSave", function(event)
{
	//Clear Alerts-----------------------

	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form Validation--------------------
	var status = validateformAppointment();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//if Valid---------------------------
	var type = ($("#hidappidSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "AppointmentsAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onAppointmentSaveComplete(response.responseText,status);
		}
	});
});

function onAppointmentSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divAppointmentsGrid").html(resultSet.data);
		}else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	}else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	}else
	{
		$("#alertError").text("Unknown error while saving.");
		$("#alertError").show();
	}
	
	$("#hideappidSave").val("");
	$("#formAppointment")[0].reset();
}

//Update--------------------------------
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hideappidSave").val($(this).closest("tr").find('#hideappidUpdate').val());
	$("#Date").val($(this).closest("tr").find('td:eq(0)').text());
	$("#Time").val($(this).closest("tr").find('td:eq(1)').text());
	$("#pat_id").val($(this).closest("tr").find('td:eq(2)').text());
	$("#hospital_id").val($(this).closest("tr").find('td:eq(3)').text());
	$("#DOC_id").val($(this).closest("tr").find('td:eq(4)').text());
	
});

//Remove-----------------------------------
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "AppointmentsAPI",
		type : "DELETE",
		data : "appid=" + $(this).data("appid"),
		dataType : "text",
		complete : function(response, status)
		{
			onAppointmentDeleteComplete(response.responseText, status);
		}
	});
});

function onAppointmentDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divAppointmentsGrid").html(resultSet.data);
		}else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}else
	{
		$("#alertError").text("Unknown error while deleting.");
		$("#alertError").show();
	}
}

//CLIENT MODEL-------------------------------------

function validateformAppointment() {
	
	//DATE
	if($("#Date").val().trim() == "")
	{
		return "Insert Date.";
	}
	
	//TIME
	if($("#Time").val().trim() == "")
	{
		return "Insert Time.";
	}
	
	//PATIENT ID
	if($("#pat_id").val().trim() == "")
	{
		return "Insert Patient ID.";
	}
	
	//is numerical value
	var tmppat_id = $("#pat_id").val().trim();
	if (!$.isNumeric(tmppat_id))
	{
		return "Insert a numerical value for Patient ID.";
	}
	
	//PATIENT FIRST NAME
	if($("#FirstName").val().trim() == "")
	{
		return "Insert Patient First Name.";
	}
	
	//PATIENT LAST NAME
	if($("#LastName").val().trim() == "")
	{
		return "Insert Patient Last Name.";
	}
	
	//HOSPITAL ID
	if($("#hospital_id").val().trim() == "")
	{
		return "Insert Hospital ID.";
	}
	
	//is numerical value
	var tmphospital_id = $("#hospital_id").val().trim();
	if (!$.isNumeric(tmphospital_id))
	{
		return "Insert a numerical value for Patient ID.";
	}
	
	//HOSPITAL NAME
	if($("#hospital_name").val().trim() == "")
	{
		return "Insert Hospital Name.";
	}
	
	//DOCTOR ID
	if($("#DOC_id").val().trim() == "")
	{
		return "Insert Doctor ID.";
	}
	
	//is numerical value
	var tmpDOC_id = $("#DOC_id").val().trim();
	if(!$.isNumeric(tmpDOC_id))
	{
		return "Insert a numerical value for Doctor ID.";
	}
	
	//DOCTOR FIRST NAME
	if($("#firstname").val().trim() == "")
	{
		return "Insert Doctor First Name.";
	}
	
	//DOCTOR LAST NAME
	if($("#lastname").val().trim() == "")
	{
		return "Insert Doctor Last Name.";
	}
	
	return true;
	
}





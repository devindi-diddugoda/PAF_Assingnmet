$(document).ready(function()
{
	$("#alertSuccess").hide();
	
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
	var status = validateformReport();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//if Valid---------------------------
	var type = ($("#hidREP_idSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ReportsAPI",
		type : type,
		data : $("#formReport").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onReportSaveComplete(response.responseText,status);
		}
	});
});

function onReportSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divReportsGrid").html(resultSet.data);
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
	
	$("#hideREP_idSave").val("");
	$("#formReport")[0].reset();
}

//Update--------------------------------
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hideREP_idSave").val($(this).closest("tr").find('#hideREP_idUpdate').val());
	$("#hospital_id").val($(this).closest("tr").find('td:eq(0)').text());
	$("#appid").val($(this).closest("tr").find('td:eq(1)').text());
	$("#pat_id").val($(this).closest("tr").find('td:eq(2)').text());
	$("#DOC_id").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Date").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Time").val($(this).closest("tr").find('td:eq(5)').text());
	$("#Description").val($(this).closest("tr").find('td:eq(6)').text());
	
});

//Remove-----------------------------------
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "ReportsAPI",
		type : "DELETE",
		data : "REP_id=" + $(this).data("REP_id"),
		dataType : "text",
		complete : function(response, status)
		{
			onReportDeleteComplete(response.responseText, status);
		}
	});
});

function onReportDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divReportsGrid").html(resultSet.data);
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

function validateformReport() {
	
	//HOSPITAL ID
	if($("#hospital_id").val().trim() == "")
	{
		return "Insert Hospital ID.";
	}
	
	//is numerical value
	var tmphospital_id = $("#hospital_id").val().trim();
	if (!$.isNumeric(tmphospital_id))
	{
		return "Insert a numerical value for Hospital ID.";
	}
	
	//HOSPITAL NAME
	if($("#hospital_name").val().trim() == "")
	{
		return "Insert Hospital Name.";
	}
	
	//APPOINTMENT ID
	if($("#appid").val().trim() == "")
	{
		return "Insert Appointment ID.";
	}
	
	//is numerical value
	var tmpappid = $("#appid").val().trim();
	if (!$.isNumeric(tmpappid))
	{
		return "Insert a numerical value for Appointment ID.";
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
	
	//DESCRIPTION
	if($("#Description").val().trim() == "")
	{
		return "Insert Description.";
	}
	
	return true;
	
}





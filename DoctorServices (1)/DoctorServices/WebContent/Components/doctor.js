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
	var status = validateformDoctor();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//if Valid---------------------------
	var type = ($("#hidDOC_idSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : 'DoctorsAPI',
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onDoctorSaveComplete(response.responseText, status);
		}
	});
});

function onDoctorSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divDoctorsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hideDOC_idSave").val("");
	$("#formDoctor")[0].reset();
}

//Update--------------------------------

$(document).on("click", ".btnUpdate", function(event)
{
	$("#hideDOC_idSave").val($(this).closest("tr").find('#hideDOC_idUpdate').val());
	$("#firstname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#lastname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#age").val($(this).closest("tr").find('td:eq(2)').text());
	$("#gender").val($(this).closest("tr").find('td:eq(3)').text());
	$("#email").val($(this).closest("tr").find('td:eq(4)').text());
	$("#phonenumber").val($(this).closest("tr").find('td:eq(5)').text());
	$("#address").val($(this).closest("tr").find('td:eq(6)').text());
	$("#specialization").val($(this).closest("tr").find('td:eq(7)').text());
	$("#password").val($(this).closest("tr").find('td:eq(8)').text());
});

//Remove-----------------------------------

$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "DoctorsAPI",
		type : "DELETE",
		data : "DOC_id=" + $(this).data("DOC_id"),
		dataType : "text",
		complete : function(response, status)
		{
			onDoctorDeleteComplete(response.responseText, status);
		}
	});
});

function onDoctorDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divDoctorsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting.");
		$("#alertError").show();
	}
}

//CLIENT MODEL-------------------------------------

function validateformDoctor() {
	
	//FIRST NAME
	if($("#firstname").val().trim() == "")
	{
		return "Insert First Name.";
	}
	
	//LAST NAME
	if($("#lastname").val().trim() == "")
	{
		return "Insert Last Name.";
	}
	
	//AGE
	if($("#age").val().trim() == "")
	{
		return "Insert Age.";
	}
	
	//is numerical value
	var tmpage = $("#age").val().trim();
	if (!$.isNumeric(tmpage))
	{
		return "Insert a numerical value for Age.";
	}
	
	//GENDER
	if($("#gender").val().trim() == "")
	{
		return "Insert Gender.";
	}
	
	//EMAIL
	if($("#email").val().trim() == "")
	{
		return "Insert a valid Email Address.";
	}
	
	//Checking if a valid email Address
	var tmpemail = $("#email").val().trim();
	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
	if (! re.test(tmpemail)){
		return "Insert a valid Email Address";
	}
	
	//PHONE NUMBER
	if($("#phonenumber").val().trim() == "")
	{
		return "Insert Phone Number.";
	}
	
	//is numerical value
	var tmpphonenumber = $("#phonenumber").val().trim();
	if(!$.isNumeric(tmpphonenumber))
	{
		return "Phone Number can't contain Letters.";
	}
	else if ($("#phonenumber").val().length !== 10) 
	{
		return "Phone Number must have 10 digits.";
	}
	
	//ADDRESS
	if($("#address").val().trim() == "")
	{
		return "Insert Address.";
	}
	
	//SPECIALIZATION
	if($("#specialization").val().trim() == "")
	{
		return "Insert your Specialization.";
	}

	//PASSWORD
	if($("#password").val().trim() == "")
	{
		return "Insert Password.";
	}

	return true;
	
}



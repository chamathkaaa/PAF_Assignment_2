$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidDoctorIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "DoctorAPI",
		type : t,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalSaveComplete(response.responseText, status);
		}
	});
}); 

function onHospitalSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
					
			$("#divDoctorsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while saving.");
		$("#alertError").show();
	}
	$("#hidDoctorIDSave").val("");
	$("#formDoctor")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
			$("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());     
			$("#doctorName").val($(this).closest("tr").find('td:eq(0)').text());     
			$("#nic").val($(this).closest("tr").find('td:eq(1)').text());     
			$("#address").val($(this).closest("tr").find('td:eq(2)').text());     
			$("#mobile").val($(this).closest("tr").find('td:eq(3)').text());
			$("#email").val($(this).closest("tr").find('td:eq(4)').text());     
			$("#spec").val($(this).closest("tr").find('td:eq(5)').text());     
			$("#hospitalName").val($(this).closest("tr").find('td:eq(6)').text());
			$("#deptName").val($(this).closest("tr").find('td:eq(7)').text());

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "DoctorAPI",
		type : "DELETE",
		data : "DoctorID=" + $(this).data("doctorid"),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalDeletedComplete(response.responseText, status);
		}
	});
});

function onHospitalDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divDoctorsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error while deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateHospitalForm() {  
	// Name  
	if ($("#doctorName").val().trim() == "")  
	{   
		return "Insert Name.";  
	} 

	// Address  
	if ($("#nic").val().trim() == "")  
	{  
		return "Insert NIC.";  
	}
	
	// City  
	if ($("#address").val().trim() == "")  
	{   
		return "Insert Address.";  
	} 

	// Phone
	if ($("#mobile").val().trim() == "")  
	{  
		return "Insert Phone number .";
	}
	
	//is Numerical value
	var phoneNum = $("#mobile").val().trim();  
	if (!$.isNumeric(phoneNum))  {   
		return "Insert valied phone number.";  
	} 
	
	// Email  
	if ($("#email").val().trim() == "")  
	{   
		return "Insert Email Address.";  
	} 
	
	// Description  
	if ($("#spec").val().trim() == "")  
	{  
		return "Insert Spec.";  
	}
	
	// Open Hours  
	if ($("#hospitalName").val().trim() == "")  
	{  
		return "Insert Hospital Name.";  
	}
	
	// Open Hours  
	if ($("#deptName").val().trim() == "")  
	{  
		return "Insert Department Name.";  
	}
	
	
	return true;
}

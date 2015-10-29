$(document).ready(function() {
	$("#tabs").tabs();
	
	$.ajax({
		   url:'api/movies',
		   type: 'GET',
		   dataType: 'json'
		}).fail(function(jqXHR, textStatus) {
			jsonValue = jQuery.parseJSON( jqXHR.responseText );
			alert(jsonValue.Message);
		}).done(function(data,status,xhr) {
			var token = localStorage.getItem("Token")
			console.log("[Statuscode] - " + xhr.status);
			console.log("[Token] - " + token);
			if(xhr.status == 200) {
				if(token == null) {
					console.log("[Token false] - enter");
					$("#movies").append("<div class='alert alert-danger' role='alert'><strong>Error </strong>You are not logged in.</div>");
				} else {
					console.log("[Token true] - enter");
					$.each(data, function(index, element) {
						$('#movieRateSelect')
				         .append($("<option></option>")
				         .attr("value",element.imdbTT)
				         .text(element.title));
			        });
				}
			} 
	});
});
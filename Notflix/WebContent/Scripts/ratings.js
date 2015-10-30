$(document).ready(function() {
	$("#tabs").tabs();
	
	var map = new Object();
	
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
					$("#ratings").append("<div class='alert alert-danger' role='alert'><strong>Error </strong>You are not logged in.</div>");
				} else {
					console.log("[Token true] - enter");
					$.each(data, function(index, element) {
						map[element.imdbTT] = element.title;
						$('#movieRateSelect')
				         .append($("<option></option>")
				         .attr("value",element.imdbTT)
				         .text(element.title));
			        });
				}
			} 
	});
	
	$.ajax({
		url: 'api/ratings',
		type: 'GET',
		dataType: 'json',
		beforeSend: function (request)
	    {
	        request.setRequestHeader("usertoken", localStorage.getItem("Token"));
	    }
		}).fail(function(jqXHR, textStatus) {
			alert("Failed");
		}).done(function(data, status, xhr) {
			$.each(data, function(index, element) {
				console.log(element.imdbtt);
				var title = map[element.imdbtt];
				$('#movieRateUpdate')
		         .append($("<option></option>")
		         .attr("value",element.imdbtt)
		         .text(title));
	        });
		});
});
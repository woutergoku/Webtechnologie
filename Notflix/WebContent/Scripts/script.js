$(document).ready(function() {
	console.log("[ready] - enter");
});

function login() { 
	console.log("[login] - enter");
	$.ajax({
	   url:'api/users/login',
	   type: 'POST',
	   data: $("#loginForm").serialize()
	}).fail(function(jqXHR, textStatus) {
		jsonValue = jQuery.parseJSON( jqXHR.responseText );
		alert(jsonValue.Message);
	}).done(function(data,status,xhr) {
		alert("Token: " + xhr.getResponseHeader('Authorization'));
	});
	
	return false;
}

function registreer() {
	console.log("[registreer] - enter");
	$.ajax({
	   url:'api/users/add',
	   type: 'POST',
	   data: $("#registreerForm").serialize(),
	   dataType: 'json'
	}).fail(function(jqXHR, textStatus) {
		jsonValue = jQuery.parseJSON( jqXHR.responseText );
		alert(jsonValue.Message);
	}).done(function(data,status,xhr) {
		$.each(data, function(index, element) {
            if(index === "nickName") {
            	alert("Toegevoegde Gebruiker: " + element)
            }
        });
	});
	
	return false;
}

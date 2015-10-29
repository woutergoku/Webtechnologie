$(document).ready(function() {
	console.log("[ready] - enter");
	
	if(typeof(Storage) !== "undefined") {
		var token = localStorage.getItem("Token");
		if(token != null) {
			console.log("[Storage True] - enter");
			$("#form").append("<button id='btnLogout' type='button' class='btn btn-danger'>Log Out</button><p id='welcomeMessage'>Welkom</p>");
		} else {
			console.log("[Storage False] - enter");
			$("#form").append("<form class='navbar-form navbar-right' id='loginForm' onsubmit='login(); return false;' role='login' method='post'><div class='form-group'><input type='text' class='form-control' name='nickname' placeholder='Nickname'></div><div class='form-group'><input type='password' class='form-control' name='password' placeholder='Password'></div><button type='submit' id='btnLogin' class='btn btn-success'>Login</button></form><a id='registreer' href='registreer.html'>registreer</a>");
		}
	} else {
	}
	
	$("#btnAddRate").click(function() {
		console.log("[btnRate Click] - enter");
		addRate();
	});
	
	$("#btnLogout").click(function() {
		localStorage.removeItem("Token");
		location.reload();
	});
});

function login() { 
	console.log("[login] - enter");
	$.ajax({
		url:'api/users/login',
		type: 'POST',
		data: $("#loginForm").serialize()
	}).fail(function(jqXHR, textStatus) {
		alert("Failed");
	}).done(function(data,status,xhr) {
		alert("done");
		if(typeof(Storage) !== "undefined") {
		    localStorage.setItem("Token", xhr.getResponseHeader('Authorization'));
		} else {
		}
	});
	
	//location.reload();
	
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
		alert("Failed");
	}).done(function(data,status,xhr) {
		$.each(data, function(index, element) {
            if(index === "nickName") {
            	alert("Toegevoegde Gebruiker: " + element)
            }
        });
	});
	
	return false;
}

function addRate() {
	console.log("[addRate] - enter");
	
	alert($("#movieRateSelect").val());
	
	var rateData = "movieid="+ $("#movieRateSelect").val() + "&rating=" + $("#movieRating").val();
	
	$.ajax({
	   url:'api/ratings/add',
	   beforeSend: function (request)
       {
           request.setRequestHeader("usertoken", localStorage.getItem("Token"));
       },
	   type: 'POST',
	   data: rateData,
	   dataType: 'json',
	}).fail(function(jqXHR, textStatus) {
		alert("fail");
	}).done(function(data,status,xhr) {
		$.each(data, function(index, element) {
            alert("Added the rate " + element + "to movie " + element + "as user " + element);
        });
	});
}

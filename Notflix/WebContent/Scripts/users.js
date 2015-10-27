$(document).ready(function () {
	$.ajax({
		   url:'api/users',
		   type: 'GET',
		   dataType: 'json'
		}).fail(function(jqXHR, textStatus) {
			jsonValue = jQuery.parseJSON( jqXHR.responseText );
			alert(jsonValue.Message);
		}).done(function(data,status,xhr) {
			$.each(data, function(index, element) {
				loadUserHtml(element.firstName, element.insertion, element.lastName, element.nickName);
			});
		});
});

function loadUserHtml(firstname, insertion, lastname, nickname){
	var html = "<div class='userArticle'><div class='userInfo'><table><tr><td>Firstname</td><td>" + firstname + "</td></tr><tr><td>Insertion</td><td>" + insertion + "</td></tr><tr><td>Lastname</td><td>" + lastname + "</td></tr><tr><td>Nickname</td><td>" + nickname + "</td></tr></table></div></div>"
	
	$("#users").append(html);
}
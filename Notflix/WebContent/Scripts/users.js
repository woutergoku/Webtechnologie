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
	
	$("#users .userArticle").each(function(index, elem) {
	    elem.click( function() {
	    	alert(this.val());
	    });
	});
});

function loadUserHtml(nickname){
	var html = "<p id='nicknameParagraph'>Nickname: " + nickname + "</p>"
	
	$("#users").append(html);
}
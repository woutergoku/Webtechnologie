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
				loadUserHtml(element.nickName, element.firstName, element.lastName, element.insertion);
			});
	});
	
	$("#accordion").accordion({
		active: false,
		collapsible: true
    });
});

function loadUserHtml(nickname, firstname, lastname, insertion){
	var html = "<h3 id='headerAcc'>" + nickname + "</h3><div><table><tr><td>Firstname: </td><td>"+ firstname +"</td></tr><tr><td>Insertion: </td><td>"+ insertion +"</td></tr><tr><td>Lastname: </td><td>"+ lastname +"</td></tr></table></div>";
	
	$("#accordion").append(html);
	$("#accordion").accordion("refresh");
}
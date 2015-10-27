$(document).ready(function () {
	$.ajax({
		   url:'api/movies',
		   type: 'GET',
		   dataType: 'json'
		}).fail(function(jqXHR, textStatus) {
			jsonValue = jQuery.parseJSON( jqXHR.responseText );
			alert(jsonValue.Message);
		}).done(function(data,status,xhr) {
			$.each(data, function(index, element) {
	            loadMovieHtml(element.imdbTT, element.title, element.dateOfPublication, element.lengthInMinutes, element.director, element.description)
	        });
		});
});


function loadMovieHtml(imdbTT, title, dateOfPublication, length, director, description) {
	var html = "<div class='movieArticle'> <img class='poster' src='Images/the-intern-.jpg' alt='poster' /><div class='vertical-line' /><div class='movieInfo'><table><tr><td>ImdbTT</td><td>" + imdbTT + "</td></tr><tr><td>Title</td><td>" + title + "</td></tr><tr><td>Date</td><td>" + dateOfPublication + "</td></tr><tr><td>Length</td><td>" + length + "</td></tr><tr><td>Director</td><td>" + director + "</td></tr><tr><td>Description</td><td>" + description + "</td></tr></table></div></div>";
	
	$("#movies").append(html);
}
$(document).ready(function () {
	$.ajax({
		   url:'api/ratings/movies',
		   beforeSend: function (request)
           {
               request.setRequestHeader("usertoken", localStorage.getItem("Token"));
           },
		   type: 'GET',
		   dataType: 'json'
		}).fail(function(jqXHR, textStatus) {
			jsonValue = jQuery.parseJSON( jqXHR.responseText );
			alert(jsonValue.Message);
		}).done(function(data,status,xhr) {
			if(xhr.status == 200) {
				$.each(data, function(index, element) {
		            loadMovieHtml(element.movie.imdbTT, element.movie.title, element.movie.dateOfPublication, element.movie.lengthInMinutes, element.movie.director, element.movie.description, element.averageRating);
		        });
			} else if(xhr.status == 400) {
				$("#movies").append("<p>U bent niet ingelogd</p>");
			}
			
		});
});


function loadMovieHtml(imdbTT, title, dateOfPublication, length, director, description, rating) {
	var html = "<div class='movieArticle'> <img class='poster' src='Images/the-intern-.jpg' alt='poster' /><div class='vertical-line' /><div class='movieInfo'><table><tr><td>ImdbTT</td><td>" + imdbTT + "</td></tr><tr><td>Title</td><td>" + title + "</td></tr><tr><td>Date</td><td>" + dateOfPublication + "</td></tr><tr><td>Length</td><td>" + length + "</td></tr><tr><td>Director</td><td>" + director + "</td></tr><tr><td>Description</td><td>" + description + "</td></tr><tr><td>Rating:</td><td>" + rating + "</td></tr></table></div></div>";
	
	$("#movies").append(html);
}
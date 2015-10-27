package resources;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import model.Model;
import model.Movie;

@Path("/movies")
public class MovieResource {
	
	@Context ServletContext context;
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<Movie> getMovies(@QueryParam("title") String title) {
		
		Model model = (Model) context.getAttribute("model");
		if(title == null) {
			return model.getMovies();
		}
		else {
			return model.getMovieByTitle(title);
		}
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Movie getMovieByIMDB(@PathParam("id") int id) {
		Model model = (Model) context.getAttribute("model");
		return model.getMovieById(id);
	}
}

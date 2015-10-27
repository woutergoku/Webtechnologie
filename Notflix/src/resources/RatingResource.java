package resources;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.MovieRate;
import model.Rating;

@Path("/ratings")
public class RatingResource {

	@Context ServletContext context;
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addRating(@FormParam("movieid") int movieid, @FormParam("rating") double rating, @HeaderParam("usertoken") String token) {
		Model model = (Model) context.getAttribute("model");
		
		if(movieid >= 1 && rating >= 0.5 && rating <= 5 && token != null) {
			Rating newRating = new Rating(model.getUsernameFromToken(token), movieid, rating);
			model.addRating(newRating);
			
			return Response.ok().entity(newRating).build();
		}
		
		return Response.status(400).build();
	}
	
	@PUT
	@Path("/update")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateRating(@FormParam("movieid") int movieid, @FormParam("rating") double rating, @HeaderParam("usertoken") String token) {
		Model model = (Model) context.getAttribute("model");
		
		if(movieid >= 1 && rating >= 0.5 && rating <= 5 && token != null) {
			Rating updatedRating = model.updateRating(rating, movieid, token);
			
			return Response.ok().entity(updatedRating).build();
		}
		
		return Response.status(400).build();
	}
	
	@DELETE
	@Path("/delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response deleteRating(@QueryParam("movieid") int movieid, @HeaderParam("usertoken") String token) {
		Model model = (Model) context.getAttribute("model");
		
		if(movieid >= 1 && token != null) {
			model.deleteRating(movieid, token);
			
			return Response.ok().build();
		}
		
		return Response.status(400).build();
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<Rating> getRatings(@HeaderParam("usertoken") String token) {
		Model model = (Model) context.getAttribute("model");
		
		if(token != null) {
			return model.getRatings(token);
		}
		
		return null;
	}
	
	@GET
	@Path("/movies")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<MovieRate> getMoviesWithRating(@HeaderParam("usertoken") String token) {
		
		Model model = (Model) context.getAttribute("model");
		if(token != null) {
			return model.getMovieRates();
		}
		return null;
	}
}

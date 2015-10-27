package resources;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.User;

@Path("/users")
public class UserResource {

	@Context ServletContext context;
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response insertUser(@FormParam("firstname") String firstname, @FormParam("insertion") String insertion, @FormParam("lastname") String lastname, @FormParam("nickname") String nickname, @FormParam("password") String password) {
		Model model = (Model) context.getAttribute("model");
		
		if(firstname.length() > 0 && lastname.length() > 0 && nickname.length() > 0 && password.length() > 0) {
			User user = new User(firstname, insertion, lastname, nickname, password);
			model.addUser(user);
			return Response.ok().entity(user).build();
		}
		
		return Response.status(400).build();
	}
	
	@GET
	@Path("{nickname}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User getUserByNickname(@PathParam("nickname") String nickname) {
		
		Model model = (Model) context.getAttribute("model");
		return model.getUserByNickname(nickname);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ArrayList<User> getUsers() {
		Model model = (Model) context.getAttribute("model");
		return model.getUsers();
	}
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Response loginUser(@FormParam("nickname") String username, @FormParam("password") String password) {
		Model model = (Model) context.getAttribute("model");
		
		if(username.length() > 0 && password.length() > 0) {
			String token = model.loginUser(username, password);
			return Response.ok().header("Authorization", token).build();
		}

		return Response.status(400).build();
	}
	
}

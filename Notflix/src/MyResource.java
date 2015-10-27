import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class MyResource extends ResourceConfig {

	public MyResource() {
		super();
		packages("resources");
		register(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
	}
}

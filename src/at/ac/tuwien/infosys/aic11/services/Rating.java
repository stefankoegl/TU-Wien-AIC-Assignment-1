package at.ac.tuwien.infosys.aic11.services;

//import at.ac.tuwien.infosys.aicc11.Customer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import at.ac.tuwien.infosys.aic11.dto.Ratings;

@Path("/rating/")
@Produces("application/json")
public interface Rating {
	
    	@Produces("application/json")
    	@GET
    	@Path("{id}")
	Ratings getRating(@PathParam("id") long customerId);

}

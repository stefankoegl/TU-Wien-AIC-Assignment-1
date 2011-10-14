package at.ac.tuwien.infosys.aicc11.services;

//import at.ac.tuwien.infosys.aicc11.Customer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

import at.ac.tuwien.infosys.aicc11.Ratings;

@Path("/rating/")
@Produces("application/json")
public class RatingService implements Rating {

    	@Produces("application/json")
	@Override
	@GET
    	@Path("{id}")
	public Ratings getRating(@PathParam("id") long customerId) {
		// TODO just some test value
		if (customerId==0)
		    return Ratings.A;
		if (customerId==23)
		    return Ratings.AAA;
		else
		    return Ratings.AAMinus;
	}

}

package at.ac.tuwien.infosys.aicc11.services;

//import at.ac.tuwien.infosys.aicc11.Customer;

import at.ac.tuwien.infosys.aicc11.Ratings;

public class RatingService implements Rating {

	public Ratings getRating(long customerId) {
		// TODO just some test value
		if (customerId==0)
		    return Ratings.A;
		if (customerId==23)
		    return Ratings.AAA;
		else
		    return Ratings.AAMinus;
	}

}

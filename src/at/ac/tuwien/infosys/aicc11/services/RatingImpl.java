package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.Ratings;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyRating;

public class RatingImpl implements Rating 
{
	LegacyRating backend = new LegacyRating();
	
	public Ratings getRating(long customerId) 
	{
		return backend.getRating(customerId);
	}
}

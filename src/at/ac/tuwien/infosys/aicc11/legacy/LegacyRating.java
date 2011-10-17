package at.ac.tuwien.infosys.aicc11.legacy;

import at.ac.tuwien.infosys.aicc11.Ratings;

public class LegacyRating 
{
	public Ratings getRating(long customerId)
	{
		// chose rating based on customer-Id
		Ratings[] ratings = Ratings.values();
		int numRatings = ratings.length;
		int rating = (int)customerId % numRatings;
		return ratings[rating];
	}
}

package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.Customer;
import at.ac.tuwien.infosys.aicc11.Ratings;

public interface Rating {
	
	Ratings getRating(Customer customer);

}

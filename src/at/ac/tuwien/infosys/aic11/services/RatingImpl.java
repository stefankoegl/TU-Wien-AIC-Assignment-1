package at.ac.tuwien.infosys.aic11.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;

import at.ac.tuwien.infosys.aic11.dto.Ratings;
import at.ac.tuwien.infosys.aic11.legacy.LegacyCustomerRelationsManagement;
import at.ac.tuwien.infosys.aic11.legacy.LegacyException;

public class RatingImpl extends BaseServiceImpl implements Rating 
{
    LegacyCustomerRelationsManagement backend = LegacyCustomerRelationsManagement.instance();
	
    public RatingImpl() {
	logger = Logger.getLogger("RatingImpl");
	Logger parent = Logger.getLogger(" at.ac.tuwien.infosys.aic11.services.");
	logger.setParent(parent);
	logger.setLevel(Level.INFO);
    }
    
	public Ratings getRating(long customerId) 
	{
		entering("getRating", new Object[]{customerId});
		
	    try 
	    {
	    	Ratings result = backend.getRating(customerId);
	    	exiting("getRating", result);
	    	return result;
	    }
	    catch (LegacyException e) 
	    {
	    	logExceptionCatch("getRating", e);
	    	
			ResponseBuilderImpl builder = new ResponseBuilderImpl();
			builder.status(Response.Status.NOT_FOUND); // == 404
			builder.type("text/html");
			builder.entity("<h3>The requested customer id does not exist.</h3>");
			Response resp = builder.build();
			
			WebApplicationException exception = new WebApplicationException(resp);
			logExceptionThrow("getRating", exception);
			throw exception;
	    }
	}
}

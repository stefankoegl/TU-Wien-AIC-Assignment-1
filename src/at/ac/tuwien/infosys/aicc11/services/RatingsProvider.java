package at.ac.tuwien.infosys.aicc11.services;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import at.ac.tuwien.infosys.aicc11.Ratings;


@Produces("application/json")
@Provider
public class RatingsProvider implements MessageBodyWriter<Ratings> {

    @Override
    public long getSize(Ratings arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
	return -1;
    }

    @Override
    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
	// TODO do correct check
	return true;
    }

    @Override
    public void writeTo(Ratings arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
	    MultivaluedMap<String, Object> arg5, OutputStream arg6) throws IOException, WebApplicationException {
	String rating = "";
	switch (arg0) {
	    case AAA:
		rating = "AAA";
		break;
	    case AAPlus:
		rating = "AAPlus";
		break;
	    case AA:
		rating = "AA";
		break;	
	    case AAMinus:
		rating = "AAMinus";
		break;
	    case APlus:
		rating = "APlus";
		break;
	    case A:
		rating = "A";
		break;
	    case AMinus:
		rating = "AMinus";
		break;
	    case Defaulting:
		rating = "Defaulting";
		break;
	}
	String output = "{ \"rating\" : \"" + rating + "\" } ";
	arg6.write(output.getBytes());
	
    }

    
}

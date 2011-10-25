package at.ac.tuwien.infosys.aicc11.server;

import at.ac.tuwien.infosys.aicc11.services.RatingImpl;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;


public class SimpleRestServer {

    /**
     * @param args
     */
    public static void main(String[] args) {

	JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	sf.setResourceClasses(RatingImpl.class);
	sf.setAddress("http://localhost:9000/");
	
	sf.create();



    }

}

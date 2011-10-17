package at.ac.tuwien.infosys.aicc11.server;

import java.util.ArrayList;

import at.ac.tuwien.infosys.aicc11.services.RatingImpl;
//import at.ac.tuwien.infosys.aicc11.services.Rating;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import javax.ws.rs.ext.Provider;



import at.ac.tuwien.infosys.aicc11.services.RatingsProvider;

public class SimpleRestServer {

    /**
     * @param args
     */
    public static void main(String[] args) {


	JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	ArrayList providers = new ArrayList();
	Object prov = new RatingsProvider();
	providers.add(prov);
	sf.setProviders(providers);
	sf.setResourceClasses(RatingImpl.class);
	sf.setAddress("http://localhost:9000/");
	
	sf.create();



    }

}

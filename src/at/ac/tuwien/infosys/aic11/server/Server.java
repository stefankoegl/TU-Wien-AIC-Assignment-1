package at.ac.tuwien.infosys.aic11.server;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;

import java.util.HashMap;

import at.ac.tuwien.infosys.aic11.services.ContractManagement;
import at.ac.tuwien.infosys.aic11.services.ContractManagementImpl;
import at.ac.tuwien.infosys.aic11.services.CustomerRelationsManagement;
import at.ac.tuwien.infosys.aic11.services.CustomerRelationsManagementImpl;
import at.ac.tuwien.infosys.aic11.services.RatingImpl;
import at.ac.tuwien.infosys.aic11.services.Shipping;
import at.ac.tuwien.infosys.aic11.services.ShippingImpl;

public class Server 
{
	
	public Server() 
	{
	    	/*
	    	HashMap<String,Object> inProps= new HashMap<String,Object>();
	    	inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
	    	inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
	    	
	    	inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,ServerPasswordHandler.class.getName());
	    	
	    	WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);*/
	    	
	    	/*
	    	HashMap<String,Object> outProps= new HashMap<String,Object>();
	    	//configure it
	    	WSS4JInInterceptor wssOut = new WSS4JInInterceptor(outProps);*/
	    
		ContractManagement contractManagement = new ContractManagementImpl();
		JaxWsServerFactoryBean svrFactoryContract = new JaxWsServerFactoryBean();
		svrFactoryContract.setServiceClass(ContractManagement.class);
		svrFactoryContract.setAddress("http://localhost:9000/contracts");
		svrFactoryContract.setServiceBean(contractManagement);
		org.apache.cxf.endpoint.Server contractSrv = svrFactoryContract.create();
		//org.apache.cxf.endpoint.Endpoint contractEndpoint = contractSrv.getEndpoint();
		//contractEndpoint.getInInterceptors().add(wssIn);
		//contractEndpoint.getOutInterceptors().add(wssOut);

		
		CustomerRelationsManagement customerRelationsManagement = new CustomerRelationsManagementImpl();
		JaxWsServerFactoryBean svrFactoryCustomers = new JaxWsServerFactoryBean();
		svrFactoryCustomers.setServiceClass(CustomerRelationsManagement.class);
		svrFactoryCustomers.setAddress("http://localhost:9000/customers");
		svrFactoryCustomers.setServiceBean(customerRelationsManagement);
		svrFactoryCustomers.create();

		
		Shipping shipping = new ShippingImpl();
		JaxWsServerFactoryBean svrFactoryShipping = new JaxWsServerFactoryBean();
		svrFactoryShipping.setServiceClass(Shipping.class);
		svrFactoryShipping.setAddress("http://localhost:9000/shipping");
		svrFactoryShipping.setServiceBean(shipping);
		svrFactoryShipping.create();
		
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(RatingImpl.class);
		sf.setAddress("http://localhost:9000/");
		
		sf.create();
	}

	public static void main(String args[]) throws Exception
	{
		Server soapServer = new Server();
	}
}

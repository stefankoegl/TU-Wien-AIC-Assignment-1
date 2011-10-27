package at.ac.tuwien.infosys.aicc11.server;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import at.ac.tuwien.infosys.aicc11.services.ContractManagement;
import at.ac.tuwien.infosys.aicc11.services.ContractManagementImpl;
import at.ac.tuwien.infosys.aicc11.services.CustomerRelationsManagement;
import at.ac.tuwien.infosys.aicc11.services.CustomerRelationsManagementImpl;
import at.ac.tuwien.infosys.aicc11.services.RatingImpl;
import at.ac.tuwien.infosys.aicc11.services.Shipping;
import at.ac.tuwien.infosys.aicc11.services.ShippingImpl;

public class Server 
{
	
	public Server() 
	{
		ContractManagement contractManagement = new ContractManagementImpl();
		JaxWsServerFactoryBean svrFactoryContract = new JaxWsServerFactoryBean();
		svrFactoryContract.setServiceClass(ContractManagement.class);
		svrFactoryContract.setAddress("http://localhost:9000/contracts");
		svrFactoryContract.setServiceBean(contractManagement);
		svrFactoryContract.create();

		
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

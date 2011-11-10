package at.ac.tuwien.infosys.aicc11.tests;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import at.ac.tuwien.infosys.aic11.dto.Customer;
import at.ac.tuwien.infosys.aic11.services.CustomerRelationsManagement;
import at.ac.tuwien.infosys.aic11.services.CustomerRelationsManagementImpl;
import junit.framework.TestCase;

public class CustomerRelationsManagementServiceTest extends TestCase  {
	public void testService() {
		// Set up server
		CustomerRelationsManagement customerRelationsManagement = new CustomerRelationsManagementImpl();
		String address = "http://localhost:9001/contractManagement";
		Endpoint.publish(address, customerRelationsManagement);
		
		// Set up client
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CustomerRelationsManagement.class);
		factory.setAddress("http://localhost:9001/contractManagement");
		CustomerRelationsManagement client = (CustomerRelationsManagement) factory.create();

		// Primitive test for getCustomerBysName
		Customer customer = client.getCustomerByName("Felix");
		assertEquals("Winter", customer.getLastName());
		
		// Primitive test for getCustomerByID
		customer = client.getCustomerByID(1);
		assertEquals("A.", customer.getMiddleName());
	}
		
}

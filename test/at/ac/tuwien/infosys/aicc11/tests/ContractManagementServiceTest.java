package at.ac.tuwien.infosys.aicc11.tests;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import at.ac.tuwien.infosys.aic11.dto.CreditRequest;
import at.ac.tuwien.infosys.aic11.dto.Offer;
import at.ac.tuwien.infosys.aic11.services.ContractManagement;
import at.ac.tuwien.infosys.aic11.services.ContractManagementImpl;
import junit.framework.TestCase;

public class ContractManagementServiceTest extends TestCase {
	public void testService() {
		// Set up server
		ContractManagement contractManagement = new ContractManagementImpl();
		String address = "http://localhost:9001/contractManagement";
		Endpoint.publish(address, contractManagement);
		
		// Set up client
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ContractManagement.class);
		factory.setAddress("http://localhost:9001/contractManagement");
		ContractManagement client = (ContractManagement) factory.create();

		// Primitive test for placeCreditRequest
		CreditRequest creditRequest = new CreditRequest();
		Offer offer = client.placeCreditRequest(creditRequest);
	
		assertEquals(1, offer.getOfferId());
		
		// Primitive test for updateCreditRequest
		try {
		creditRequest = new CreditRequest();
		offer = client.updateCreditRequest(creditRequest);
		
		} catch (SOAPFaultException e) {
			assertEquals("request needs to have an Id for updating", e.getMessage());
		}
		
	}
}

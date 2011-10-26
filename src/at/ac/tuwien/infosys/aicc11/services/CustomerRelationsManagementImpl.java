package at.ac.tuwien.infosys.aicc11.services;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import at.ac.tuwien.infosys.aicc11.Customer;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyCustomerRelationsManagement;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyException;

@WebService(endpointInterface="at.ac.tuwien.infosys.aicc11.services.CustomerRelationsManagement",
targetNamespace="at.ac.tuwien.infosys.aic11.services",
serviceName="CustomerRelationsManagementService"
)
public class CustomerRelationsManagementImpl extends BaseServiceImpl implements
		CustomerRelationsManagement {

	private LegacyCustomerRelationsManagement legacyCustomerRelationsManagement = LegacyCustomerRelationsManagement.instance();
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: CustomerRelationsManagementImpl <host> <port>");
		}
		
		System.out.println("Starting Server");
		CustomerRelationsManagement customerRelationsManagement = new CustomerRelationsManagementImpl();
		String address = "http://" + args[0] + ":" + args[1] +"/customerRelationsManagement";
		Endpoint.publish(address, customerRelationsManagement);
		
		while(true) {}
	}
	
	@Override
	public Customer getCustomerByName(String name) {
		Customer customer = null;
		try {
			customer = legacyCustomerRelationsManagement.getCustomerByName(name);
		} catch (LegacyException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	@Override
	public Customer getCustomerByID(long customerId) {
		Customer customer = null;
		try {
			customer = legacyCustomerRelationsManagement.getCustomerByID(customerId);
		} catch (LegacyException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

}

package at.ac.tuwien.infosys.aicc11.services;

import javax.jws.WebParam;
import javax.jws.WebService;

import at.ac.tuwien.infosys.aic11.dto.Customer;

@WebService(name="CustomerRelationsManagementService",
targetNamespace="at.ac.tuwien.infosys.aic11.services")
public interface CustomerRelationsManagement {
	
	public Customer getCustomerByName(@WebParam(name="name")String name);
	public Customer getCustomerByID(@WebParam(name="customerId")long customerId);

}

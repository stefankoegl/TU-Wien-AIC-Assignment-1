package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.Customer;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyCustomerRelationsManagement;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyException;

public class CustomerRelationsManagementImpl extends BaseServiceImpl implements
		CustomerRelationsManagement {

	private LegacyCustomerRelationsManagement legacyCustomerRelationsManagement = LegacyCustomerRelationsManagement.instance();
	
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

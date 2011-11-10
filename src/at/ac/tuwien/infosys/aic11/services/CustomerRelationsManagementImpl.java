package at.ac.tuwien.infosys.aic11.services;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;

import at.ac.tuwien.infosys.aic11.dto.Customer;
import at.ac.tuwien.infosys.aic11.legacy.LegacyCustomerRelationsManagement;
import at.ac.tuwien.infosys.aic11.legacy.LegacyException;

public class CustomerRelationsManagementImpl extends BaseServiceImpl implements
		CustomerRelationsManagement {

	private LegacyCustomerRelationsManagement legacyCustomerRelationsManagement = LegacyCustomerRelationsManagement.instance();
	
	@Override
	public Customer getCustomerByName(String name) {
		Customer customer = null;
		try {
			customer = legacyCustomerRelationsManagement.getCustomerByName(name);
		} catch (LegacyException e) {
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			throw fault;
		}
		return customer;
	}

	@Override
	public Customer getCustomerByID(long customerId) {
		Customer customer = null;
		try {
			customer = legacyCustomerRelationsManagement.getCustomerByID(customerId);
		} catch (LegacyException e) {
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			throw fault;
		}
		return customer;
	}

}

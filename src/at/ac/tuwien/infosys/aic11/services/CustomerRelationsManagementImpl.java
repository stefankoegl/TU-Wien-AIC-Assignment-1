package at.ac.tuwien.infosys.aic11.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;

import at.ac.tuwien.infosys.aic11.dto.Customer;
import at.ac.tuwien.infosys.aic11.legacy.LegacyCustomerRelationsManagement;
import at.ac.tuwien.infosys.aic11.legacy.LegacyException;

@WebService(endpointInterface="at.ac.tuwien.infosys.aic11.services.CustomerRelationsManagement",
targetNamespace="at.ac.tuwien.infosys.aic11.services"
)
public class CustomerRelationsManagementImpl extends BaseServiceImpl implements
		CustomerRelationsManagement {

	private LegacyCustomerRelationsManagement legacyCustomerRelationsManagement = LegacyCustomerRelationsManagement.instance();
	
	public CustomerRelationsManagementImpl() {
	    logger = Logger.getLogger("CustomerRelationsManagementImpl");
	    Logger parent = Logger.getLogger(" at.ac.tuwien.infosys.aic11.services.");
	    logger.setParent(parent);
	    logger.setLevel(Level.INFO);
	}
	
	@Override
	public Customer getCustomerByName(String name) {
		entering("getCustomerByName", new Object[]{name});
		Customer customer = null;
		try {
			customer = legacyCustomerRelationsManagement.getCustomerByName(name);
		} catch (LegacyException e) {
			logExceptionCatch("getCustomerByName(String)", e);
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			logExceptionThrow("getCustomerByName(String)", fault);
			throw fault;
		}
		exiting("getCustomerByName", name);
		return customer;
	}

	@Override
	public Customer getCustomerByID(long customerId) {
		entering("getCustomerByName", new Object[]{customerId});
		Customer customer = null;
		try {
			customer = legacyCustomerRelationsManagement.getCustomerByID(customerId);
		} catch (LegacyException e) {
			logExceptionCatch("getCustomerByID(long)", e);			
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			logExceptionThrow("getCustomerByID(long)", fault);
			throw fault;
		}
		exiting("getCustomerByID", customer);
		return customer;
	}

}

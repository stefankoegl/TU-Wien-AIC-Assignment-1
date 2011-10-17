package at.ac.tuwien.infosys.aicc11.legacy;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Map;

import at.ac.tuwien.infosys.aicc11.Address;
import at.ac.tuwien.infosys.aicc11.BankTransfer;
import at.ac.tuwien.infosys.aicc11.Cheque;
import at.ac.tuwien.infosys.aicc11.Customer;

/**
 * I assume that we don't need to create new customers, just use existing ones
 * @author stefan
 *
 */
public class LegacyCustomerRelationsManagement {
	
	private Map<Long, Customer> customers = new Hashtable<Long, Customer>();
	
	public LegacyCustomerRelationsManagement()
	{		
		customers.put(1L, new Customer(1, "Stefan", "A.", "Kögl", new BigDecimal(10000), new Address(), new Cheque("Stefan Kögl")));
		customers.put(2L, new Customer(2, "Stefan", "B.", "Derkits", new BigDecimal(10000), new Address(), new BankTransfer("MyBank", "12345", "ABCA234234")));
		customers.put(3L, new Customer(3, "Felix",  "C.", "Winter", new BigDecimal(10000), new Address(), new Cheque("Felix Winter")));
		customers.put(4L, new Customer(4, "Manfred", "M.", "Mustermann", new BigDecimal(10000), new Address(), new BankTransfer("Some Bank", "asdfasdf", "123123asdfasdf")));
		customers.put(5L, new Customer(5, "Erika", "E.", "Musterfrau", new BigDecimal(10000), new Address(), new Cheque("Erika Musterfrau")));
	}
	
	/**
	 * Tries to find an exact match in any part of the customers' name. 
	 * The first match (or null) is returned
	 * @param name
	 * @return
	 */
	public Customer getCustomerByName(String name)
	{
		for(Customer customer : customers.values())
		{
			String fullname = customer.getFirstName() + customer.getMiddleName() + customer.getLastName();
			
			if(fullname.contains(name))
			{
				return customer;
			}
		}
		
		return null;	
	}
	
	public synchronized void sendEmail(Customer customer)
	throws LegacyException
	{
		if (!customers.containsKey(customer.getCustomerId()))
		{
			throw new LegacyException("customer " + customer.getCustomerId() + " does not exist");
		}
		
		// send mail! 
	}
}

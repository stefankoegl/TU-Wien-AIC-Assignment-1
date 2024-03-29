package at.ac.tuwien.infosys.aicc11.tests;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import at.ac.tuwien.infosys.aic11.dto.Address;
import at.ac.tuwien.infosys.aic11.dto.BankTransfer;
import at.ac.tuwien.infosys.aic11.dto.CreditRequest;
import at.ac.tuwien.infosys.aic11.dto.Customer;
import at.ac.tuwien.infosys.aic11.dto.Duration;
import at.ac.tuwien.infosys.aic11.dto.InterestRate;
import at.ac.tuwien.infosys.aic11.dto.Money;
import at.ac.tuwien.infosys.aic11.dto.Offer;
import at.ac.tuwien.infosys.aic11.dto.Warrantor;
import junit.framework.TestCase;

public class JAXBTests extends TestCase {


	public void test_marshal_unmarshal() throws JAXBException
	/***
	 * Tests if marshalling and unmarshalling an object results in the original object
	 */
	{
		Offer a = new Offer(150, "first offer", new InterestRate(0.5),  
			new CreditRequest(123, new Vector<Warrantor>(), 
				new Customer(100, "A", "B", "C", new BigDecimal(100), 
						new Address("100", "Street", "City", "100A", "10/2", "1000"),
						new BankTransfer("MyBank", "10000", "AAAA")),
				new Money("EUR", 1000), 
				new Duration(100), "test"));
		
		JAXBContext context = JAXBContext.newInstance(CreditRequest.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		m.marshal(a, sw);
		
		Unmarshaller u = context.createUnmarshaller();
		Offer b = (Offer)u.unmarshal(new StringReader(sw.toString()));

		assertEquals(a, b);
	}
}

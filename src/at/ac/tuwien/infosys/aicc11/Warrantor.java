package at.ac.tuwien.infosys.aicc11;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

public class Warrantor extends Customer {
	
	public Warrantor()
	{
		this(0, null, null, null, null, new Address(), new Cheque(), Ratings.A,
				new Vector<CreditRequest>());
	}

	public Warrantor(long customerId, String firstName, String middleName,
			String lastName, BigDecimal openBalance, Address address,
			DisbursementPreference disbursementPreference, Ratings rating,
			List<CreditRequest> creditRequests) {
		super(customerId, firstName, middleName, lastName, openBalance, address,
				disbursementPreference, rating, creditRequests);
	}

}

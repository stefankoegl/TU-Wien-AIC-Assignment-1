package at.ac.tuwien.infosys.aicc11;

import java.math.BigDecimal;
import java.util.List;

public class Warrantor extends Customer {

	public Warrantor(long customerId, String firstName, String middleName,
			String lastName, BigDecimal openBalance, Address address,
			DisbursementPreference disbursementPreference, Rating rating,
			List<CreditRequest> creditRequests) {
		super(customerId, firstName, middleName, lastName, openBalance, address,
				disbursementPreference, rating, creditRequests);
	}

}

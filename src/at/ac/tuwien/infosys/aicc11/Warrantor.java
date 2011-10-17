package at.ac.tuwien.infosys.aicc11;

import java.math.BigDecimal;

public class Warrantor extends Customer {
	
	public Warrantor()
	{
		this(0, null, null, null, null, new Address(), new Cheque());
	}

	public Warrantor(long customerId, String firstName, String middleName,
			String lastName, BigDecimal openBalance, Address address,
			DisbursementPreference disbursementPreference) {
		super(customerId, firstName, middleName, lastName, openBalance, address,
				disbursementPreference);
	}

}

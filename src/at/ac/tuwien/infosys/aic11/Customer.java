package at.ac.tuwien.infosys.aic11;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Customer {
	
	private long customerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private BigDecimal openBalance;
	private Address address;
	private DisbursementPreference disbursementPreference;
	private Ratings rating;
	private List<CreditRequest> creditRequests;
	
	public Customer()
	{
		this(0, "", "", "", new BigDecimal(0), new Address(), new Cheque());
	}
	
	public Customer(long customerId, String firstName, String middleName,
			String lastName, BigDecimal openBalance, Address address,
			DisbursementPreference disbursementPreference) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.openBalance = openBalance;
		this.address = address;
		this.disbursementPreference = disbursementPreference;
		this.rating = null;
		this.creditRequests = new Vector<CreditRequest>();
	}

	@XmlAttribute(name="customer_id")
	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	@XmlElement(name="first_name")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@XmlElement(name="middle_name")
	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@XmlElement(name="last_name")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@XmlElement(name="open_balance")
	public BigDecimal getOpenBalance() {
		return openBalance;
	}


	public void setOpenBalance(BigDecimal openBalance) {
		this.openBalance = openBalance;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@XmlElement(name="disbursement_preference")
	public DisbursementPreference getDisbursementPreference() {
		return disbursementPreference;
	}


	public void setDisbursementPreference(
			DisbursementPreference disbursementPreference) {
		this.disbursementPreference = disbursementPreference;
	}

	@XmlElement(name="rating", nillable = true)
	public Ratings getRating() {
		return rating;
	}


	public void setRating(Ratings rating) {
		this.rating = rating;
	}


	@XmlElement(name="credit_request")
	public List<CreditRequest> getCreditRequests() {
		return creditRequests;
	}


	public void setCreditRequests(List<CreditRequest> creditRequests) {
		this.creditRequests = creditRequests;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((creditRequests == null) ? 0 : creditRequests.hashCode());
		result = prime * result + (int) (customerId ^ (customerId >>> 32));
		result = prime
				* result
				+ ((disbursementPreference == null) ? 0
						: disbursementPreference.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((openBalance == null) ? 0 : openBalance.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (creditRequests == null) {
			if (other.creditRequests != null)
				return false;
		} else if (!creditRequests.equals(other.creditRequests))
			return false;
		if (customerId != other.customerId)
			return false;
		if (disbursementPreference == null) {
			if (other.disbursementPreference != null)
				return false;
		} else if (!disbursementPreference.equals(other.disbursementPreference))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (openBalance == null) {
			if (other.openBalance != null)
				return false;
		} else if (!openBalance.equals(other.openBalance))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		return true;
	}
}

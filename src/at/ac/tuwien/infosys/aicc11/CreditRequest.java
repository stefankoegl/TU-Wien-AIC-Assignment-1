package at.ac.tuwien.infosys.aicc11;

import java.util.List;

public class CreditRequest {
	
	private long requestId;
	private String reason;
	private List<Warrantor> warrantors;
	private Customer customer;
	private Offer offer;
	private Money amount;
	private Duration duration;
	
	
	public CreditRequest(long requestId, List<Warrantor> warrantors, 
			Customer customer, Offer offer, Money amount, 
			Duration duration, String reason) {
		super();
		this.requestId = requestId;
		this.reason = reason;
		this.warrantors = warrantors;
		this.customer = customer;
		this.offer = offer;
		this.amount = amount;
		this.duration = duration;
	}


	public long getRequestId() {
		return requestId;
	}


	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	public List<Warrantor> getWarrantors() {
		return warrantors;
	}


	public void setWarrantors(List<Warrantor> warrantors) {
		this.warrantors = warrantors;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Offer getOffer() {
		return offer;
	}


	public void setOffer(Offer offer) {
		this.offer = offer;
	}


	public Money getAmount() {
		return amount;
	}


	public void setAmount(Money amount) {
		this.amount = amount;
	}


	public Duration getDuration() {
		return duration;
	}


	public void setDuration(Duration duration) {
		this.duration = duration;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((offer == null) ? 0 : offer.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + (int) (requestId ^ (requestId >>> 32));
		result = prime * result
				+ ((warrantors == null) ? 0 : warrantors.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CreditRequest))
			return false;
		CreditRequest other = (CreditRequest) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (offer == null) {
			if (other.offer != null)
				return false;
		} else if (!offer.equals(other.offer))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (requestId != other.requestId)
			return false;
		if (warrantors == null) {
			if (other.warrantors != null)
				return false;
		} else if (!warrantors.equals(other.warrantors))
			return false;
		return true;
	}
}
package at.ac.tuwien.infosys.aicc11;

public class Offer {
	
	private long offerId;
	private String comments;
	private InterestRate interestRate;
	
	
	public Offer(long offerId, String comments, InterestRate interestRate) {
		super();
		this.offerId = offerId;
		this.comments = comments;
		this.interestRate = interestRate;
	}


	public long getOfferId() {
		return offerId;
	}


	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public InterestRate getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(InterestRate interestRate) {
		this.interestRate = interestRate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((interestRate == null) ? 0 : interestRate.hashCode());
		result = prime * result + (int) (offerId ^ (offerId >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Offer))
			return false;
		Offer other = (Offer) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (interestRate == null) {
			if (other.interestRate != null)
				return false;
		} else if (!interestRate.equals(other.interestRate))
			return false;
		if (offerId != other.offerId)
			return false;
		return true;
	}
}

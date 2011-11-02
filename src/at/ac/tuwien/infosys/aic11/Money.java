package at.ac.tuwien.infosys.aic11;

import javax.xml.bind.annotation.XmlElement;

public class Money {
	
	private String currencyCode;
	private long amount;
	
	public Money()
	{
		this("", 0);
	}
	
	public Money(String currencyCode, long amount) {
		super();
		this.currencyCode = currencyCode;
		this.amount = amount;
	}

	@XmlElement(name="currency_code")
	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public long getAmount() {
		return amount;
	}


	public void setAmount(long amount) {
		this.amount = amount;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result
				+ ((currencyCode == null) ? 0 : currencyCode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Money))
			return false;
		Money other = (Money) obj;
		if (amount != other.amount)
			return false;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		return true;
	}
}

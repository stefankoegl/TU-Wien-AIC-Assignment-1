package at.ac.tuwien.infosys.aicc11;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bank_transfer")
public class BankTransfer extends DisbursementPreference {

	private String bankName;
	private String bic;
	private String iban;
	
	public BankTransfer()
	{
		this("", "", "");
	}
	
	public BankTransfer(String bankName, String bic, String iban) {
		super();
		this.bankName = bankName;
		this.bic = bic;
		this.iban = iban;
	}
	
	@XmlElement(name="bank_name")
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((bic == null) ? 0 : bic.hashCode());
		result = prime * result + ((iban == null) ? 0 : iban.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BankTransfer))
			return false;
		BankTransfer other = (BankTransfer) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (bic == null) {
			if (other.bic != null)
				return false;
		} else if (!bic.equals(other.bic))
			return false;
		if (iban == null) {
			if (other.iban != null)
				return false;
		} else if (!iban.equals(other.iban))
			return false;
		return true;
	}
}

package at.ac.tuwien.infosys.aic11;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cheque")
public class Cheque extends DisbursementPreference {

	private String name;
	
	public Cheque()
	{
		this("");
	}

	public Cheque(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cheque))
			return false;
		Cheque other = (Cheque) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

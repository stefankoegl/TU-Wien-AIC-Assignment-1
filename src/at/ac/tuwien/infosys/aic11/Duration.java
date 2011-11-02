package at.ac.tuwien.infosys.aic11;

public class Duration {
	
	private int years;
	
	public Duration()
	{
		this(0);
	}

	public Duration(int years) {
		super();
		this.years = years;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + years;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Duration))
			return false;
		Duration other = (Duration) obj;
		if (years != other.years)
			return false;
		return true;
	}
}

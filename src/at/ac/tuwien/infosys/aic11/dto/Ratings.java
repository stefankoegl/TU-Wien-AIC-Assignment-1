package at.ac.tuwien.infosys.aic11.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rating")
public enum Ratings {
	
	AAA,
	
	AAPlus,
	
	AA,
	
	AAMinus,
	
	APlus,
	
	A,
	
	AMinus,
	
	Defaulting

}

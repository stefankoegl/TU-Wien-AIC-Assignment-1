package at.ac.tuwien.infosys.aic11.dto;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Cheque.class, BankTransfer.class})
public abstract class DisbursementPreference {

}

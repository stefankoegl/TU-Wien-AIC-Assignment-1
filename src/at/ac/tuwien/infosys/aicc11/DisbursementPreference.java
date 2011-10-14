package at.ac.tuwien.infosys.aicc11;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Cheque.class, BankTransfer.class})
public abstract class DisbursementPreference {

}

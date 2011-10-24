package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;


public interface ContractManagement {
	
	void placeCreditRequest(CreditRequest creditRequest);
	
	void updateCreditRequest(CreditRequest creditRequest);
	
	void acceptOffer(Offer offer);
	
	void declineOffer(Offer offer);

}

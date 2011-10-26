package at.ac.tuwien.infosys.aicc11.services;

import javax.jws.WebParam;
import javax.jws.WebService;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;


@WebService()
public interface ContractManagement {
	
	void placeCreditRequest(@WebParam(name="creditRequest") CreditRequest creditRequest);
	
	void updateCreditRequest(CreditRequest creditRequest);
	
	void acceptOffer(Offer offer);
	
	void declineOffer(Offer offer);

}

package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;

import javax.jws.*;


@WebService(name="ContractManagementService",
targetNamespace="at.ac.tuwien.infosys.aic11.services")
public interface ContractManagement {
	
	void placeCreditRequest(@WebParam(name="creditRequest") CreditRequest creditRequest);
	
	void updateCreditRequest(@WebParam(name="creditRequest") CreditRequest creditRequest);
	
	void acceptOffer(@WebParam(name="offer") Offer offer);
	
	void declineOffer(@WebParam(name="offer") Offer offer);

}

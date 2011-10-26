package at.ac.tuwien.infosys.aicc11.services;

import javax.jws.WebParam;
import javax.jws.WebService;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;

@WebService(name="ContractManagementService",
targetNamespace="at.ac.tuwien.infosys.aic11.services")
public interface ContractManagement {
	
	CreditRequest placeCreditRequest(@WebParam(name="creditRequest") CreditRequest creditRequest);
	
	CreditRequest updateCreditRequest(@WebParam(name="creditRequest") CreditRequest creditRequest);
	
	void acceptOffer(@WebParam(name="offer") Offer offer);
	
	void declineOffer(@WebParam(name="offer") Offer offer);

}

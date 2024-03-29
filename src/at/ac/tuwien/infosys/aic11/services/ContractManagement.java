package at.ac.tuwien.infosys.aic11.services;

import javax.jws.WebParam;
import javax.jws.WebService;

import at.ac.tuwien.infosys.aic11.dto.CreditRequest;
import at.ac.tuwien.infosys.aic11.dto.Offer;

@WebService(name="ContractManagementService",
targetNamespace="at.ac.tuwien.infosys.aic11.services")
public interface ContractManagement {
	
	Offer placeCreditRequest(@WebParam(name="creditRequest") CreditRequest creditRequest);
	
	Offer updateCreditRequest(@WebParam(name="creditRequest") CreditRequest creditRequest);
	
	void acceptOffer(@WebParam(name="offer") Offer offer);
	
	void declineOffer(@WebParam(name="offer") Offer offer);

}

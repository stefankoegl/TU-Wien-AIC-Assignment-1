package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyContractManagement;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyException;

public class ContractManagementImpl extends BaseServiceImpl implements ContractManagement {

	private LegacyContractManagement legacyContractManagement = LegacyContractManagement.instance();
	
	@Override
	public void acceptOffer(Offer offer) {
		entering("acceptOffer", new Object[]{offer});
		
		try {
			legacyContractManagement.acceptOffer(offer);
		} catch (LegacyException e) {
			System.err.println(e.getMessage());
		}
		
		exiting("acceptOffer");
	}

	@Override
	public void declineOffer(Offer offer) 
	{
		entering("declineOffer", new Object[]{offer});

		try {
			legacyContractManagement.deleteOffer(offer);
		} catch (LegacyException e) {
			System.err.println(e.getMessage());
		}
		
		exiting("declineOffer");
	}

	@Override
	public CreditRequest placeCreditRequest(CreditRequest creditRequest) 
	{		
		entering("placeCreditRequest", new Object[]{creditRequest});

		try {
			legacyContractManagement.createCreditRequest(creditRequest);
		} catch (LegacyException e) {
			System.err.println(e.getMessage());
		}
		
		exiting("placeCreditRequest");
		return creditRequest;
	}

	@Override
	public CreditRequest updateCreditRequest(CreditRequest creditRequest) 
	{
		entering("updateCreditRequest", new Object[]{creditRequest});

		try {
			legacyContractManagement.updateCreditRequest(creditRequest);
		} catch (LegacyException e) {
			System.err.println(e.getMessage());
		}

		exiting("updateCreditRequest");
		return creditRequest;
	}
}

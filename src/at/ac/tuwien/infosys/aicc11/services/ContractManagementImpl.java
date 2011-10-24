package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;

public class ContractManagementImpl extends BaseServiceImpl implements ContractManagement {

	@Override
	public void acceptOffer(Offer offer) {
		entering("acceptOffer", new Object[]{offer});
		
		// TODO Auto-generated method stub
		
		exiting("acceptOffer");
	}

	@Override
	public void declineOffer(Offer offer) 
	{
		entering("declineOffer", new Object[]{offer});

		// TODO Auto-generated method stub

		exiting("declineOffer");
	}

	@Override
	public void placeCreditRequest(CreditRequest creditRequest) 
	{		
		entering("placeCreditRequest", new Object[]{creditRequest});

		// TODO Auto-generated method stub
		
		exiting("placeCreditRequest");
	}

	@Override
	public void updateCreditRequest(CreditRequest creditRequest) 
	{
		entering("updateCreditRequest", new Object[]{creditRequest});

		// TODO Auto-generated method stub

		exiting("updateCreditRequest");
	}
}

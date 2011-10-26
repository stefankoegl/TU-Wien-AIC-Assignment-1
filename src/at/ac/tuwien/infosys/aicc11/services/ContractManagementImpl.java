package at.ac.tuwien.infosys.aicc11.services;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyContractManagement;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyException;

import javax.jws.*;
import javax.xml.ws.*;

@WebService(endpointInterface="at.ac.tuwien.infosys.aicc11.services.ContractManagement",
targetNamespace="at.ac.tuwien.infosys.aic11.services",
serviceName="ContractManagementService"
)

public class ContractManagementImpl extends BaseServiceImpl implements ContractManagement {

	private LegacyContractManagement legacyContractManagement = LegacyContractManagement.instance();
	
	public static void main(String[] args) {
		System.out.println("Starting Server");
		ContractManagement contractManagement = new ContractManagementImpl();
		String address = "http://localhost:9000/contractManagement";
		Endpoint.publish(address, contractManagement);
		
		while(true) {}
	}
	
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
	public void placeCreditRequest(CreditRequest creditRequest) 
	{		
		entering("placeCreditRequest", new Object[]{creditRequest});

		try {
			legacyContractManagement.createCreditRequest(creditRequest);
		} catch (LegacyException e) {
			System.err.println(e.getMessage());
		}
		
		exiting("placeCreditRequest");
	}

	@Override
	public void updateCreditRequest(CreditRequest creditRequest) 
	{
		entering("updateCreditRequest", new Object[]{creditRequest});

		try {
			legacyContractManagement.updateCreditRequest(creditRequest);
		} catch (LegacyException e) {
			System.err.println(e.getMessage());
		}

		exiting("updateCreditRequest");
	}
}

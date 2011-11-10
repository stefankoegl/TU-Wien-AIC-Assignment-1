package at.ac.tuwien.infosys.aic11.services;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;

import at.ac.tuwien.infosys.aic11.dto.CreditRequest;
import at.ac.tuwien.infosys.aic11.dto.Offer;
import at.ac.tuwien.infosys.aic11.legacy.LegacyContractManagement;
import at.ac.tuwien.infosys.aic11.legacy.LegacyException;

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
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			throw fault;
		}
		
		exiting("declineOffer");
	}

	@Override
	public Offer placeCreditRequest(CreditRequest creditRequest) 
	{		
		entering("placeCreditRequest", new Object[]{creditRequest});
		
		Offer offer = null;
		
		try {
			offer = legacyContractManagement.createCreditRequest(creditRequest);
		} catch (LegacyException e) {
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			throw fault;
		}
		
		exiting("placeCreditRequest");
		return offer;
	}

	@Override
	public Offer updateCreditRequest(CreditRequest creditRequest) 
	{
		entering("updateCreditRequest", new Object[]{creditRequest});
		Offer offer = new Offer();
		try {
			offer = legacyContractManagement.updateCreditRequest(creditRequest);
		} catch (LegacyException e) {
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			throw fault;
		}

		exiting("updateCreditRequest");
		return offer;
	}
}

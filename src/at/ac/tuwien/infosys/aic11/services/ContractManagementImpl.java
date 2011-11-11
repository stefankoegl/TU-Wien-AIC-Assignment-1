package at.ac.tuwien.infosys.aic11.services;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;

import at.ac.tuwien.infosys.aic11.dto.CreditRequest;
import at.ac.tuwien.infosys.aic11.dto.Offer;
import at.ac.tuwien.infosys.aic11.legacy.LegacyContractManagement;
import at.ac.tuwien.infosys.aic11.legacy.LegacyException;

public class ContractManagementImpl extends BaseServiceImpl implements ContractManagement {

	private LegacyContractManagement legacyContractManagement = LegacyContractManagement.instance();

	public ContractManagementImpl() {
		super();
	}
	
	@Override
	public void acceptOffer(Offer offer) {
		entering("acceptOffer", new Object[]{offer});
		
		try {
			legacyContractManagement.acceptOffer(offer);
		} catch (LegacyException e) {
			logExceptionCatch("acceptOffer(Offer)", e);
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			logExceptionThrow("acceptOffer(Offer)", fault);
			throw fault;
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
			logExceptionCatch("declineOffer(Offer)", e);
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			logExceptionThrow("declineOffer(Offer)", fault);
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
			logExceptionCatch("placeCreditRequest(CreditRequest)", e);
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			logExceptionThrow("placeCreditRequest(CreditRequest)", fault);
			throw fault;
		}
		
		exiting("placeCreditRequest", offer);
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
			logExceptionCatch("updateCreditRequest(CreditRequest)", e);
			SoapFault fault = new SoapFault(e.getMessage(), new QName("LegacyException"));
			logExceptionThrow("updateCreditRequest(CreditRequest)", fault);
			throw fault;
		}

		exiting("updateCreditRequest", offer);
		return offer;
	}
}

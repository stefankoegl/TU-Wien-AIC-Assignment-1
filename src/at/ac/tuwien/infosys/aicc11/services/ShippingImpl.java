package at.ac.tuwien.infosys.aicc11.services;



import org.apache.cxf.binding.soap.SoapFault;

import at.ac.tuwien.infosys.aic11.dto.Offer;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyException;
import at.ac.tuwien.infosys.aicc11.legacy.LegacyShipping;

public class ShippingImpl extends BaseServiceImpl implements Shipping {
    
    private LegacyShipping legacyShipping = LegacyShipping.instance();
    
    @Override
    public void sendContractFax(Offer offer) {
	try {
	    legacyShipping.sendFax(offer);
	} catch (LegacyException e) {
	    //could be a better soap Fault
	    throw new SoapFault("error",SoapFault.FAULT_CODE_CLIENT);
	}
	
    }

    @Override
    public boolean contractSigned(Offer offer) {
	return legacyShipping.receivedFaxReply(offer);
    }

}

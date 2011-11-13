package at.ac.tuwien.infosys.aic11.services;



import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;

import at.ac.tuwien.infosys.aic11.dto.Offer;
import at.ac.tuwien.infosys.aic11.legacy.LegacyException;
import at.ac.tuwien.infosys.aic11.legacy.LegacyShipping;

@WebService(endpointInterface="at.ac.tuwien.infosys.aic11.services.Shipping",
targetNamespace="at.ac.tuwien.infosys.aic11.services"
)
public class ShippingImpl extends BaseServiceImpl implements Shipping {

	private LegacyShipping legacyShipping = LegacyShipping.instance();

	public ShippingImpl() {
	    logger = Logger.getLogger("ShippingImpl");
	    Logger parent = Logger.getLogger(" at.ac.tuwien.infosys.aic11.services.");
	    logger.setParent(parent);
	    logger.setLevel(Level.INFO);
	}
	
	@Override
	public void sendContractFax(Offer offer) {
		entering("sendContractFax", new Object[]{offer});
		try {
			legacyShipping.sendFax(offer);
		} catch (LegacyException e) {
			throw new SoapFault(e.getMessage(), new QName("LegacyException"));
		}
		exiting("sendContractFax");
	}

	@Override
	public boolean contractSigned(Offer offer) {
		entering("contractSigned", new Object[]{offer});
		boolean result = legacyShipping.receivedFaxReply(offer);
		exiting("contractSigned", result);
		return result;
	}

}

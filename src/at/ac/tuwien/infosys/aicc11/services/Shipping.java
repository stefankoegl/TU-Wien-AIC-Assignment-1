package at.ac.tuwien.infosys.aicc11.services;

import javax.jws.WebParam;
import javax.jws.WebService;

import at.ac.tuwien.infosys.aic11.dto.Offer;

@WebService(name="ShippingService",
targetNamespace="at.ac.tuwien.infosys.aic11.services")
public interface Shipping {
    
    void sendContractFax(@WebParam(name="offer")Offer offer);
    
    boolean contractSigned(@WebParam(name="offer")Offer offer);

}

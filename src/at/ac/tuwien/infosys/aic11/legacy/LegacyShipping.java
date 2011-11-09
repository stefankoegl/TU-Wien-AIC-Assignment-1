package at.ac.tuwien.infosys.aic11.legacy;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import at.ac.tuwien.infosys.aic11.dto.Offer;

public class LegacyShipping {
    
    	public class ReceiveFaxTask extends TimerTask {
	    
	    @Override
	    public void run() {
		for ( long offerID : sentFaxes.keySet() ) {
		    if (sentFaxes.get(offerID) == false)
			try {
			    receivedFaxReply(offerID);
			} catch (LegacyException e) {
			    //should never happen
			}
		}
		
	    }
	    
	}
	
    	private static LegacyShipping instance;
    	
    	public static LegacyShipping instance() {
    	    if (instance == null)
    		instance = new LegacyShipping();
    	    return instance;
    	}
    	
    	private LegacyShipping() {
    	    Timer faxTimer = new Timer();
    	    faxTimer.schedule(new ReceiveFaxTask(), 0, 3500);
    	}
    
	/* Key: requestId, value: true if fax reply has already been received */
	private ConcurrentHashMap<Long, Boolean> sentFaxes = new ConcurrentHashMap<Long, Boolean>();
	
	public synchronized void sendFax(Offer offer)
	throws LegacyException
	{
		long requestId = offer.getOfferId();
		
		if(sentFaxes.containsKey(requestId))
		{
			throw new LegacyException("fax for Id " + requestId + " already sent");
		}
		
		sentFaxes.put(requestId, false);
	}
	
	
	public synchronized void receivedFaxReply(Long offerId)
	throws LegacyException
	{
		if(!sentFaxes.containsKey(offerId))
		{
			throw new LegacyException("fax for Id " + offerId + " has not been sent");
		}
		
		if(sentFaxes.get(offerId) == true)
		{
			throw new LegacyException("fax for Id " + offerId + " has already been received");
		}
		
		sentFaxes.put(offerId, true);
		
		//TODO: trigger some callback
	}
	
	//use this via polling
	public synchronized boolean receivedFaxReply(Offer offer) {
	    return sentFaxes.get(offer.getOfferId());
	}
}

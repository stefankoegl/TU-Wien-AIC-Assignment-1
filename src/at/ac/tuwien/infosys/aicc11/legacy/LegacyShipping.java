package at.ac.tuwien.infosys.aicc11.legacy;

import java.util.concurrent.ConcurrentHashMap;

import at.ac.tuwien.infosys.aic11.dto.CreditRequest;

public class LegacyShipping {
	
    	private static LegacyShipping instance;
    	
    	public static LegacyShipping instance() {
    	    if (instance == null)
    		instance = new LegacyShipping();
    	    return instance;
    	}
    	
    	private LegacyShipping() {
    	}
    
	/* Key: requestId, value: true if fax reply has already been received */
	private ConcurrentHashMap<Long, Boolean> sentFaxes = new ConcurrentHashMap<Long, Boolean>();
	
	public synchronized void sendFax(CreditRequest creditRequest)
	throws LegacyException
	{
		long requestId = creditRequest.getRequestId();
		
		if(sentFaxes.containsKey(requestId))
		{
			throw new LegacyException("fax for Id " + requestId + " already sent");
		}
		
		sentFaxes.put(requestId, false);
	}
	
	
	public synchronized void receivedFaxReply(Long requestId)
	throws LegacyException
	{
		if(!sentFaxes.containsKey(requestId))
		{
			throw new LegacyException("fax for Id " + requestId + " has not been sent");
		}
		
		if(sentFaxes.get(requestId) == true)
		{
			throw new LegacyException("fax for Id " + requestId + " has already been received");
		}
		
		sentFaxes.put(requestId, true);
		
		//TODO: trigger some callback
	}
}

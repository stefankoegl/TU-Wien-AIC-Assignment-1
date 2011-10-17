package at.ac.tuwien.infosys.aicc11.legacy;

import java.util.Hashtable;
import java.util.Map;

import at.ac.tuwien.infosys.aicc11.CreditRequest;

public class LegacyShipping {
	
	/* Key: requestId, value: true if fax reply has already been received */
	private Map<Long, Boolean> sentFaxes = new Hashtable<Long, Boolean>();
	
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

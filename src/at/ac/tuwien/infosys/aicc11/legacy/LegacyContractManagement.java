package at.ac.tuwien.infosys.aicc11.legacy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import at.ac.tuwien.infosys.aic11.CreditRequest;
import at.ac.tuwien.infosys.aic11.DisbursementPreference;
import at.ac.tuwien.infosys.aic11.InterestRate;
import at.ac.tuwien.infosys.aic11.Offer;
import at.ac.tuwien.infosys.aic11.Ratings;
import at.ac.tuwien.infosys.aic11.Warrantor;

public class LegacyContractManagement 
{
	private long nextOfferId = 1;
	private long nextRequestId = 1;
	
	private ConcurrentHashMap<Long, CreditRequest> requests = new ConcurrentHashMap<Long, CreditRequest>();
	private ConcurrentHashMap<Long, Offer> offers = new ConcurrentHashMap<Long, Offer>();
	
	private static LegacyContractManagement instance;
	
	public static LegacyContractManagement instance() {
	    if (instance == null)
		instance = new LegacyContractManagement();
	    return instance;
	}
	
	private LegacyContractManagement() {
	    
	}
	
	
	/**
	 * Generates an Id and creates an Offer for the request and returns the updated request
	 * @param creditRequest
	 * @return
	 * @throws LegacyException
	 */
	public synchronized Offer createCreditRequest(CreditRequest creditRequest)
	throws LegacyException
	{
		if (creditRequest.getRequestId() != 0)
		{
			throw new LegacyException("request should not have an Id yet");
		}
	
		long requestId = nextRequestId++;
		creditRequest.setRequestId(requestId);
		Offer offer = createOffer(creditRequest);
		offer.setRequest(creditRequest);
		
		requests.put(requestId, creditRequest);
		
		return offer;
	}
	
	/**
	 * Creates a new offer for the udpates request
	 * @param creditRequest
	 * @throws LegacyException
	 */
	public synchronized Offer updateCreditRequest(CreditRequest creditRequest)
	throws LegacyException
	{
		if (creditRequest.getRequestId() == 0)
		{
			throw new LegacyException("request needs to have an Id for updating");
		}
		
		if (!requests.containsKey(creditRequest.getRequestId()))
		{
			throw new LegacyException("request with Id " + creditRequest.getRequestId() + " does not exist");
		}
		
		Offer offer = createOffer(creditRequest);
		offer.setRequest(creditRequest);
		requests.put(creditRequest.getRequestId(), creditRequest);
		
		return offer;
	}
	
	// we could also pass the whole CreditRequest here, so that we can check if it actually exists...
	public synchronized void acceptOffer(Offer offer)
	throws LegacyException
	{
		if (offer.getOfferId() == 0)
		{
			throw new LegacyException("offer without an Id can't be accepted");
		}
		
		if (!offers.containsKey(offer.getOfferId()))
		{
			throw new LegacyException("offer with Id " + offer.getOfferId() + " does not exist");
		}

		// should we do anything here?
	}
	
	// we could also pass the whole CreditRequest here, so that we can check if it actually exists...
	public synchronized void deleteOffer(Offer offer)
	throws LegacyException
	{
		if (offer.getOfferId() == 0)
		{
			throw new LegacyException("offer without an Id can't be deleted");
		}
		
		if (!offers.containsKey(offer.getOfferId()))
		{
			throw new LegacyException("offer with Id " + offer.getOfferId() + " does not exist");
		}

		offers.remove(offer.getOfferId());
	}
	
	
	private synchronized Offer createOffer(CreditRequest creditRequest)
	{
		long offerId = nextOfferId++;
		Offer offer = new Offer();
		offer.setInterestRate(getInterestRate(creditRequest));
		offer.setComments("spend wisely!");
		offer.setOfferId(offerId);
		offer.setRequest(creditRequest);
		
		offers.put(offerId, offer);
		
		return offer;
	}
	
	/**
	 * Some fancy algorithm to determine the interest rate
	 * @param creditRequest
	 * @return
	 */
	private InterestRate getInterestRate(CreditRequest creditRequest)
	{
		int DEFAULT_RATE = 30;
		int DEFAULT_AMOUNT = 100000;
		int DEFAULT_DURATION = 10;
		
		double ratingsFactor = DEFAULT_RATE / sumRatings(creditRequest);
		double amountFactor = creditRequest.getAmount().getAmount() / DEFAULT_AMOUNT;
		double durationFactor = creditRequest.getDuration().getYears() / DEFAULT_DURATION;
		
		return new InterestRate( ( ratingsFactor + amountFactor + durationFactor) / 3);
	}

	/***
	 * Returns a numerical value indicating the credit-worthiness of the customer and his warrantors.
	 * The higher the value the better.
	 */
	private int sumRatings(CreditRequest creditRequest)
	{
		/* sort the ratings so that worst has index 0 */
		List<Ratings> ratings = Arrays.asList(Ratings.values());
		Collections.reverse(ratings);

		int sum = ratings.indexOf(creditRequest.getCustomer().getRating());
		
		for(Warrantor warrantor : creditRequest.getWarrantors())
		{
			sum += ratings.indexOf(warrantor.getRating());
		}
		
		return sum;
	}
	
	public void disburseMoney(Offer offer)
	throws LegacyException
	{
		if (offer == null)
		{
			throw new LegacyException("request does not have an offer");
		}

		if (offer.getOfferId() == 0)
		{
			throw new LegacyException("offer without an Id can't be disbursed");
		}
		
		if (!offers.containsKey(offer.getOfferId()))
		{
			throw new LegacyException("offer with Id " + offer.getOfferId() + " does not exist");
		}

		CreditRequest creditRequest = offer.getRequest();
		
		if (creditRequest.getRequestId() == 0)
		{
			throw new LegacyException("can't process request without Id");
		}
		
		if (!requests.containsKey(creditRequest.getRequestId()))
		{
			throw new LegacyException("request with Id " + creditRequest.getRequestId() + " does not exist");
		}
		
		DisbursementPreference disbursementPreference = creditRequest.getCustomer().getDisbursementPreference();
		// disburse money via disbursementPreference
	}
}

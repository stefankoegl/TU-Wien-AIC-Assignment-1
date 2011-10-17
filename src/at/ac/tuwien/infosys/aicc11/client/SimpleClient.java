package at.ac.tuwien.infosys.aicc11.client;

import java.util.Vector;

import at.ac.tuwien.infosys.aicc11.CreditRequest;
import at.ac.tuwien.infosys.aicc11.Offer;
import at.ac.tuwien.infosys.aicc11.Ratings;
import at.ac.tuwien.infosys.aicc11.services.ContractManagementImpl;
import at.ac.tuwien.infosys.aicc11.services.Rating;
import at.ac.tuwien.infosys.aicc11.services.RatingImpl;
import at.ac.tuwien.infosys.aicc11.Warrantor;
import at.ac.tuwien.infosys.aicc11.services.ContractManagement;

/* The following code should just illustrate the workflow
 * and is not a working client!
 * 
 * Still missing: ship contract, start disbursement, wait for fax, email notification
 */



public class SimpleClient {
	
	/* These represent the services -- the communication should happen 
	 * through the webservice interfaces of course 
	 */
	private static ContractManagement contractManagementService = new ContractManagementImpl();
	private static Rating ratingService = new RatingImpl();
	
	public static void main(String[] args)
	{
		CreditRequest creditRequest = getCreditRequest();
		
		/* TODO: supply some callback */
		contractManagementService.placeCreditRequest(creditRequest);
	}
	

	private static CreditRequest getCreditRequest() 
	{
		CreditRequest creditRequest = getRequestDataFromCustomer();
		
		for(Warrantor warrantor : creditRequest.getWarrantors())
		{
			Ratings rating = ratingService.getRating(warrantor.getCustomerId()); 
			warrantor.setRating(rating);
		}
		
		return creditRequest;
	}


	private static CreditRequest getRequestDataFromCustomer() {
		/* let user enter amount, etc */
		return new CreditRequest(0, new Vector<Warrantor>(), null, null, null, null, null);
	}
	
	private static void handleOffer(Offer offer)
	{
		int acceptDecision = getAcceptDecision(offer);
		
		if (acceptDecision == 0)
		{
			/* should ship contract / start disbursement be called directly ? */
			/* according to 
			 * http://vc.infosys.tuwien.ac.at/index.php?option=com_kunena&func=view&catid=25&id=31&Itemid=16
			 * services don't call each other, only the client calls services
			 */
			contractManagementService.acccceptOffer(offer);
		}
		else if (acceptDecision == 1)
		{
			/* update my credit request */
			
			CreditRequest creditRequest = getCreditRequest();
			contractManagementService.updateCreditRequest(creditRequest);
		}
		else if (acceptDecision == 2)
		{
			/* should this be called "delete offer"? */
			contractManagementService.declineOffer(offer);
		}
	}


	private static int getAcceptDecision(Offer offer) {
		// TODO Auto-generated method stub
		return 0;
	}
}

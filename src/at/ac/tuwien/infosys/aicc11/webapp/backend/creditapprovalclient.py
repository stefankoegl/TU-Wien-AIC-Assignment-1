from suds.client import Client
from webapp import settings

customer_client = Client(settings.WSDLS.get("customers"))
shipping_client = Client(settings.WSDLS.get("shipping"))
contract_client = Client(settings.WSDLS.get("contracts"))

#TODO: better error handling (if no result, throw an exception??)
#TODO: maybe fill out Rating in Customers already?

def getCustomerByID(customerId):
    return customer_client.service.getCustomerByID(customerId)
    
def getCustomerByName(name):
    return customer_client.service.getCustomerByName(name)

def acceptOffer(offer):
    #TODO: also call shipping & disbursement
    return contract_client.service.acceptOffer(offer)
    
def declineOffer(offer):
    return contract_client.service.declineOffer(offer)
    

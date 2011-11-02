from suds.client import Client
from suds.wsse import Security
from suds.wsse import UsernameToken
from webapp import settings

import disbursementclient

customer_client = Client(settings.WSDLS.get("customers"))
shipping_client = Client(settings.WSDLS.get("shipping"))
contract_client = Client(settings.WSDLS.get("contracts"))

security = Security()
token = UsernameToken('aic11', 'supersecret')
security.tokens.append(token)
contract_client.set_options(wsse=security)

#TODO: better error handling (if no result, throw an exception??)

def getCustomerByID(customerId):
    return customer_client.service.getCustomerByID(customerId)

def getCustomerByName(name):
    return customer_client.service.getCustomerByName(name)

def placeCreditRequest(request):
    return contract_client.service.placeCreditRequest(request)

def updateCreditRequest(request):
    return contract_client.service.updateCreditRequest(request)

def acceptOffer(offer):
    #TODO: also call shipping
    #TODO: do things in parallel
    request = offer.credit_request
    disbursementclient.startDisbursement(request.amount, request.customer)
    return contract_client.service.acceptOffer(offer)

def declineOffer(offer):
    return contract_client.service.declineOffer(offer)

#wrapping of Factory methods to make things easier
def createCreditRequest():
    return contract_client.factory.create("creditRequest")

def createMoney():
    return contract_client.factory.create("money")

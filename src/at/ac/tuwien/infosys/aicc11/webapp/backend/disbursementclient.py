#Client for Registry & Disbursement Service

from suds.client import Client

registryUrl = "http://stefan.derkits.at/files/registry_service.wsdl" #change to be served from JavaServer or from Local Django Server
client = Client(registryUrl)

def startDisbursement(preference, money, customer):
    #TODO: create correct disbursement preference
    s_pref = client.factory.create("cheque")
    s_pref.name = "Foo Bar"
    serviceUrl = getDisbursementServiceUrl(s_pref)
    client2 = Client(serviceUrl)
    #TODO: create correct customer & money
    s_customer = client2.factory.create("customer")
    s_money = client2.factory.create("money")
    address = client2.factory.create("address")
    address.city = "Vienna"
    address.countryCode = "AT"
    address.door = 10
    address.house = 23
    address.street = "Hauptstrasse"
    address.postalCode = 1010
    s_customer.customerId = 1
    s_customer.firstName = "Stefan"
    s_customer.lastName = "Derkits"
    s_customer.middleName = "X."
    s_customer.address = address
    s_money.amount = 10000
    s_money.currencyCode = "EUR"
    result = client2.service.start_money_transfer_process(s_pref,s_money,s_customer)
    print result
    
def getDisbursementServiceUrl(disbursementPreference):
    result = client.service.query(disbursementPreference)
    return result.location + "?wsdl"

#Client for Registry & Disbursement Service

from suds.client import Client

registryUrl = "http://stefan.derkits.at/files/registry_service.wsdl" #change to be served from JavaServer or from Local Django Server
client = Client(registryUrl)

def startDisbursement(money, customer):
    serviceUrl = getDisbursementServiceUrl(customer.disbursement_preference)
    client2 = Client(serviceUrl)
    result = client2.service.start_money_transfer_process(customer.disbursement_preference,money,customer)
    return result
    
def getDisbursementServiceUrl(disbursementPreference):
    result = client.service.query(disbursementPreference)
    return result.location + "?wsdl"

#Client for Registry & Disbursement Service

from suds.client import Client

registryUrl = "http://stefan.derkits.at/files/registry_service.wsdl" #change to be served from JavaServer or from Local Django Server
client = Client(registryUrl)

def startDisbursement(preference):
    #TODO: create correct disbursement preference
    pref = client.factory.create("cheque")
    pref.name = "Foo Bar"
    #TODO: do something correct with serviceUrl (atm no wsdl is accessible there)
    serviceUrl = getDisbursementServiceUrl(pref)
    print serviceUrl
    
def getDisbursementServiceUrl(disbursementPreference):
    result = client.service.query(disbursementPreference)
    return result.location

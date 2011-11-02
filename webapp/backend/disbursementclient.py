#Client for Registry & Disbursement Service

from suds.client import Client

registryUrl = "http://stefan.derkits.at/files/registry_service.wsdl" #change to be served from JavaServer or from Local Django Server
client = Client(registryUrl)

def startDisbursement(money, customer):
    disbursement_pref = get_disbursement_pref(customer)
    serviceUrl = getDisbursementServiceUrl(disbursement_pref)
    client2 = Client(serviceUrl)
    result = client2.service.start_money_transfer_process(disbursement_pref, money, customer)
    return result

def getDisbursementServiceUrl(disbursementPreference):
    result = client.service.query(disbursementPreference)
    return result.location + "?wsdl"


def get_disbursement_pref(customer):
    if customer.disbursement_preference.__class__.__name__ == 'cheque':
        cheque = client.factory.create('cheque')
        cheque.name = customer.disbursement_preference.name
        return cheque

    else:
        bt = client.factory.create('bank_transfer')
        bt.bankName = customer.disbursement_preference.bank_name
        bt.bic = customer.disbursement_preference.bic
        bt.iban = customer.disbursement_preference.iban
        return bt

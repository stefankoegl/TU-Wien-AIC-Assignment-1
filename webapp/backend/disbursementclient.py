#Client for Registry & Disbursement Service

from suds.client import Client

registryUrl = "http://stefan.derkits.at/files/registry_service.wsdl" #change to be served from JavaServer or from Local Django Server
client = Client(registryUrl)

def startDisbursement(money, customer):
    disbursement_pref = get_disbursement_pref(customer)

    serviceUrl = getDisbursementServiceUrl(disbursement_pref)
    client2 = Client(serviceUrl)

    money = get_money(client2, money)
    customer = get_customer(client2, customer)

    print customer

    result = client2.service.start_money_transfer_process(disbursement_pref,
            money, customer)
    return result

def getDisbursementServiceUrl(disbursementPreference):
    result = client.service.query(disbursementPreference)
    return result.location + "?wsdl"


# It seems the disbursement services uses different xml names than the rest
# of the application. We therefore need to re-map all the data that is passed
# to the services

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


def get_money(client, money):
    m = client.factory.create('money')
    m.amount = money.amount
    m.currencyCode = money.currency_code
    return m


def get_customer(client, customer):
    c = client.factory.create('customer')
    c.address.city = customer.address.city
    c.address.countryCode = ''
    c.address.door = customer.address.door
    c.address.house = customer.address.house
    c.address.postalCode = customer.address.zip_code
    c.address.street = customer.address.street
    c.customerId = customer._customer_id
    c.firstName = customer.first_name
    c.lastName = customer.last_name
    c.middleName = customer.middle_name
    return c

from webapp.backend.creditapprovalclient import customer_client, \
         contract_client


def customer_to_dict(customer):
    dic = {}
    dic['id'] = customer._customer_id
    dic['city'] = customer.address.city
    dic['door'] = customer.address.door
    dic['house'] = customer.address.house
    dic['street'] = customer.address.street
    dic['zip'] = customer.address.zip_code
    dic['first_name'] = customer.first_name
    dic['last_name'] = customer.last_name.encode('utf-8')
    dic['middle_name'] = customer.middle_name
    dic['open_balance'] = customer.open_balance
    if customer.rating:
        dic['rating'] = customer.rating.value
    else:
        dic['rating'] = None

    if customer.disbursement_preference.__class__.__name__ == 'cheque':
        dic['disbursement_pref'] = 'cheque'
        dic['cheque_name'] = customer.disbursement_preference.name
    else:
        dic['disbursement_pref'] = 'bank_transfer'
        dic['bank_name'] = customer.disbursement_preference.bank_name
        dic['bic'] = customer.disbursement_preference.bic
        dic['iban'] = customer.disbursement_preference.iban

    return dic



def customer_from_dict(dic):
    customer = customer_client.factory.create('customer')
    customer._customer_id = dic.get('id', None)
    customer.address.city = dic.get('city', None)
    customer.address.door = dic.get('door', None)
    customer.address.house = dic.get('house', None)
    customer.address.street = dic.get('street', None)
    customer.address.zip_code = dic.get('zip', None)
    customer.first_name = dic.get('first_name', None)
    customer.last_name = dic.get('last_name', None)
    customer.middle_name = dic.get('middle_name', None)
    customer.open_balance = dic.get('open_balance', None)
    customer.rating.value = dic.get('rating', None)

    if dic['disbursement_pref'] == 'cheque':
        customer.disbursement_preference = \
                customer_client.factory.create('cheque')
        customer.disbursement_preference.name = dic['cheque_name']
    else:
        customer.disbursement_preference = \
                customer_client.factory.create('bank_transfer')
        customer.disbursement_preference.bank_name = dic['bank_name']
        customer.disbursement_preference.bic = dic['bic']
        customer.disbursement_preference.iban = dic['iban']

    return customer



def request_to_dict(request):
    dic = {}
    dic['id'] = request._request_id
    dic['amount'] = request.amount.amount
    dic['currency'] = request.amount.currency_code
    dic['customer'] = customer_to_dict(request.customer)
    dic['duration'] = request.duration.years
    dic['offer'] = offer_to_dict(request.offer)
    dic['reason'] = request.reason
    dic['warrantors'] = map(customer_to_dict, request.warrantors)
    return dic



def request_from_dict(dic):
    request = customer_client.factory.create('credit_request')
    request._request_id = dic['id']
    request.amount.amount = dic['amount']
    request.amount.currency_code = dic['currency']
    request.customer = customer_from_dict(dic['customer'])
    request.duration.years = dic['duration']
    request.offer = offer_from_dict(dic['offer'])
    request.reason = dic['reason']
    request.warrantors = map(customer_from_dict, dic['warrantors'])
    return request



def offer_to_dict(offer):
    dic = {}
    dic['id'] = offer._offer_id
    dic['comments'] = offer.comments
    dic['rate'] = offer.interest_rate.rate
    return dic



def offer_from_dict(dic):
    offer = customer_client.factory.create('offer')
    offer._offer_id = dic['id']
    offer.comments = dic['comments']
    offer.interest_rate.rate = dic['rate']
    return offer



def set_customer(request, customer):
    request.session['customer'] = customer_to_dict(customer)


def get_customer(request):
    return customer_from_dict(request.session['customer'])


def set_request(request, credit_req):
    request.session['request'] = request_to_dict(credit_req)

def get_request(request):
    return request_from_dict(request.session['request'])

def set_offer(request, offer):
    request.session['offer'] = offer_to_dict(offer)

def get_offer(request):
    return offer_from_dict(request.session['offer'])



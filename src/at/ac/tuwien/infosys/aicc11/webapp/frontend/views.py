from django.shortcuts import render_to_response
from django.template import RequestContext

from webapp.backend import creditapprovalclient, ratingclient
from webapp.frontend import sessionstore


def index(request):
    return render_to_response('index.html', {
        }, context_instance=RequestContext(request))


def customers(request):
    name = request.POST['name']

    customer = creditapprovalclient.getCustomerByName(name)

    sessionstore.set_customer(request, customer)

    return render_to_response('customers.html', {
            'name': name,
            'customer': customer,
        }, context_instance=RequestContext(request))


def enter_request(request):
    return render_to_response('enter-request.html', {
        }, context_instance=RequestContext(request))


def create_request(request):
    credit_req = creditapprovalclient.createCreditRequest()

    credit_req.amount.amount = request.POST['amount']
    credit_req.amount.currency_code = request.POST['currency']
    credit_req.customer = sessionstore.get_customer(request)
    credit_req.duration.years = request.POST['duration']
    credit_req.reason = request.POST['reason']

    sessionstore.set_request(request, credit_req)

    return render_to_response('search-warrantors.html', {
            'credit_request': credit_req,
        }, context_instance=RequestContext(request))


def show_warrantor(request):
    name = request.POST['name']
    warrantor = creditapprovalclient.getCustomerByName(name)

    return render_to_response('show-warrantor.html', {
            'name': name,
            'warrantor': warrantor,
            'id': warrantor._customer_id,
        }, context_instance=RequestContext(request))


def add_warrantor(request):

    credit_req = sessionstore.get_request(request)
    warrantor = creditapprovalclient.getCustomerByID(int(request.GET['id']))

    credit_req.warrantors.append(warrantor)

    return render_to_response('search-warrantors.html', {
            'credit_request': credit_req,
        }, context_instance=RequestContext(request))


def submit_request(request):

    credit_req = sessionstore.get_request(request)

    customer = credit_req.customer
    credit_req.customer = ratingclient.setRating(customer)
    credit_req.warrantors = map(ratingclient.setRating, credit_req.warrantors)

    offer = creditapprovalclient.placeCreditRequest(credit_req)

    sessionstore.set_offer(request, offer)

    return render_to_response('offer.html', {
            'id': offer._offer_id,
            'offer': offer,
        }, context_instance=RequestContext(request))



def accept_offer(request):

    offer = sessionstore.get_offer(request)
    credit_req = sessionstore.get_request(request)

    offer.credit_request = credit_req

    x = creditapprovalclient.acceptOffer(offer)

    print x



def decline_offer(request):

    offer = sessionstore.get_offer(request)
    credit_req = sessionstore.get_request(request)

    offer.credit_request = credit_req

    x = creditapprovalclient.declineOffer(offer)

    print x


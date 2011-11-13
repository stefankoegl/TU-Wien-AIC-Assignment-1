from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponseRedirect
from django.core.urlresolvers import reverse
from django.contrib import messages

from webapp.backend import creditapprovalclient, ratingclient
from webapp.frontend import sessionstore

import logging
logging.basicConfig(level=logging.INFO)
logging.getLogger('suds.transport').setLevel(logging.DEBUG)

def index(request):

    sessionstore.set_customer(request, None)
    sessionstore.set_offer(request, None)
    sessionstore.set_request(request, None)

    return render_to_response('index.html', {
        }, context_instance=RequestContext(request))


def enter_request(request):

    name = request.POST['name']

    try:
        customer = creditapprovalclient.getCustomerByName(name)
        customer = ratingclient.setRating(customer)
        sessionstore.set_customer(request, customer)

    except Exception as e:
        messages.error(request, e)
        return HttpResponseRedirect(request.META['HTTP_REFERER'])

    return render_to_response('enter-request.html', {
            'customer': customer,
            'target': 'create-request',
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

    credit_req = sessionstore.get_request(request)

    try:
        warrantor = creditapprovalclient.getCustomerByName(name)
    except:
        messages.error(request, 'Customer with name "{name}" not found'.format(
            name=name))
        warrantor = None

    if warrantor:
        warrantor = ratingclient.setRating(warrantor)
        credit_req.warrantors.append(warrantor)

    sessionstore.set_request(request, credit_req)

    return render_to_response('search-warrantors.html', {
            'credit_request': credit_req,
        }, context_instance=RequestContext(request))


def submit_request(request):

    credit_req = sessionstore.get_request(request)

    offer = creditapprovalclient.placeCreditRequest(credit_req)

    if not getattr(offer, 'warrantors', False):
        offer.request.warrantors = []

    sessionstore.set_offer(request, offer)

    return render_to_response('offer.html', {
            'id': offer._offer_id,
            'offer': offer,
            'credit_request': credit_req,
        }, context_instance=RequestContext(request))



def accept_offer(request):

    offer = sessionstore.get_offer(request)
    credit_req = sessionstore.get_request(request)

    offer.request = credit_req

    creditapprovalclient.acceptOffer(offer)

    sessionstore.set_offer(request, offer)

    return HttpResponseRedirect(reverse(offer_status))



def offer_status(request):

    offer = sessionstore.get_offer(request)

    is_signed = creditapprovalclient.contractSigned(offer)

    return render_to_response('offer-status.html', {
            'id': offer._offer_id,
            'offer': offer,
            'is_signed': is_signed,
        }, context_instance=RequestContext(request))





def decline_offer(request):

    offer = sessionstore.get_offer(request)
    credit_req = sessionstore.get_request(request)

    offer.request = credit_req

    x = creditapprovalclient.declineOffer(offer)

    return render_to_response('offer-declined.html', {
            'id': offer._offer_id,
            'offer': offer,
        }, context_instance=RequestContext(request))



def edit_request(request):

    offer = sessionstore.get_offer(request)
    credit_req = offer.request

    return render_to_response('enter-request.html', {
            'customer': offer.request.customer,
            'credit_request': credit_req,
            'target': 'update-request',
        }, context_instance=RequestContext(request))


def update_request(request):
    credit_req = sessionstore.get_request(request)

    credit_req.amount.amount = request.POST['amount']
    credit_req.amount.currency_code = request.POST['currency']
    credit_req.duration.years = request.POST['duration']
    credit_req.reason = request.POST['reason']

    sessionstore.set_request(request, credit_req)

    offer = creditapprovalclient.placeCreditRequest(credit_req)

    if not getattr(offer, 'warrantors', False):
        offer.request.warrantors = []

    sessionstore.set_offer(request, offer)

    return render_to_response('offer.html', {
            'id': offer._offer_id,
            'offer': offer,
        }, context_instance=RequestContext(request))

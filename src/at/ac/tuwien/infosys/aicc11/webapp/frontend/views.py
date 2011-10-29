from django.shortcuts import render_to_response
from django.template import RequestContext

from webapp.backend import creditapprovalclient


def index(request):
    return render_to_response('index.html', {
        }, context_instance=RequestContext(request))


def customers(request):
    name = request.POST['name']

    customer = creditapprovalclient.getCustomerByName(name)

    return render_to_response('customers.html', {
            'name': name,
            'customer': customer,
        }, context_instance=RequestContext(request))

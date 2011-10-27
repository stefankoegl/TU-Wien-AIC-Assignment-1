from httplib import HTTPConnection
import json

from webapp import settings

def setRating(customer):
    conn = HTTPConnection(settings.SERVER)
    url = "/rating/%s" % customer._customer_id
    conn.request("GET", url)
    res = conn.getresponse()
    data = res.read()
    customer.rating = json.loads(data).get("rating")
    return customer
    
    
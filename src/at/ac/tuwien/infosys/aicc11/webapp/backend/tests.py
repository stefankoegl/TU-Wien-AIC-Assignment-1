"""
This file demonstrates writing tests using the unittest module. These will pass
when you run "manage.py test".

Replace this with more appropriate tests for your application.
"""

from django.test import TestCase

import disbursementclient


class SimpleTest(TestCase):
    def test_basic_addition(self):
        """
        Tests that 1 + 1 always equals 2.
        """
        self.assertEqual(1 + 1, 2)

class DisbursementClientTest(TestCase):
    def test_getDibursementServiceUrl_cheque(self):
        ch = disbursementclient.client.factory.create("cheque")
        ch.name = "foo bar"
        url = disbursementclient.getDisbursementServiceUrl(ch)
        self.assertEqual(url, "http://vc.infosys.tuwien.ac.at:8091/cheque")
        
    def test_getDibursementServiceUrl_bankTransfer(self):
        bank = disbursementclient.client.factory.create("bankTransfer")
        bank.bankName = "Foo Bank"
        bank.iban = 1234567890
        bank.bic = 4567
        url = disbursementclient.getDisbursementServiceUrl(bank)
        self.assertEqual(url, "http://vc.infosys.tuwien.ac.at:8090/banking")

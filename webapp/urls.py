from django.conf.urls.defaults import patterns, include, url

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('webapp.frontend.views',
    # Examples:
    url(r'^$',                  'index', name='index'),
    url(r'^enter-request$',     'enter_request'),
    url(r'^create-request$',    'create_request'),
    url(r'^show-warrantor$',    'show_warrantor'),

    url(r'^submit-request$',    'submit_request'),
    url(r'^accept-offer$',      'accept_offer'),
    url(r'^decline-offer$',     'decline_offer'),

    url(r'^edit-request$',      'edit_request'),
    url(r'^update-request$',    'update_request'),
    url(r'^status',             'offer_status'),

    # url(r'^creditapprovalwebapp/', include('creditapprovalwebapp.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    # url(r'^admin/', include(admin.site.urls)),
)

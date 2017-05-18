/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.usage;

import com.google.common.base.Joiner;
import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.InvalidRequestException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class TriggerReader extends Reader<Trigger> {
    private String pathAccountSid;
    private Trigger.Recurring recurring;
    private Trigger.TriggerField triggerBy;
    private Trigger.UsageCategory usageCategory;

    /**
     * Construct a new TriggerReader.
     */
    public TriggerReader() {
    }

    /**
     * Construct a new TriggerReader.
     * 
     * @param pathAccountSid The account_sid
     */
    public TriggerReader(final String pathAccountSid) {
        this.pathAccountSid = pathAccountSid;
    }

    /**
     * Only show UsageTriggers that count over this interval. One of daily, monthly,
     * or yearly.
     * 
     * @param recurring Filter by recurring
     * @return this
     */
    public TriggerReader setRecurring(final Trigger.Recurring recurring) {
        this.recurring = recurring;
        return this;
    }

    /**
     * Only show UsageTriggers that trigger by this field in the UsagRecord.
     * 
     * @param triggerBy Filter by trigger by
     * @return this
     */
    public TriggerReader setTriggerBy(final Trigger.TriggerField triggerBy) {
        this.triggerBy = triggerBy;
        return this;
    }

    /**
     * Only show UsageTriggers that watch this usage category.
     * 
     * @param usageCategory Filter by Usage Category
     * @return this
     */
    public TriggerReader setUsageCategory(final Trigger.UsageCategory usageCategory) {
        this.usageCategory = usageCategory;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Trigger ResourceSet
     */
    @Override
    public ResourceSet<Trigger> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Trigger ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Trigger> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Usage/Triggers.json",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     * 
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Target Page
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Trigger> getPage(final String targetUrl, final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        String resourceUrl = "https://"
                           + Joiner.on(".").skipNulls().join(
                                Domains.API.toString(),
                                client.getRegion(),
                                "twilio",
                                "com")
                           + "/2010-04-01/Accounts/" + this.pathAccountSid + "/Usage/Triggers.json";
        if (!targetUrl.startsWith(resourceUrl)) {
            throw new InvalidRequestException("Invalid targetUrl for Trigger resource.");
        }

        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     * 
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Trigger> nextPage(final Page<Trigger> page, 
                                  final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     * 
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Trigger> previousPage(final Page<Trigger> page, 
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Trigger Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Trigger> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Trigger read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Page.fromJson(
            "usage_triggers",
            response.getContent(),
            Trigger.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (recurring != null) {
            request.addQueryParam("Recurring", recurring.toString());
        }

        if (triggerBy != null) {
            request.addQueryParam("TriggerBy", triggerBy.toString());
        }

        if (usageCategory != null) {
            request.addQueryParam("UsageCategory", usageCategory.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
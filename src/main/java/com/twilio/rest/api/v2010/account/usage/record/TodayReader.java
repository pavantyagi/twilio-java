/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.usage.record;

import com.google.common.base.Joiner;
import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.InvalidRequestException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.LocalDate;

public class TodayReader extends Reader<Today> {
    private String pathAccountSid;
    private Today.Category category;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Construct a new TodayReader.
     */
    public TodayReader() {
    }

    /**
     * Construct a new TodayReader.
     * 
     * @param pathAccountSid The account_sid
     */
    public TodayReader(final String pathAccountSid) {
        this.pathAccountSid = pathAccountSid;
    }

    /**
     * The category.
     * 
     * @param category The category
     * @return this
     */
    public TodayReader setCategory(final Today.Category category) {
        this.category = category;
        return this;
    }

    /**
     * The start_date.
     * 
     * @param startDate The start_date
     * @return this
     */
    public TodayReader setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * The end_date.
     * 
     * @param endDate The end_date
     * @return this
     */
    public TodayReader setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Today ResourceSet
     */
    @Override
    public ResourceSet<Today> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Today ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Today> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Usage/Records/Today.json",
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
    public Page<Today> getPage(final String targetUrl, final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        String resourceUrl = "https://"
                           + Joiner.on(".").skipNulls().join(
                                Domains.API.toString(),
                                client.getRegion(),
                                "twilio",
                                "com")
                           + "/2010-04-01/Accounts/" + this.pathAccountSid + "/Usage/Records/Today.json";
        if (!targetUrl.startsWith(resourceUrl)) {
            throw new InvalidRequestException("Invalid targetUrl for Today resource.");
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
    public Page<Today> nextPage(final Page<Today> page, 
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
    public Page<Today> previousPage(final Page<Today> page, 
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
     * Generate a Page of Today Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Today> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Today read failed: Unable to connect to server");
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
            "usage_records",
            response.getContent(),
            Today.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (category != null) {
            request.addQueryParam("Category", category.toString());
        }

        if (startDate != null) {
            request.addQueryParam("StartDate", DateConverter.dateStringFromLocalDate(startDate));
        }

        if (endDate != null) {
            request.addQueryParam("EndDate", DateConverter.dateStringFromLocalDate(endDate));
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
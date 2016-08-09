/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.resource.taskrouter.v1.workspace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.rest.converter.DateConverter;
import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.fetcher.taskrouter.v1.workspace.TaskChannelFetcher;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.reader.taskrouter.v1.workspace.TaskChannelReader;
import com.twilio.rest.resource.Resource;
import com.twilio.rest.resource.RestException;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskChannel extends Resource {
    private static final long serialVersionUID = 123017740799311L;

    /**
     * Create a TaskChannelFetcher to execute fetch.
     * 
     * @param workspaceSid The workspace_sid
     * @param sid The sid
     * @return TaskChannelFetcher capable of executing the fetch
     */
    public static TaskChannelFetcher fetch(final String workspaceSid, 
                                           final String sid) {
        return new TaskChannelFetcher(workspaceSid, sid);
    }

    /**
     * Create a TaskChannelReader to execute read.
     * 
     * @param workspaceSid The workspace_sid
     * @return TaskChannelReader capable of executing the read
     */
    public static TaskChannelReader read(final String workspaceSid) {
        return new TaskChannelReader(workspaceSid);
    }

    /**
     * Converts a JSON String into a TaskChannel object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return TaskChannel object represented by the provided JSON
     */
    public static TaskChannel fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskChannel.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a TaskChannel object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return TaskChannel object represented by the provided JSON
     */
    public static TaskChannel fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, TaskChannel.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String friendlyName;
    private final String sid;
    private final String uniqueName;
    private final String workspaceSid;
    private final URI url;

    @JsonCreator
    private TaskChannel(@JsonProperty("account_sid")
                        final String accountSid, 
                        @JsonProperty("date_created")
                        final String dateCreated, 
                        @JsonProperty("date_updated")
                        final String dateUpdated, 
                        @JsonProperty("friendly_name")
                        final String friendlyName, 
                        @JsonProperty("sid")
                        final String sid, 
                        @JsonProperty("unique_name")
                        final String uniqueName, 
                        @JsonProperty("workspace_sid")
                        final String workspaceSid, 
                        @JsonProperty("url")
                        final URI url) {
        this.accountSid = accountSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.friendlyName = friendlyName;
        this.sid = sid;
        this.uniqueName = uniqueName;
        this.workspaceSid = workspaceSid;
        this.url = url;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The date_created.
     * 
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The date_updated.
     * 
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The friendly_name.
     * 
     * @return The friendly_name
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The unique_name.
     * 
     * @return The unique_name
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The The workspace_sid.
     * 
     * @return The workspace_sid
     */
    public final String getWorkspaceSid() {
        return this.workspaceSid;
    }

    /**
     * Returns The The url.
     * 
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        TaskChannel other = (TaskChannel) o;
        
        return Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(sid, other.sid) && 
               Objects.equals(uniqueName, other.uniqueName) && 
               Objects.equals(workspaceSid, other.workspaceSid) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            dateCreated,
                            dateUpdated,
                            friendlyName,
                            sid,
                            uniqueName,
                            workspaceSid,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("friendlyName", friendlyName)
                          .add("sid", sid)
                          .add("uniqueName", uniqueName)
                          .add("workspaceSid", workspaceSid)
                          .add("url", url)
                          .toString();
    }
}
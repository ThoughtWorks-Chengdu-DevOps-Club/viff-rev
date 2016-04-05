package io.viff.sdk.request;

import javax.ws.rs.QueryParam;


public class CompareRequest {
    @QueryParam("from")
    private String from;

    @QueryParam("to")
    private String to;

    public CompareRequest() {
    }

    public CompareRequest(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

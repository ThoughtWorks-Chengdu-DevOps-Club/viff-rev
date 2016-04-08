package io.viff.sdk.request;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;


public class CompareRequest {
    @NotNull
    @QueryParam("from")
    private String from;

    @NotNull
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

    public String getTo() {
        return to;
    }

}

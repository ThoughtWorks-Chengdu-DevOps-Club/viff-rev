package io.viff.comparator.domain;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;


public class CompareRequest {
    @NotNull
    @QueryParam("from")
    private String from;

    @NotNull
    @QueryParam("to")
    private String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

}

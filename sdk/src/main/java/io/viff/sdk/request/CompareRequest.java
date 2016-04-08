package io.viff.sdk.request;

public class CompareRequest {

    private String from;

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

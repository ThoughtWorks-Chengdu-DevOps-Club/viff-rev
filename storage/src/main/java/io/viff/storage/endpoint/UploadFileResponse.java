package io.viff.storage.endpoint;

/**
 * Created by tbzhang on 3/7/16.
 */
public class UploadFileResponse {
    private String url;
    private String tag;
    private String buildNumber;


    public UploadFileResponse() {
    }

    public UploadFileResponse(String url, String tag, String buildNumber) {
        this.url = url;
        this.tag = tag;
        this.buildNumber = buildNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }


}

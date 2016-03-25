package io.viff.storage.endpoint;

/**
 * Created by tbzhang on 3/7/16.
 */
public class UploadFileResponse {
    private String url;
    private String project;
    private String tag;
    private Integer buildNumber;



    public UploadFileResponse() {
    }

    public UploadFileResponse(String url, String tag, Integer buildNumber) {
        this.url = url;
        this.tag = tag;
        this.buildNumber = buildNumber;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Integer buildNumber) {
        this.buildNumber = buildNumber;
    }


}

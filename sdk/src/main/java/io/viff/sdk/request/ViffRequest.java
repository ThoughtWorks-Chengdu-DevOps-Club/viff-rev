package io.viff.sdk.request;


public class ViffRequest {

    private String projectID;
    private String tagName;
    private Integer buildNumber;
    private String targetTagName;
    private Integer targetBuildNumber;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Integer buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getTargetTagName() {
        return targetTagName;
    }

    public void setTargetTagName(String targetTagName) {
        this.targetTagName = targetTagName;
    }

    public Integer getTargetBuildNumber() {
        return targetBuildNumber;
    }

    public void setTargetBuildNumber(Integer targetBuildNumber) {
        this.targetBuildNumber = targetBuildNumber;
    }
}

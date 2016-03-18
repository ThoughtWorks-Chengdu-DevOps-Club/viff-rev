package io.viff.storage.endpoint;


public class ViffRequest {

    private String projectID;
    private String tagName;
    private String buildNumber;
    private String targetProjectID;
    private String targetTagName;
    private String targetBuildNumber;

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

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getTargetProjectID() {
        return targetProjectID;
    }

    public void setTargetProjectID(String targetProjectID) {
        this.targetProjectID = targetProjectID;
    }

    public String getTargetTagName() {
        return targetTagName;
    }

    public void setTargetTagName(String targetTagName) {
        this.targetTagName = targetTagName;
    }

    public String getTargetBuildNumber() {
        return targetBuildNumber;
    }

    public void setTargetBuildNumber(String targetBuildNumber) {
        this.targetBuildNumber = targetBuildNumber;
    }
}

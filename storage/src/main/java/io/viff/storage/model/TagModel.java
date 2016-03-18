package io.viff.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity(name = "tag")
public class TagModel extends BaseModel {

    @Column(name = "project_id")
    private Long projectID;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "latest_build_id")
    private Long latestBuildID;

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getLatestBuildID() {
        return latestBuildID;
    }

    public void setLatestBuildID(Long latestBuildID) {
        this.latestBuildID = latestBuildID;
    }
}

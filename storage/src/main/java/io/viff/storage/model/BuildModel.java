package io.viff.storage.model;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BuildModel extends BaseModel {

    @Column(name = "tag_id")
    private Long tagID;

    @Column(name = "build_number")
    private Integer buildNumber;

    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Integer buildNumber) {
        this.buildNumber = buildNumber;
    }
}

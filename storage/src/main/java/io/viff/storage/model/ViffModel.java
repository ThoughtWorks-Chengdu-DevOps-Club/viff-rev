package io.viff.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ViffModel extends BaseModel {
    @Column(name = "project_id")
    private Long projectID;

    @Column(name = "origin_tag_id")
    private Long originTagID;

    @Column(name = "origin_build_id")
    private Long originBuildID;

    @Column(name = "target_tag_id")
    private Long targetTagID;

    @Column(name = "target_build_id")
    private Long targetBuildID;

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public Long getOriginTagID() {
        return originTagID;
    }

    public void setOriginTagID(Long originTagID) {
        this.originTagID = originTagID;
    }

    public Long getOriginBuildID() {
        return originBuildID;
    }

    public void setOriginBuildID(Long originBuildID) {
        this.originBuildID = originBuildID;
    }

    public Long getTargetTagID() {
        return targetTagID;
    }

    public void setTargetTagID(Long targetTagID) {
        this.targetTagID = targetTagID;
    }

    public Long getTargetBuildID() {
        return targetBuildID;
    }

    public void setTargetBuildID(Long targetBuildID) {
        this.targetBuildID = targetBuildID;
    }
}

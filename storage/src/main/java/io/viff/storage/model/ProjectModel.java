package io.viff.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by tbzhang on 3/10/16.
 */

@Entity
public class ProjectModel extends BaseModel {

    @Column(name = "project_id")
    private String projectID;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}

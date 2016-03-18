package io.viff.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "viff_file_item")
public class ViffFileItemModel extends BaseModel {
    @Column(name = "origin_file_id")
    private Long originFileID;

    @Column(name = "target_file_id")
    private Long targetFileID;

    @Column(name = "viff_file_path")
    private String viffFilePath;

    public Long getOriginFileID() {
        return originFileID;
    }

    public void setOriginFileID(Long originFileID) {
        this.originFileID = originFileID;
    }

    public Long getTargetFileID() {
        return targetFileID;
    }

    public void setTargetFileID(Long targetFileID) {
        this.targetFileID = targetFileID;
    }

    public String getViffFilePath() {
        return viffFilePath;
    }

    public void setViffFilePath(String viffFilePath) {
        this.viffFilePath = viffFilePath;
    }
}

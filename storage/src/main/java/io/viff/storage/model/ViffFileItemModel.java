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

    @Column(name = "is_same")
    private Boolean isSame;

    @Column(name = "similarity")
    private Double similarity;

    @Column(name = "viff_id")
    private Long viffID;

    public Boolean getSame() {
        return isSame;
    }

    public void setSame(Boolean same) {
        isSame = same;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public Long getViffID() {
        return viffID;
    }

    public void setViffID(Long viffID) {
        this.viffID = viffID;
    }

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

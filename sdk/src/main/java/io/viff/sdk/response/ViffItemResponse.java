package io.viff.sdk.response;


public class ViffItemResponse {
    private String imageID;
    private boolean isNew;
    private boolean isSame;
    private boolean isRemoved;
    private double similarity;
    private String originImageURL;
    private String targetImageURL;
    private String viffImageURL;


    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public String getOriginImageURL() {
        return originImageURL;
    }

    public void setOriginImageURL(String originImageURL) {
        this.originImageURL = originImageURL;
    }

    public String getTargetImageURL() {
        return targetImageURL;
    }

    public void setTargetImageURL(String targetImageURL) {
        this.targetImageURL = targetImageURL;
    }

    public String getViffImageURL() {
        return viffImageURL;
    }

    public void setViffImageURL(String viffImageURL) {
        this.viffImageURL = viffImageURL;
    }
}

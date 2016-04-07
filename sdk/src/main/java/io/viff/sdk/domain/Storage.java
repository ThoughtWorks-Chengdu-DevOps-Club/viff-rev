package io.viff.sdk.domain;

/**
 * Created by tbzhang on 4/7/16.
 */
public class Storage {
    private String internalAccessPath;
    private String externalAccessPath;

    public Storage() {
    }

    public Storage(Storable storable) {
        this.internalAccessPath = storable.getInternalAccessiblePath();
        this.externalAccessPath = storable.getExternalAccessiblePath();
    }

    public String getInternalAccessPath() {
        return internalAccessPath;
    }

    public void setInternalAccessPath(String internalAccessPath) {
        this.internalAccessPath = internalAccessPath;
    }

    public String getExternalAccessPath() {
        return externalAccessPath;
    }

    public void setExternalAccessPath(String externalAccessPath) {
        this.externalAccessPath = externalAccessPath;
    }
}

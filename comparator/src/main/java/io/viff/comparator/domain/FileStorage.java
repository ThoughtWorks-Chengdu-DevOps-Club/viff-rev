package io.viff.comparator.domain;

import java.io.File;

/**
 * Created by tbzhang on 2/25/16.
 */
public class FileStorage implements Storable {

    private String filePath;


    @Override
    public void Store(Object o) {
    }

    @Override
    public String getInternalAccessiblePath() {
        return new File(filePath).getAbsolutePath();
    }

    @Override
    public String getExternalAccessiblePath() {
        return new File(filePath).getAbsolutePath();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

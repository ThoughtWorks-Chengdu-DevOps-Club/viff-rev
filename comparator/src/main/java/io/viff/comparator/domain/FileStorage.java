package io.viff.comparator.domain;

import java.io.File;

/**
 * Created by tbzhang on 2/25/16.
 */
public class FileStorage implements Storable {

    private File file;

    public FileStorage(File file) {
        this.file = file;
    }

    @Override
    public void Store(Object o) {
    }

    @Override
    public String getInternalAccessiblePath() {
        return file.getAbsolutePath();
    }

    @Override
    public String getExternalAccessiblePath() {
        return file.getAbsolutePath();
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "internal path=" + getInternalAccessiblePath() + ", " +
                "external path=" + getExternalAccessiblePath() +
                '}';
    }
}

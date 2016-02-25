package io.viff.comparator.domain;

/**
 * Created by tbzhang on 2/25/16.
 */
public interface Storable {
    void Store(Object o);

    String getInternalAccessiblePath();

    String getExternalAccessiblePath();
}

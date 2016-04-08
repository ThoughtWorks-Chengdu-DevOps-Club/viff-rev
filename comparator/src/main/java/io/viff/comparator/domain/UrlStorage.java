package io.viff.comparator.domain;

import io.viff.sdk.domain.Storable;

public class UrlStorage implements Storable {
    private String url;

    public UrlStorage(String url) {
        this.url = url;
    }

    @Override
    public String getInternalAccessiblePath() {
        return url;
    }

    @Override
    public String getExternalAccessiblePath() {
        return url;
    }
}

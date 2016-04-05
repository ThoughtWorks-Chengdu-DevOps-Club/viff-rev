package io.viff.comparator.domain;


import io.viff.sdk.domain.Storable;

public interface StorageAdapter {
    Storable store(Storable source);
}

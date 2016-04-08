package io.viff.comparator.service;

import io.viff.sdk.response.CompareResult;
import io.viff.sdk.domain.Storable;


public interface Comparator {
    CompareResult compare(Storable x, Storable y);
}

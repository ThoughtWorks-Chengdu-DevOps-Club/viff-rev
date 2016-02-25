package io.viff.comparator.service;

import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.Storable;

/**
 * Created by tbzhang on 2/24/16.
 */
public interface FileComparator {
    CompareResult compare(Storable x, Storable y);
}

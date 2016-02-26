package io.viff.comparator.service;

import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.Point;
import io.viff.comparator.domain.Storable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


public interface Comparator {
    CompareResult compare(Storable x, Storable y);
    Storable renderDiffImage(BufferedImage originImage, List<Point> points) throws IOException;
}

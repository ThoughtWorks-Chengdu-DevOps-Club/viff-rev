package io.viff.comparator.service;

import io.viff.comparator.domain.Point;
import io.viff.sdk.domain.Storable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface DiffImageRenderer {

    Storable render(String fileName, BufferedImage originImage, List<Point> points, int defaultDiffRGB) throws IOException;
}

package io.viff.comparator.service;

import io.viff.comparator.domain.Point;
import io.viff.sdk.domain.Storable;
import io.viff.sdk.domain.Storage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface DiffImageRenderer {

    Storage render(String fileName, BufferedImage originImage, List<Point> points, int defaultDiffRGB) throws IOException;
}

package io.viff.comparator.service.impl;

import com.google.common.collect.Lists;
import io.viff.comparator.domain.Point;
import io.viff.comparator.service.ImageDiffAlgorithm;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;

@Service("strictImageDiffAlgorithm")
public class StrictImageDiffAlgorithm extends ImageDiffAlgorithm {

    @Override
    public List<Point> diff(BufferedImage originImage, BufferedImage targetImage) {
        List<Point> points = Lists.newArrayList();
        for (int x = 0; x < originImage.getWidth(); x++) {
            for (int y = 0; y < originImage.getHeight(); y++) {
                int originImageRGB = originImage.getRGB(x, y);
                int targetImageRGB = targetImage.getRGB(x, y);
                if (originImageRGB != targetImageRGB) {
                    points.add(new Point(x, y));
                }
            }
        }
        return points;
    }

    @Override
    protected List<Point> diffOverlap(BufferedImage originImage, BufferedImage targetImage) {
        int height = originImage.getHeight() > targetImage.getHeight() ? targetImage.getHeight() : originImage.getHeight();
        int width = originImage.getWidth() > targetImage.getWidth() ? targetImage.getWidth() : originImage.getWidth();
        List<Point> points = Lists.newArrayList();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int originImageRGB = originImage.getRGB(x, y);
                int targetImageRGB = targetImage.getRGB(x, y);
                if (originImageRGB != targetImageRGB) {
                    points.add(new Point(x, y));
                }
            }
        }
        return points;
    }
}

package io.viff.comparator.service.utils;


import io.viff.comparator.domain.Point;

import java.awt.image.BufferedImage;
import java.util.List;

public class ComparatorUtils {

    public static int calculateSameSizeImageDiff(BufferedImage originImage, BufferedImage targetImage, List<Point> diffPoints, int defaultDiffRGB) {
        int member = 0;
        for (int x = 0; x < originImage.getWidth(); x++) {
            for (int y = 0; y < originImage.getHeight(); y++) {
                int originImageRGB = originImage.getRGB(x, y);
                int targetImageRGB = targetImage.getRGB(x, y);

                int originRed = (originImageRGB >> 16) & 0xff;
                int originGreen = (originImageRGB >> 8) & 0xff;
                int originBlue = originImageRGB & 0xff;

                int targetRed = (targetImageRGB >> 16) & 0xff;
                int targetGreen = (targetImageRGB >> 8) & 0xff;
                int targetBlue = targetImageRGB & 0xff;
                int diff = Math.abs(originRed - targetRed) + Math.abs(originBlue - targetBlue) + Math.abs(originGreen - targetGreen);
                if (diff > 0) {
                    diffPoints.add(new Point(x, y, defaultDiffRGB));
                } else {
                    member++;
                }
            }
        }
        return member;
    }
}

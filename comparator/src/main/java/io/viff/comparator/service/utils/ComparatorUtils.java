package io.viff.comparator.service.utils;


import io.viff.comparator.domain.DiffResult;
import io.viff.comparator.domain.Point;
import jersey.repackaged.com.google.common.collect.Lists;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class ComparatorUtils {

    public static List<Point> strictImageDiff(BufferedImage originImage, BufferedImage targetImage, int defaultDiffRGB) {
        List<Point> points = Lists.newArrayList();
        for (int x = 0; x < originImage.getWidth(); x++) {
            for (int y = 0; y < originImage.getHeight(); y++) {
                int originImageRGB = originImage.getRGB(x, y);
                int targetImageRGB = targetImage.getRGB(x, y);
                if (originImageRGB != targetImageRGB) {
                    points.add(new Point(x, y, defaultDiffRGB));
                }
            }
        }
        return points;
    }

    public static DiffResult calculateImageDiff(BufferedImage originImage, BufferedImage targetImage, int defaultDiffRGB) throws IOException {
        int originImageHeight = originImage.getHeight();
        int targetImageHeight = targetImage.getHeight();
        int originImageWidth = originImage.getWidth();
        int targetImageWidth = targetImage.getWidth();

        DiffResult diffResult = new DiffResult();

        if ((originImageHeight == targetImageHeight) && (originImageWidth == targetImageWidth)) {
            diffResult.setDenominator(originImageHeight * originImageWidth);
            diffResult.setDiffPoints(ComparatorUtils.strictImageDiff(originImage, targetImage, defaultDiffRGB));
        } else {
            // todo: add different size comparator
        }

        return diffResult;
    }
}

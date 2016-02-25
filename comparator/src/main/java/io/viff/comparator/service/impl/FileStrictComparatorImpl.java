package io.viff.comparator.service.impl;

import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.FileStorage;
import io.viff.comparator.domain.Point;
import io.viff.comparator.domain.Storable;
import io.viff.comparator.service.FileComparator;
import jersey.repackaged.com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tbzhang on 2/24/16.
 */
public class FileStrictComparatorImpl implements FileComparator {

    private static final Logger logger = Logger.getLogger(FileStrictComparatorImpl.class);

    private static final int defaultDiffRGB = 0x99ff0000;

    @Override
    public CompareResult compare(Storable origin, Storable target) {
        CompareResult result = new CompareResult();
        try {
            BufferedImage originImage = ImageIO.read(new File(origin.getInternalAccessiblePath()));
            BufferedImage targetImage = ImageIO.read(new File(target.getInternalAccessiblePath()));

            int originImageHeight = originImage.getHeight();
            int targetImageHeight = targetImage.getHeight();
            int originImageWidth = originImage.getWidth();
            int targetImageWidth = targetImage.getWidth();
            double denominator = 0;
            int member = 0;

            List<Point> diffPoints = Lists.newArrayList();

            if ((originImageHeight == targetImageHeight) && (originImageWidth == targetImageWidth)) {
                denominator = originImageHeight * originImageWidth;
                member = calculateSameSizeImageDiff(originImage, targetImage, diffPoints);
            } else {

            }

            FileStorage resultStorage = renderDiffImage(originImage, diffPoints);

            result.setSimilarity(member / denominator);
            result.setDiff(resultStorage);
            result.setSame(member == denominator);

        } catch (IOException e) {
            e.printStackTrace();
            result.setSimilarity(0d);
            result.setDiff(null);
            result.setSame(false);
        }
        return result;
    }

    private FileStorage renderDiffImage(BufferedImage originImage, List<Point> diffPoints) throws IOException {
        FileStorage resultStorage = new FileStorage(new File("result.png"));
        for (Point point : diffPoints) {
            originImage.setRGB(point.getX(), point.getY(), point.getRgb());
        }
        ImageIO.write(originImage, "png", new File(resultStorage.getInternalAccessiblePath()));
        return resultStorage;
    }

    private int calculateSameSizeImageDiff(BufferedImage originImage, BufferedImage targetImage, List<Point> diffPoints) {
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

package io.viff.comparator.service.impl;

import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.FileStorage;
import io.viff.comparator.domain.Point;
import io.viff.comparator.domain.Storable;
import io.viff.comparator.service.Comparator;
import io.viff.comparator.service.utils.ComparatorUtils;
import jersey.repackaged.com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tbzhang on 2/24/16.
 */
@Service
public class FileStrictComparatorImpl implements Comparator {

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
                member = ComparatorUtils.calculateSameSizeImageDiff(originImage, targetImage, diffPoints, defaultDiffRGB);
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

}

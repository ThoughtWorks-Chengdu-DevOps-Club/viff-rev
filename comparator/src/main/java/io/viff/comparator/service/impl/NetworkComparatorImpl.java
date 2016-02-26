package io.viff.comparator.service.impl;

import io.viff.comparator.domain.*;
import io.viff.comparator.service.Comparator;
import io.viff.comparator.service.utils.ComparatorUtils;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class NetworkComparatorImpl implements Comparator {
    private static final int defaultDiffRGB = 0x99ff0000;

    @Override
    public CompareResult compare(Storable origin, Storable target) {
        CompareResult result = new CompareResult();

        try {
            BufferedImage originImage = ImageIO.read(new URL(origin.getExternalAccessiblePath()));
            BufferedImage targetImage = ImageIO.read(new URL(target.getExternalAccessiblePath()));


            int originImageHeight = originImage.getHeight();
            int targetImageHeight = targetImage.getHeight();
            int originImageWidth = originImage.getWidth();
            int targetImageWidth = targetImage.getWidth();
            double denominator = originImageHeight * originImageWidth;
            int member = 0;

            List<Point> diffPoints = Lists.newArrayList();

            if ((originImageHeight == targetImageHeight) && (originImageWidth == targetImageWidth)) {
                member = ComparatorUtils.calculateSameSizeImageDiff(originImage, targetImage, diffPoints, defaultDiffRGB);
            } else {

            }

            UrlStorage resultStorage = renderDiffImage(originImage, diffPoints);

            result.setSimilarity(member / denominator);
            result.setDiff(resultStorage);
            result.setSame(member == denominator);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result;
    }

    private UrlStorage renderDiffImage(BufferedImage originImage, List<Point> diffPoints) throws IOException {
        UrlStorage resultStorage = new UrlStorage(new File("result.png").getAbsolutePath());
        for (Point point : diffPoints) {
            originImage.setRGB(point.getX(), point.getY(), point.getRgb());
        }
        ImageIO.write(originImage, "png", new File(resultStorage.getInternalAccessiblePath()));

        return resultStorage;

    }
}

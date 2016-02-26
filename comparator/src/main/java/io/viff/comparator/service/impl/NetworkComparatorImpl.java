package io.viff.comparator.service.impl;

import io.viff.comparator.domain.*;
import io.viff.comparator.service.Comparator;
import io.viff.comparator.service.utils.ComparatorUtils;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service("networkComparator")
public class NetworkComparatorImpl implements Comparator {
    private static final int defaultDiffRGB = 0x99ff0000;

    @Override
    public CompareResult compare(Storable origin, Storable target) {
        CompareResult result = new CompareResult();

        try {
            BufferedImage originImage = ImageIO.read(new URL(origin.getExternalAccessiblePath()));
            BufferedImage targetImage = ImageIO.read(new URL(target.getExternalAccessiblePath()));

            DiffResult diffResult = ComparatorUtils.calculateImageDiff(originImage, targetImage, defaultDiffRGB);

            Storable resultStorage = renderDiffImage(originImage, diffResult.getDiffPoints());

            result.setSimilarity(1 - (diffResult.getDiffPoints().size() / diffResult.getDenominator()));
            result.setDiff(resultStorage);
            result.setSame(diffResult.getDiffPoints().size() == 0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result;
    }

    @Override
    public Storable renderDiffImage(BufferedImage originImage, List<Point> points) throws IOException {
        UrlStorage resultStorage = new UrlStorage(new File("result.png").getAbsolutePath());
        for (Point point : points) {
            originImage.setRGB(point.getX(), point.getY(), point.getRgb());
        }
        ImageIO.write(originImage, "png", new File(resultStorage.getInternalAccessiblePath()));

        return resultStorage;

    }
}

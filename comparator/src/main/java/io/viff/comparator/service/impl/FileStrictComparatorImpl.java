package io.viff.comparator.service.impl;

import io.viff.comparator.domain.*;
import io.viff.comparator.service.Comparator;
import io.viff.comparator.service.utils.ComparatorUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Service("fileStrictComparator")
public class FileStrictComparatorImpl implements Comparator {

    private static final Logger logger = Logger.getLogger(FileStrictComparatorImpl.class);

    private static final int defaultDiffRGB = 0x99ff0000;

    @Override
    public CompareResult compare(Storable origin, Storable target) {
        CompareResult result = new CompareResult();
        try {
            BufferedImage originImage = ImageIO.read(new File(origin.getInternalAccessiblePath()));
            BufferedImage targetImage = ImageIO.read(new File(target.getInternalAccessiblePath()));

            DiffResult diffResult = ComparatorUtils.calculateImageDiff(originImage, targetImage, defaultDiffRGB);

            FileStorage resultStorage = renderDiffImage(originImage, diffResult.getDiffPoints());

            result.setSimilarity(1 - (diffResult.getDiffPoints().size() / diffResult.getDenominator()));
            result.setDiff(resultStorage);
            result.setSame(diffResult.getDiffPoints().size() == 0);

        } catch (IOException e) {
            e.printStackTrace();
            result.setSimilarity(0d);
            result.setDiff(null);
            result.setSame(false);
        }
        return result;
    }

    @Override
    public FileStorage renderDiffImage(BufferedImage originImage, List<Point> diffPoints) throws IOException {
        FileStorage resultStorage = new FileStorage(new File("result.png"));
        for (Point point : diffPoints) {
            originImage.setRGB(point.getX(), point.getY(), point.getRgb());
        }
        ImageIO.write(originImage, "png", new File(resultStorage.getInternalAccessiblePath()));
        return resultStorage;
    }

}

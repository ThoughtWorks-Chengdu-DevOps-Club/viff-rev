package io.viff.comparator.service.impl;

import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.DiffResult;
import io.viff.comparator.domain.Storable;
import io.viff.comparator.service.Comparator;
import io.viff.comparator.service.DiffImageRenderer;
import io.viff.comparator.service.ImageDiffAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Service("networkComparator")
public class NetworkStrictComparator implements Comparator {

    private int defaultDiffRGB = 0x99ff0000;

    @Autowired
    @Qualifier("strictImageDiffAlgorithm")
    ImageDiffAlgorithm strictImageDiffAlgorithm;


    @Autowired
    @Qualifier("defaultDiffImageRenderer")
    private DiffImageRenderer defaultDiffImageRenderer;

    @Override
    public CompareResult compare(Storable origin, Storable target) {
        CompareResult result = new CompareResult();

        try {
            BufferedImage originImage = ImageIO.read(new URL(origin.getExternalAccessiblePath()));
            BufferedImage targetImage = ImageIO.read(new URL(target.getExternalAccessiblePath()));

            DiffResult diffResult = strictImageDiffAlgorithm.calculateImageDiff(originImage, targetImage);

            Storable resultStorage = defaultDiffImageRenderer.render(originImage, diffResult.getDiffPoints(), defaultDiffRGB);

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
}

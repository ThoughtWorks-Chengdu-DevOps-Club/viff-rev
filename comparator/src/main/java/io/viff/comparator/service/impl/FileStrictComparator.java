package io.viff.comparator.service.impl;

import io.viff.comparator.domain.DiffResult;
import io.viff.comparator.service.Comparator;
import io.viff.comparator.service.DiffImageRenderer;
import io.viff.comparator.service.ImageDiffAlgorithm;
import io.viff.sdk.domain.Storable;
import io.viff.sdk.response.CompareResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("fileStrictComparator")
public class FileStrictComparator implements Comparator {

    private static final Logger logger = Logger.getLogger(FileStrictComparator.class);


    private int defaultDiffRGB = 0x99ff0000;

    @Autowired
    @Qualifier("strictImageDiffAlgorithm")
    private ImageDiffAlgorithm strictImageDiffAlgorithm;

    @Autowired
    @Qualifier("defaultDiffImageRenderer")
    private DiffImageRenderer defaultDiffImageRenderer;


    @Override
    public CompareResult compare(Storable origin, Storable target) {
        CompareResult result = new CompareResult();
        try {
            BufferedImage originImage = ImageIO.read(new File(origin.getInternalAccessiblePath()));
            BufferedImage targetImage = ImageIO.read(new File(target.getInternalAccessiblePath()));

            DiffResult diffResult = strictImageDiffAlgorithm.calculateImageDiff(originImage, targetImage);

            Storable resultStorage = defaultDiffImageRenderer.render(getFilename(origin.getInternalAccessiblePath()), originImage, diffResult.getDiffPoints(), defaultDiffRGB);

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

    private String getFilename(String path) {
        Pattern pattern = Pattern.compile("\\\\/(.*)\\.png");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return "";
        }
    }


}

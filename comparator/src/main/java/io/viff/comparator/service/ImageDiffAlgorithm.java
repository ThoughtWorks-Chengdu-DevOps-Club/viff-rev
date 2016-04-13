package io.viff.comparator.service;

import io.viff.comparator.domain.DiffResult;
import io.viff.comparator.domain.Point;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


public abstract class ImageDiffAlgorithm {

    public DiffResult calculateImageDiff(BufferedImage originImage, BufferedImage targetImage) throws IOException {
        int originImageHeight = originImage.getHeight();
        int targetImageHeight = targetImage.getHeight();
        int originImageWidth = originImage.getWidth();
        int targetImageWidth = targetImage.getWidth();

        DiffResult diffResult = new DiffResult();

        if ((originImageHeight == targetImageHeight) && (originImageWidth == targetImageWidth)) {
            diffResult.setDenominator(originImageHeight * originImageWidth);
            diffResult.setDiffPoints(this.diff(originImage, targetImage));
        } else {
            // todo: add different size comparator
            diffResult.setDenominator(originImageHeight * originImageWidth);
            diffResult.setDiffPoints(diffOverlap(originImage, targetImage));
        }

        return diffResult;
    }

    protected abstract List<Point> diff(BufferedImage originImage, BufferedImage targetImage);

    protected abstract List<Point> diffOverlap(BufferedImage originImage, BufferedImage targetImage);
}

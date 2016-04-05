package io.viff.comparator.service.impl;

import io.viff.comparator.domain.FileStorage;
import io.viff.comparator.domain.Point;
import io.viff.sdk.domain.Storable;
import io.viff.comparator.service.DiffImageRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("defaultDiffImageRenderer")
public class DefaultDiffImageRenderer implements DiffImageRenderer {

    @Value("${application.resultFileName}")
    private String resultFileName;

    @Override
    public Storable render(BufferedImage originImage, List<Point> points, int defaultDiffRGB) throws IOException {
        FileStorage resultStorage = new FileStorage(new File("result.png"));
        for (Point point : points) {
            originImage.setRGB(point.getX(), point.getY(), defaultDiffRGB);
        }
        ImageIO.write(originImage, "png", new File(resultStorage.getInternalAccessiblePath()));
        return resultStorage;
    }
}

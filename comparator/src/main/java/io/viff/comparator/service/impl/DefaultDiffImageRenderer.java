package io.viff.comparator.service.impl;

import io.viff.comparator.domain.FileStorage;
import io.viff.comparator.domain.Point;
import io.viff.comparator.service.DiffImageRenderer;
import io.viff.sdk.domain.Storable;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service("defaultDiffImageRenderer")
public class DefaultDiffImageRenderer implements DiffImageRenderer {

    @Value("${application.files.resultPrefix}")
    private String prefix;

    @Value("${application.files.localPath}")
    private String filePath;

    @Override
    public Storable render(String originFilename, BufferedImage originImage, List<Point> points, int defaultDiffRGB) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            Files.createDirectories(Paths.get(filePath));
        }
        FileStorage resultStorage = new FileStorage(new File(String.format("%s/%s_%s_%s.png", filePath, prefix, originFilename, randomName(16))));
        for (Point point : points) {
            originImage.setRGB(point.getX(), point.getY(), defaultDiffRGB);
        }
        ImageIO.write(originImage, "png", new File(resultStorage.getInternalAccessiblePath()));
        return resultStorage;
    }

    private String randomName(int length) {
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            b[i] = (byte) ThreadLocalRandom.current().nextInt(48, 122);
        }
        return Base64.encodeBase64String(b);
    }
}

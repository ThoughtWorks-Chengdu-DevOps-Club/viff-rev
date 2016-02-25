package io.viff.comparator.service.impl;

import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.FileStorage;
import io.viff.comparator.service.FileComparator;
import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * Created by tbzhang on 2/24/16.
 */
public class FileComparatorImplTest {
    private FileComparator fileComparator = new FileComparatorImpl();


    @Test
    public void should_output_image() throws Exception {
        long start = new Date().getTime();


        FileStorage fileA = new FileStorage();
        FileStorage fileB = new FileStorage();
        fileA.setFilePath("/Users/tbzhang/Documents/1.png");
        fileB.setFilePath("/Users/tbzhang/Documents/1.png");


        CompareResult compare = fileComparator.compare(fileA,
                fileB);
        System.out.println(compare);
        System.out.println("cost:" + (new Date().getTime() - start));

    }
}
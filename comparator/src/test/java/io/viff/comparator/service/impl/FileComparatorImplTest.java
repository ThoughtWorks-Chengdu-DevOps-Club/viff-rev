package io.viff.comparator.service.impl;

import io.viff.comparator.ComparatorApplication;
import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.FileStorage;
import io.viff.comparator.service.FileComparator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Date;

/**
 * Created by tbzhang on 2/24/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ComparatorApplication.class)
public class FileComparatorImplTest {
    private FileComparator fileComparator = new FileComparatorImpl();

    @Autowired
    ApplicationContext context;


    @Test
    public void should_output_image() throws Exception {
        long start = new Date().getTime();


        FileStorage fileA = new FileStorage(context.getResource("tc_1_1.png").getFile());
        FileStorage fileB = new FileStorage(context.getResource("tc_1_2.png").getFile());


        CompareResult compare = fileComparator.compare(fileA,
                fileB);
        System.out.println(compare);
        System.out.println("cost:" + (new Date().getTime() - start));

    }
}
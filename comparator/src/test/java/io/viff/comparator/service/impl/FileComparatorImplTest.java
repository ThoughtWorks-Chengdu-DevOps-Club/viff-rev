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

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.AdditionalMatchers.lt;

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
    public void should_get_is_same_when_two_image_is_same() throws Exception {
        long start = new Date().getTime();

        FileStorage fileA = new FileStorage(context.getResource("tc_1_1.png").getFile());
        FileStorage fileB = new FileStorage(context.getResource("tc_1_1.png").getFile());

        CompareResult result = fileComparator.compare(fileA,
                fileB);
        assertThat(result.getSimilarity(), is(1d));
        System.out.println("cost:" + (new Date().getTime() - start));
    }


    @Test
    public void should_get_similarity_under_1_when_given_two_different_image() throws Exception {
        long start = new Date().getTime();

        FileStorage fileA = new FileStorage(context.getResource("tc_1_1.png").getFile());
        FileStorage fileB = new FileStorage(context.getResource("tc_1_2.png").getFile());

        CompareResult result = fileComparator.compare(fileA, fileB);
        assertThat(result.getSimilarity(), is(lessThan(1.0)));
        System.out.println("cost:" + (new Date().getTime() - start));

    }


}
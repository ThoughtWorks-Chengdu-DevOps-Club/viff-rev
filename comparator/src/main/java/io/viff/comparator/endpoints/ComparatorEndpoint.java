package io.viff.comparator.endpoints;

import io.viff.comparator.domain.FileStorage;
import io.viff.comparator.domain.UrlStorage;
import io.viff.comparator.service.Comparator;
import io.viff.sdk.request.CompareRequest;
import io.viff.sdk.response.CompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


@RestController
@RequestMapping("/compare")
public class ComparatorEndpoint {

    @Autowired
    @Qualifier("networkComparator")
    private Comparator networkComparator;

    @Autowired
    @Qualifier("fileStrictComparator")
    private Comparator fileStrictComparator;

    @RequestMapping(method = RequestMethod.POST, path = "/network")
    public CompareResult networkCompare(@RequestBody CompareRequest compareRequest) {

        UrlStorage FileA = new UrlStorage(compareRequest.getFrom());
        UrlStorage fileB = new UrlStorage(compareRequest.getTo());

        return networkComparator.compare(FileA, fileB);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/fs")
    public CompareResult fsCompare(@RequestBody CompareRequest compareRequest) {

        FileStorage fileA = new FileStorage(new File(compareRequest.getFrom()));
        FileStorage fileB = new FileStorage(new File(compareRequest.getTo()));

        return fileStrictComparator.compare(fileA, fileB);
    }


}

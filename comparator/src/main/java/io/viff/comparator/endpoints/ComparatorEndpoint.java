package io.viff.comparator.endpoints;

import io.viff.comparator.domain.UrlStorage;
import io.viff.comparator.service.Comparator;
import io.viff.sdk.response.CompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;


@RestController
@RequestMapping("/compare")
public class ComparatorEndpoint {

    @Autowired
    @Qualifier("networkComparator")
    private Comparator networkComparator;

    @RequestMapping(method = RequestMethod.GET, path = "{from}/{to}")
    public CompareResult compare(@PathParam("from") String from, @PathParam("to") String to) {

        UrlStorage FileA = new UrlStorage(from);
        UrlStorage fileB = new UrlStorage(to);

        return networkComparator.compare(FileA, fileB);
    }
}

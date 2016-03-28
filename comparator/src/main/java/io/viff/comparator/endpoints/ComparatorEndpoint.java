package io.viff.comparator.endpoints;

import io.viff.comparator.domain.CompareRequest;
import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.UrlStorage;
import io.viff.comparator.service.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/compare")
@Produces("application/json")
@Consumes("application/json")
public class ComparatorEndpoint {

    @Autowired
    @Qualifier("networkComparator")
    private Comparator networkComparator;

    @GET
    public CompareResult compare(@BeanParam CompareRequest compareRequest) {

        UrlStorage fileA = new UrlStorage(compareRequest.getFrom());
        UrlStorage fileB = new UrlStorage(compareRequest.getTo());

        return networkComparator.compare(fileA, fileB);
    }
}

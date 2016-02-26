package io.viff.comparator.endpoints;

import io.viff.comparator.domain.CompareRequest;
import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.UrlStorage;
import io.viff.comparator.service.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/compare")
@Produces("application/json")
public class ComparatorEndpoint {

    @Autowired
    @Qualifier("networkComparator")
    private Comparator networkComparator;

    @GET
    public CompareResult compare(@NotNull @BeanParam CompareRequest compareRequest) {

        UrlStorage FileA = new UrlStorage(compareRequest.getFrom());
        UrlStorage fileB = new UrlStorage(compareRequest.getTo());

        return networkComparator.compare(FileA, fileB);
    }
}

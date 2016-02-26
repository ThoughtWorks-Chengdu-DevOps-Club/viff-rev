package io.viff.comparator.endpoints;

import io.viff.comparator.domain.CompareResult;
import io.viff.comparator.domain.UrlStorage;
import io.viff.comparator.service.impl.NetworkComparatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

@Component
@Path("/compare")
@Produces("application/json")
public class ComparatorEndpoint {

    @Autowired
    private NetworkComparatorImpl networkComparator;

    @POST
    public CompareResult compare(@NotNull @FormParam("compareTo") String compareTo,@NotNull @FormParam("compared") String compared){

        UrlStorage FileA = new UrlStorage(compareTo);
        UrlStorage fileB = new UrlStorage(compared);

        CompareResult result = networkComparator.compare(FileA, fileB);

        return result;
    }
}

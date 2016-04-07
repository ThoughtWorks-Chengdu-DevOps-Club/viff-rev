package io.viff.storage.client;

import io.viff.sdk.request.CompareRequest;
import io.viff.sdk.response.CompareResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("viff-comparator")
public interface ComparatorClient {

    @RequestMapping(method = RequestMethod.POST, value = "/compare/network",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompareResult networkCompare(@RequestBody CompareRequest compareRequest);


    @RequestMapping(method = RequestMethod.POST, value = "/compare/fs",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompareResult fsCompare(@RequestBody CompareRequest compareRequest);


}

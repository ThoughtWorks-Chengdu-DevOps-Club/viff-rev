package io.viff.storage.client;

import io.viff.sdk.request.CompareRequest;
import io.viff.sdk.response.CompareResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tbzhang on 4/5/16.
 */
@FeignClient(name = "viff-comparator")
public interface ComparatorClient {

    @RequestMapping(method = RequestMethod.GET, value = "/compare")
    CompareResult compare(CompareRequest request);

}

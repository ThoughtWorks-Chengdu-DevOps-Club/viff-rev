package io.viff.storage.client;

import io.viff.sdk.response.CompareResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("viff-comparator")
public interface ComparatorClient {

    @RequestMapping(method = RequestMethod.GET, value = "/compare")
    CompareResult compare(@RequestParam("from") String from, @RequestParam("to") String to);

}

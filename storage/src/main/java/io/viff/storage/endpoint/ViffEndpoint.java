package io.viff.storage.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viff")
public class ViffEndpoint {

    @RequestMapping(method = RequestMethod.POST)
    public String viff() {

        return "";
    }

}

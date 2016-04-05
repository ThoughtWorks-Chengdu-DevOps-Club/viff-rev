package io.viff.storage.endpoint;

import io.viff.sdk.request.ViffRequest;
import io.viff.sdk.response.ViffResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viff")
public class ViffEndpoint {

    @RequestMapping(method = RequestMethod.POST)
    public ViffResponse viff(ViffRequest request) {


        ViffResponse viffResponse = new ViffResponse();
        viffResponse.setMetaData(request);
        return viffResponse;
    }

}

package io.viff.storage.endpoint;

import io.viff.sdk.request.ViffRequest;
import io.viff.sdk.response.ViffResponse;
import io.viff.storage.service.ViffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viff")
public class ViffEndpoint {

    @Qualifier("viffService")
    @Autowired
    private ViffService viffService;

    @RequestMapping(method = RequestMethod.POST)
    public ViffResponse viff(ViffRequest request) {
        ViffResponse viffResponse = new ViffResponse();
        viffResponse.setMetaData(request);
        viffResponse.setViffResult(viffService.viff(request));
        return viffResponse;
    }

}

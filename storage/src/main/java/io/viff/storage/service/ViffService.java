package io.viff.storage.service;

import io.viff.sdk.request.ViffRequest;
import io.viff.sdk.response.ViffItemResponse;

import java.util.List;

/**
 * Created by tbzhang on 4/5/16.
 */
public interface ViffService {
    List<ViffItemResponse> viff(ViffRequest viffRequest);
}

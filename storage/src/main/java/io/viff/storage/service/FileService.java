package io.viff.storage.service;

import io.viff.sdk.response.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by tbzhang on 3/7/16.
 */
public interface FileService {
    UploadFileResponse save(String projectID, String tag, MultipartFile file) throws IOException;

    UploadFileResponse save(String projectID, String tag, Integer buildNumber, MultipartFile file) throws IOException;

}

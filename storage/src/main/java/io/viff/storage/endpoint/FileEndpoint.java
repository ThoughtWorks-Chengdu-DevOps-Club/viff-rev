package io.viff.storage.endpoint;

import io.viff.sdk.response.UploadFileResponse;
import io.viff.storage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/file")
public class FileEndpoint {

    @Autowired
    @Qualifier("fileService")
    FileService fileService;


    @RequestMapping(method = RequestMethod.POST, value = "/upload/{projectID}/{tag}")
    public UploadFileResponse upload(@RequestParam("file") MultipartFile file, @PathVariable("projectID") String projectID,
                                     @PathVariable("tag") String tag) throws IOException {
        return fileService.save(projectID, tag, file);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/upload/{projectID}/{tag}/{buildNumber}")
    public UploadFileResponse upload(@RequestParam("file") MultipartFile file, @PathVariable("projectID") String projectID,
                                     @PathVariable("tag") String tag, @PathVariable("buildNumber") Integer buildNumber) throws IOException {
        return fileService.save(projectID, tag, buildNumber, file);
    }

}

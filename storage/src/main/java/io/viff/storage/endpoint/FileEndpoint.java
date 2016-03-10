package io.viff.storage.endpoint;

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
    public String upload(@RequestParam("file") MultipartFile file, @PathVariable("projectID") String projectID,
                         @PathVariable("tag") String tag) throws IOException {
        fileService.save(projectID, tag, file);
        return file.toString();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/upload/{projectID}/{tag}/{buildNumber}")
    public String upload(@RequestParam("file") MultipartFile file, @PathVariable("projectID") String projectID,
                         @PathVariable("tag") String tag, @PathVariable("buildNumber") String buildNumber) throws IOException {
        fileService.save(projectID, tag, buildNumber, file);
        return file.toString();
    }

}

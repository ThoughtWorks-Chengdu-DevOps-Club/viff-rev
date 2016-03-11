package io.viff.storage.service.impl;

import io.viff.storage.endpoint.UploadFileResponse;
import io.viff.storage.model.ProjectModel;
import io.viff.storage.model.TagModel;
import io.viff.storage.repository.ProjectRepository;
import io.viff.storage.repository.TagRepository;
import io.viff.storage.service.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Value("${application.file.localPath}")
    private String localPath;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TagRepository tagRepository;


    @Override
    public UploadFileResponse save(String projectID, String tag, MultipartFile file) throws IOException {
        createParentPath(Paths.get(localPath + "/" + projectID + "/" + tag + "/" + "test/"));
        Files.copy(file.getInputStream(), Paths.get(localPath + "/" + projectID + "/" + tag + "/" + file.getOriginalFilename()));

        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        uploadFileResponse.setTag(tag);

        return uploadFileResponse;
    }

    @Override
    public UploadFileResponse save(String projectID, String tag, String buildNumber, MultipartFile file) throws IOException {
        createParentPath(Paths.get(localPath + "/" + projectID + "/" + tag + "/" + buildNumber));

        Files.copy(file.getInputStream(), Paths.get(localPath + "/" + projectID + "/" + tag + "/" + buildNumber + "/" + file.getOriginalFilename()));
        return new UploadFileResponse("", tag, buildNumber);
    }

    private ProjectModel getProject(String projectID) {
        ProjectModel projectModel = projectRepository.findOneByProjectID(projectID);
        if (projectModel == null) {
            ProjectModel newProject = new ProjectModel();
            newProject.setProjectID(projectID);
            projectRepository.save(newProject);
            return newProject;

        } else {
            return projectModel;
        }
    }

    private TagModel getTag(Long projectID, String tagName) {
        TagModel tagModel = tagRepository.findOneByTagNameAndProjectID(tagName, projectID);
        if (null == tagModel) {
            TagModel tag = new TagModel();
            tag.setProjectID(projectID);
            tag.setTagName(tagName);
            tagRepository.save(tag);
            return tag;
        } else {
            return tagModel;
        }
    }


    private void createParentPath(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

}

package io.viff.storage.service.impl;

import io.viff.storage.endpoint.UploadFileResponse;
import io.viff.storage.model.BuildModel;
import io.viff.storage.model.ProjectModel;
import io.viff.storage.model.TagModel;
import io.viff.storage.repository.BuildRepository;
import io.viff.storage.repository.ProjectRepository;
import io.viff.storage.repository.TagRepository;
import io.viff.storage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    BuildRepository buildRepository;


    @Override
    @Transactional
    public UploadFileResponse save(String projectID, String tagName, MultipartFile file) throws IOException {

        ProjectModel projectModel = getProject(projectID);
        TagModel tag = getTag(projectModel.getId(), tagName);
        BuildModel buildModel = getBuild(tag);

        tag.setLatestBuildID(buildModel.getId());
        tagRepository.save(tag);

        createParentPath(Paths.get(String.format("%s/%s/%s/%d",
                localPath, projectID, tagName, buildModel.getBuildNumber())));

        Path target = Paths.get(String.format("%s/%s/%s/%d/%s",
                localPath, projectID, tagName, buildModel.getBuildNumber(), file.getOriginalFilename()));
        Files.copy(file.getInputStream(), target);


        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        uploadFileResponse.setTag(tagName);
        uploadFileResponse.setBuildNumber(buildModel.getBuildNumber());
        uploadFileResponse.setUrl(target.toString());

        return uploadFileResponse;
    }

    @Override
    @Transactional
    public UploadFileResponse save(String projectID, String tag, Integer buildNumber, MultipartFile file) throws IOException {
        createParentPath(Paths.get(String.format("%s/%s/%s/%s", localPath, projectID, tag, buildNumber)));
        Files.copy(file.getInputStream(), Paths.get(String.format("%s/%s/%s/%s/%s", localPath, projectID, tag, buildNumber, file.getOriginalFilename())));
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

    private BuildModel getBuild(TagModel tagModel) {
        BuildModel buildModel = new BuildModel();
        buildModel.setTagID(tagModel.getId());
        if (null == tagModel.getLatestBuildID()) {
            buildModel.setBuildNumber(1);
            buildRepository.save(buildModel);
            tagModel.setLatestBuildID(buildModel.getId());
            tagRepository.save(tagModel);
        } else {
            BuildModel lastBuild = buildRepository.findOne(tagModel.getLatestBuildID());
            // build number增长+1
            buildModel.setBuildNumber(lastBuild.getBuildNumber() + 1);
            buildRepository.save(buildModel);
            tagModel.setLatestBuildID(buildModel.getId());
            tagRepository.save(tagModel);
        }
        return buildModel;

    }


    private void createParentPath(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

}

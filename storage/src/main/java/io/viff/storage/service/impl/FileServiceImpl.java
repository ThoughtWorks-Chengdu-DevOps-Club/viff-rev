package io.viff.storage.service.impl;

import io.viff.storage.endpoint.UploadFileResponse;
import io.viff.storage.model.BuildModel;
import io.viff.storage.model.FileModel;
import io.viff.storage.model.ProjectModel;
import io.viff.storage.model.TagModel;
import io.viff.storage.repository.BuildRepository;
import io.viff.storage.repository.FileRepository;
import io.viff.storage.repository.ProjectRepository;
import io.viff.storage.repository.TagRepository;
import io.viff.storage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Value("${application.files.localPath}")
    private String localPath;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    BuildRepository buildRepository;

    @Autowired
    FileRepository fileRepository;


    @Override
    public UploadFileResponse save(String projectID, String tagName, MultipartFile file) throws IOException {

        ProjectModel projectModel = getProject(projectID);
        TagModel tag = getTag(projectModel.getId(), tagName);
        BuildModel buildModel = getBuild(tag);

        createParentPath(Paths.get(String.format("%s/%s/%s/%d",
                localPath, projectID, tagName, buildModel.getBuildNumber())));

        Path target = Paths.get(String.format("%s/%s/%s/%d/%s",
                localPath, projectID, tagName, buildModel.getBuildNumber(), file.getOriginalFilename()));
        Files.copy(file.getInputStream(), target);
        FileModel savedFileModel = saveFileModel(target, buildModel);

        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        uploadFileResponse.setProject(projectID);
        uploadFileResponse.setTag(tagName);
        uploadFileResponse.setBuildNumber(buildModel.getBuildNumber());
        uploadFileResponse.setUrl(savedFileModel.getFilePath());
        return uploadFileResponse;
    }

    @Override
    @Transactional
    public UploadFileResponse save(String projectID, String tagName, Integer buildNumber, MultipartFile file) throws IOException {
        createParentPath(Paths.get(String.format("%s/%s/%s/%s", localPath, projectID, tagName, buildNumber)));
        Path target = Paths.get(String.format("%s/%s/%s/%s/%s", localPath, projectID, tagName, buildNumber, file.getOriginalFilename()));
        if (Files.exists(target)) {
            throw new FileAlreadyExistsException(file.getName(), "", String.format("%s/%s/%s/%s/%s already exists", localPath, projectID, tagName, buildNumber, file.getOriginalFilename()));
        } else {
            Files.copy(file.getInputStream(), target);
            ProjectModel project = projectRepository.findOneByProjectID(projectID);
            TagModel tag = tagRepository.findOneByTagNameAndProjectID(tagName, project.getId());
            BuildModel build = buildRepository.findOneByTagIDAndBuildNumber(tag.getId(), buildNumber);
            FileModel fileModel = saveFileModel(target, build);
            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileModel.getFilePath(), tagName, buildNumber);
            uploadFileResponse.setProject(projectID);
            return uploadFileResponse;
        }
    }

    @Transactional
    private ProjectModel getProject(String projectID) {
        ProjectModel projectModel = projectRepository.findOneByProjectID(projectID);
        if (projectModel == null) {
            ProjectModel newProject = new ProjectModel();
            newProject.setProjectID(projectID);
            return projectRepository.save(newProject);
        } else {
            return projectModel;
        }
    }

    @Transactional
    private TagModel getTag(Long projectID, String tagName) {
        TagModel tagModel = tagRepository.findOneByTagNameAndProjectID(tagName, projectID);
        if (null == tagModel) {
            TagModel tag = new TagModel();
            tag.setProjectID(projectID);
            tag.setTagName(tagName);
            return tagRepository.save(tag);
        } else {
            return tagModel;
        }
    }

    @Transactional
    private BuildModel getBuild(TagModel tagModel) {
        BuildModel buildModel = new BuildModel();
        buildModel.setTagID(tagModel.getId());
        if (null == tagModel.getLatestBuildID()) {
            // create new build
            buildModel.setBuildNumber(1);
            buildModel = buildRepository.save(buildModel);
            tagModel.setLatestBuildID(buildModel.getId());
        } else {
            BuildModel lastBuild = buildRepository.findOne(tagModel.getLatestBuildID());
            // build number增长+1
            buildModel.setBuildNumber(lastBuild.getBuildNumber() + 1);
            buildModel = buildRepository.save(buildModel);
            tagModel.setLatestBuildID(buildModel.getId());
        }
        tagRepository.save(tagModel);
        return buildModel;
    }

    @Transactional
    private FileModel saveFileModel(Path path, BuildModel build) {
        String filePath = path.toAbsolutePath().toString();
        FileModel fileModel = new FileModel();
        fileModel.setFileName(path.getFileName().toString());
        fileModel.setFilePath(filePath);
        fileModel.setBuildID(build.getId());
        return fileRepository.save(fileModel);
    }


    private void createParentPath(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

}

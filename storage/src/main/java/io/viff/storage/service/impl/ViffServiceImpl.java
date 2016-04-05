package io.viff.storage.service.impl;

import com.google.common.collect.Lists;
import io.viff.sdk.request.CompareRequest;
import io.viff.sdk.request.ViffRequest;
import io.viff.sdk.response.CompareResult;
import io.viff.sdk.response.ViffItemResponse;
import io.viff.storage.client.ComparatorClient;
import io.viff.storage.model.BuildModel;
import io.viff.storage.model.FileModel;
import io.viff.storage.model.ProjectModel;
import io.viff.storage.model.TagModel;
import io.viff.storage.repository.*;
import io.viff.storage.service.ViffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("viffService")
public class ViffServiceImpl implements ViffService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    BuildRepository buildRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    ViffRepository viffRepository;

    @Autowired
    ViffFileItemRepository viffFileItemRepository;

    @Autowired
    ComparatorClient comparatorClient;

    @Override
    public List<ViffItemResponse> viff(ViffRequest viffRequest) {
        ProjectModel projectModel = projectRepository.findOneByProjectID(viffRequest.getProjectID());
        TagModel originTagModel = tagRepository.findOneByTagNameAndProjectID(viffRequest.getTagName(), projectModel.getId());
        TagModel targetTagModel = tagRepository.findOneByTagNameAndProjectID(viffRequest.getTargetTagName(), projectModel.getId());
        BuildModel originBuildModel = buildRepository.findOneByTagIDAndBuildNumber(originTagModel.getId(), viffRequest.getBuildNumber());
        BuildModel targetBuildModel = buildRepository.findOneByTagIDAndBuildNumber(targetTagModel.getId(), viffRequest.getTargetBuildNumber());
        List<FileModel> originFiles = fileRepository.findByBuildID(originBuildModel.getId());
        List<FileModel> targetFiles = fileRepository.findByBuildID(targetBuildModel.getId());


        Map<String, FileModel> originFileMap = originFiles.stream().collect(Collectors.toMap(FileModel::getFileName, fileModel -> fileModel));
        Map<String, FileModel> targetFileMap = targetFiles.stream().collect(Collectors.toMap(FileModel::getFileName, fileModel -> fileModel));

        List<ViffItemResponse> result = Lists.newArrayList();
        for (Map.Entry<String, FileModel> originFileEntry : originFileMap.entrySet()) {
            if (targetFileMap.containsKey(originFileEntry.getKey())) {
                CompareRequest compareRequest = new CompareRequest(originFileEntry.getValue().getFilePath(), targetFileMap.get(originFileEntry.getKey()).getFilePath());
                CompareResult compareResult = comparatorClient.compare(compareRequest);
                ViffItemResponse viffItemResponse = new ViffItemResponse();
                viffItemResponse.setImageID(originFileEntry.getValue().getFileName());
                viffItemResponse.setNew(false);
                viffItemResponse.setOriginImageURL(originFileEntry.getValue().getFilePath());
                viffItemResponse.setSame(compareResult.getSimilarity() == 1.0d);
                viffItemResponse.setSimilarity(compareResult.getSimilarity());
                viffItemResponse.setTargetImageURL(targetFileMap.get(originFileEntry.getKey()).getFilePath());
                viffItemResponse.setViffImageURL(compareResult.getDiff().getInternalAccessiblePath());
                result.add(viffItemResponse);
                targetFileMap.remove(originFileEntry.getKey());
            } else {
                ViffItemResponse viffItemResponse = new ViffItemResponse();
                viffItemResponse.setNew(true);
                viffItemResponse.setRemoved(false);
                viffItemResponse.setSame(false);
                viffItemResponse.setImageID(originFileEntry.getValue().getFileName());
                viffItemResponse.setOriginImageURL(originFileEntry.getValue().getFilePath());
                viffItemResponse.setViffImageURL(originFileEntry.getValue().getFilePath());
                viffItemResponse.setSimilarity(0d);
                result.add(viffItemResponse);
            }
        }
        for (Map.Entry<String, FileModel> targetFileModelEntry : targetFileMap.entrySet()) {
            ViffItemResponse viffItemResponse = new ViffItemResponse();
            viffItemResponse.setNew(false);
            viffItemResponse.setRemoved(true);
            viffItemResponse.setSame(false);
            viffItemResponse.setImageID(targetFileModelEntry.getValue().getFileName());
            viffItemResponse.setTargetImageURL(targetFileModelEntry.getValue().getFilePath());
            viffItemResponse.setViffImageURL(targetFileModelEntry.getValue().getFilePath());
            viffItemResponse.setSimilarity(0d);
            result.add(viffItemResponse);
        }
        return result;
    }
}

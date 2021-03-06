package io.viff.storage.service.impl;

import com.google.common.collect.Lists;
import io.viff.sdk.request.CompareRequest;
import io.viff.sdk.request.ViffRequest;
import io.viff.sdk.response.CompareResult;
import io.viff.sdk.response.ViffItemResponse;
import io.viff.storage.client.ComparatorClient;
import io.viff.storage.model.*;
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

        ViffModel viffModel = saveViffModel(projectModel, originTagModel, targetTagModel, originBuildModel, targetBuildModel);
        List<ViffFileItemModel> viffFileItemModelList = Lists.newArrayList();

        List<ViffItemResponse> result = Lists.newArrayList();
        for (Map.Entry<String, FileModel> originFileEntry : originFileMap.entrySet()) {
            if (targetFileMap.containsKey(originFileEntry.getKey())) {
                CompareRequest compareRequest = new CompareRequest(originFileEntry.getValue().getFilePath(), targetFileMap.get(originFileEntry.getKey()).getFilePath());
                CompareResult compareResult = comparatorClient.fsCompare(compareRequest);
                viffFileItemModelList.add(getViffFileItemModel(originFileEntry.getValue().getId(), targetFileMap.get(originFileEntry.getKey()).getId(), compareResult.getDiff().getInternalAccessPath(), compareResult, viffModel));
                ViffItemResponse viffItemResponse = buildViffItemResponse(targetFileMap, originFileEntry, compareResult);
                result.add(viffItemResponse);
                targetFileMap.remove(originFileEntry.getKey());
            } else {
                viffFileItemModelList.add(getNewViffFileItem(originFileEntry, viffModel));

                ViffItemResponse viffItemResponse = buildNewViffItemResponse(originFileEntry);
                result.add(viffItemResponse);
            }
        }
        for (Map.Entry<String, FileModel> targetFileModelEntry : targetFileMap.entrySet()) {
            viffFileItemModelList.add(getRemovedViffFileItem(targetFileModelEntry, viffModel));
            ViffItemResponse viffItemResponse = buildRemovedViffItemResponse(targetFileModelEntry);
            result.add(viffItemResponse);
        }
        viffFileItemRepository.save(viffFileItemModelList);
        return result;
    }

    private ViffFileItemModel getViffFileItemModel(Long id, Long id2, String internalAccessPath, CompareResult compareResult, ViffModel viffModel) {
        ViffFileItemModel viffFileItemModel = new ViffFileItemModel();
        viffFileItemModel.setOriginFileID(id);
        viffFileItemModel.setTargetFileID(id2);
        viffFileItemModel.setViffFilePath(internalAccessPath);
        viffFileItemModel.setSame(compareResult.getSimilarity() == 1d);
        viffFileItemModel.setSimilarity(compareResult.getSimilarity());
        viffFileItemModel.setViffID(viffModel.getId());
        return viffFileItemModel;
    }

    private ViffFileItemModel getNewViffFileItem(Map.Entry<String, FileModel> originFileEntry, ViffModel viffModel) {
        ViffFileItemModel viffFileItemModel = new ViffFileItemModel();
        viffFileItemModel.setOriginFileID(originFileEntry.getValue().getId());
        viffFileItemModel.setTargetFileID(null);
        viffFileItemModel.setViffFilePath(originFileEntry.getValue().getFilePath());
        viffFileItemModel.setSame(false);
        viffFileItemModel.setSimilarity(0d);
        viffFileItemModel.setViffID(viffModel.getId());
        return viffFileItemModel;
    }

    private ViffFileItemModel getRemovedViffFileItem(Map.Entry<String, FileModel> targetFileModelEntry, ViffModel viffModel) {
        ViffFileItemModel viffFileItemModel = new ViffFileItemModel();
        viffFileItemModel.setOriginFileID(null);
        viffFileItemModel.setTargetFileID(targetFileModelEntry.getValue().getId());
        viffFileItemModel.setViffFilePath(targetFileModelEntry.getValue().getFilePath());
        viffFileItemModel.setSame(false);
        viffFileItemModel.setSimilarity(0d);
        viffFileItemModel.setViffID(viffModel.getId());
        return viffFileItemModel;
    }

    private ViffItemResponse buildRemovedViffItemResponse(Map.Entry<String, FileModel> targetFileModelEntry) {
        ViffItemResponse viffItemResponse = new ViffItemResponse();
        viffItemResponse.setNew(false);
        viffItemResponse.setRemoved(true);
        viffItemResponse.setSame(false);
        viffItemResponse.setImageID(targetFileModelEntry.getValue().getFileName());
        viffItemResponse.setTargetImageURL(targetFileModelEntry.getValue().getFilePath());
        viffItemResponse.setViffImageURL(targetFileModelEntry.getValue().getFilePath());
        viffItemResponse.setSimilarity(0d);
        return viffItemResponse;
    }

    private ViffItemResponse buildNewViffItemResponse(Map.Entry<String, FileModel> originFileEntry) {
        ViffItemResponse viffItemResponse = new ViffItemResponse();
        viffItemResponse.setNew(true);
        viffItemResponse.setRemoved(false);
        viffItemResponse.setSame(false);
        viffItemResponse.setImageID(originFileEntry.getValue().getFileName());
        viffItemResponse.setOriginImageURL(originFileEntry.getValue().getFilePath());
        viffItemResponse.setViffImageURL(originFileEntry.getValue().getFilePath());
        viffItemResponse.setSimilarity(0d);
        return viffItemResponse;
    }

    private ViffItemResponse buildViffItemResponse(Map<String, FileModel> targetFileMap, Map.Entry<String, FileModel> originFileEntry, CompareResult compareResult) {
        ViffItemResponse viffItemResponse = new ViffItemResponse();
        viffItemResponse.setImageID(originFileEntry.getValue().getFileName());
        viffItemResponse.setNew(false);
        viffItemResponse.setOriginImageURL(originFileEntry.getValue().getFilePath());
        viffItemResponse.setSame(compareResult.getSimilarity() == 1.0d);
        viffItemResponse.setSimilarity(compareResult.getSimilarity());
        viffItemResponse.setTargetImageURL(targetFileMap.get(originFileEntry.getKey()).getFilePath());
        viffItemResponse.setViffImageURL(compareResult.getDiff().getInternalAccessPath());
        return viffItemResponse;
    }

    private ViffModel saveViffModel(ProjectModel projectModel, TagModel originTagModel, TagModel targetTagModel, BuildModel originBuildModel, BuildModel targetBuildModel) {
        ViffModel viffModel = new ViffModel();
        viffModel.setOriginBuildID(originBuildModel.getTagID());
        viffModel.setOriginTagID(originTagModel.getId());
        viffModel.setProjectID(projectModel.getId());
        viffModel.setTargetBuildID(targetBuildModel.getId());
        viffModel.setTargetTagID(targetTagModel.getId());
        return viffRepository.save(viffModel);
    }


}

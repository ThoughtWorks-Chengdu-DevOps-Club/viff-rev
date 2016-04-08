package io.viff.storage.repository;

import io.viff.storage.model.FileModel;

import java.util.List;


public interface FileRepository extends BaseRepository<FileModel, Long> {
    List<FileModel> findByBuildID(Long buildID);
}

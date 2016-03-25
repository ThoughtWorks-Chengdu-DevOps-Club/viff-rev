package io.viff.storage.repository;

import io.viff.storage.model.BuildModel;

public interface BuildRepository extends BaseRepository<BuildModel, Long> {
    BuildModel findOneByTagIDAndBuildNumber(Long tagID, Integer buildNumber);
}

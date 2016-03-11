package io.viff.storage.repository;

import io.viff.storage.model.TagModel;

public interface TagRepository extends BaseRepository<TagModel, Long> {
    TagModel findOneByTagName(String tagName);

    TagModel findOneByTagNameAndProjectID(String tagName, Long projectID);
}

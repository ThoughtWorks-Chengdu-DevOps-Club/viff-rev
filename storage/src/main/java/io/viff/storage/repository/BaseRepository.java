package io.viff.storage.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;


@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    T findOne(ID id);

    T save(T t);

    void delete(T t);

    T update(T t);
}

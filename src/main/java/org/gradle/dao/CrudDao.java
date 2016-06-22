package org.gradle.dao;

import java.util.List;

import org.gradle.domain.Entity;

/**
 * Interface for CRUD-operation.
 * @author Artsiom_Buyevich
 * @param <K> key
 * @param <T> entity
 */
public interface CrudDao<K, T extends Entity> {

    /**
     * Cretate entity.
     * @param entity entity
     */
    void create(T entity);

    /**
     * Read entity by key.
     * @param key identifier entity.
     * @return if found entity, then instance, else null
     */
    T read(K key);

    /**
     * Update entity.
     * @param entity entity
     */
    void update(T entity);

    /**
     * Delete entity by key.
     * @param key key
     */
    void delete(K key);

    /**
     * Find all entities.
     * @return list of entity
     */
    List<T> findAll();

}

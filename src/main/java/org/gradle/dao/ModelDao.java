package org.gradle.dao;

import java.util.List;

import org.gradle.domain.Model;

/**
 * Dao for work with model entity.
 * @author Artsiom_Buyevich
 */
public interface ModelDao extends CrudDao<Long, Model> {

    /**
     * Find Model by id.
     * @param makeId id make
     * @return if found then model instance else null
     */
    List<Model> findByMakeId(Long makeId);

    /**
     * Delete models by ids.
     * @param ids list of model ids
     */
    void deleteOrphanModel(List<Long> ids);

    /**
     * Delete model by id make.
     * @param makeId id make
     */
    void deleteByMakeId(Long makeId);

}

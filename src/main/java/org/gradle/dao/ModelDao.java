package org.gradle.dao;

import java.util.List;

import org.gradle.domain.Model;

public interface ModelDao extends CrudDao<Long, Model> {

	List<Model> findByMakeId(Long makeId);

	void deleteOrphanModel(List<Long> ids);

	void deleteByMakeId(Long makeId);

}

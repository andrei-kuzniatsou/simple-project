package org.gradle.service;

import java.util.List;

import org.gradle.domain.Entity;

/**
 * Service for work with CRUD-operation.
 * 
 * @author Artsiom_Buyevich
 *
 * @param <K> key
 * @param <T> entity
 */
public interface CrudService<K, T extends Entity> {

	/**
	 * Save entity.
	 * @param entity entity
	 */
	void save(T entity);

	/**
	 * Get entity by key.
	 * @param key key
	 * @return entity
	 */
	T get(K key);

	/**
	 * Delete entity.
	 * @param key key
	 */
	void delete(K key);

	/**
	 * Find all entities.
	 * @return list of entities
	 */
	List<T> findAll();

}

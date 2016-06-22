package org.gradle.service;

import java.util.List;

import org.gradle.domain.Entity;

public interface CrudService<K, T extends Entity> {

	void save(T entity);

	T get(K key);

	void delete(K key);

	List<T> findAll();

}

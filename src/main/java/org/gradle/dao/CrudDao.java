package org.gradle.dao;

import java.util.List;

import org.gradle.domain.Entity;

public interface CrudDao<K, T extends Entity> {
  
  void create(T entity);
  
  T read(K key);
  
  void update(T entity);
  
  void delete(K key);
  
  List<T> findAll();

}

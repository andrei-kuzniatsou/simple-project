package org.gradle.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gradle.domain.Make;

import java.util.List;

public interface MakeDao {
	
	String SELECT_MAKE =
			"SELECT make.id, make.name, model.id AS MODEL_ID, "
			+ "model.name AS MODEL_NAME, model.start_date AS MODEL_START_DATE,"
			+ "model.end_date AS MODEL_END_DATE "
			+ "FROM public.make make "
			+ "INNER JOIN public.model model ON make.id = model.make_id";

	@Insert("INSERT INTO  public.make (name) VALUES (#{name})")
    void create(Make make);

	@Select(SELECT_MAKE + " WHERE make.id=#{id}")
	@ResultMap(value = "makeResultMap")
	Make read(Long id);

	@Update("UPDATE public.make SET name=#{name} WHERE id=#{id}")
    void update(Make make);

	@Delete("DELETE FROM public.make WHERE id=#{id}")
    void delete(Long id);

	@Select(SELECT_MAKE)
	@ResultMap(value = "makeResultMap")
    List<Make> findAll();

}

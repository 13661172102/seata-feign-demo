package com.teamer.servicetcc.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface TccDAO {

    @Insert("insert into service_tcc (name) values (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Map<String, Object> map);


    @Update("update service_tcc set name = #{name}")
    int update(Map<String, Object> map);

    @Delete("delete from service_tcc where id = #{id}")
    int delete(int id);

}

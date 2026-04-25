package com.example.demo.mapper;

import com.example.demo.entity.WordRootSpec;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WordRootSpecMapper {
    @Select("SELECT * FROM word_root_spec WHERE root = #{root} limit 1")
    WordRootSpec findByRoot(String root);
    @Select("SELECT * FROM word_root_spec order by cnt desc")
    List<WordRootSpec> findAll();

    @Select("SELECT * FROM word_root_spec WHERE id = #{id}")
    WordRootSpec findById(Integer id);

    @Insert("INSERT INTO word_root_spec(root, meaning, source, note) " +
            "VALUES(#{root}, #{meaning}, #{source}, #{note})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(WordRootSpec wordRootSpec);

    @Update("UPDATE word_root_spec SET root=#{root}, meaning=#{meaning}, " +
            "source=#{source}, note=#{note} WHERE id=#{id}")
    int update(WordRootSpec wordRootSpec);

    @Delete("DELETE FROM word_root_spec WHERE id=#{id}")
    int delete(Integer id);
}

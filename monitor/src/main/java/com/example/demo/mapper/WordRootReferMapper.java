package com.example.demo.mapper;

import com.example.demo.entity.WordRootRefer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WordRootReferMapper {
    @Select("SELECT * FROM word_root_refer")
    List<WordRootRefer> findAll();

    @Select("SELECT * FROM word_root_refer WHERE id = #{id}")
    WordRootRefer findById(Integer id);

    @Select("SELECT * FROM word_root_refer WHERE word = #{word} order by id asc")
    List<WordRootRefer> findByWord(String word);

    @Select("SELECT * FROM word_root_refer WHERE root = #{root} order by word asc")
    List<WordRootRefer> findByRoot(String root);

    @Insert("INSERT INTO word_root_refer(word, root) VALUES(#{word}, #{root})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(WordRootRefer wordRootRefer);

    @Update("UPDATE word_root_refer SET word=#{word}, root=#{root} WHERE id=#{id}")
    int update(WordRootRefer wordRootRefer);

    @Delete("DELETE FROM word_root_refer WHERE id=#{id}")
    int delete(Integer id);
}

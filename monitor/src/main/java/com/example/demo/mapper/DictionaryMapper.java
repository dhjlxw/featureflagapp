package com.example.demo.mapper;

import com.example.demo.entity.Dictionary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictionaryMapper {
    @Select("SELECT * FROM dictionary WHERE english = #{english}")
    Dictionary findByEnglish(String english);
    @Select("SELECT * FROM dictionary")
    List<Dictionary> findAll();

    @Select("SELECT * FROM dictionary WHERE id = #{id}")
    Dictionary findById(Integer id);

    @Insert("INSERT INTO dictionary(english, chinese, status) VALUES(#{english}, #{chinese}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Dictionary dictionary);

    @Update("UPDATE dictionary SET english=#{english}, chinese=#{chinese}, status=#{status} WHERE id=#{id}")
    int update(Dictionary dictionary);

    @Delete("DELETE FROM dictionary WHERE id=#{id}")
    int delete(Integer id);

    @Select("<script>" +
            "SELECT * FROM dictionary " +
            "WHERE 1=1 " +
            "<if test='status != null and status != \"\"'>AND status = #{status}</if> " +
            "<if test='keyword != null and keyword != \"\"'>AND (english LIKE CONCAT('%',#{keyword},'%') OR chinese LIKE CONCAT('%',#{keyword},'%'))</if> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<Dictionary> searchByPage(@Param("status") String status, 
                                @Param("keyword") String keyword,
                                @Param("offset") int offset,
                                @Param("pageSize") int pageSize);

    @Select("<script>" +
            "SELECT COUNT(*) FROM dictionary " +
            "WHERE 1=1 " +
            "<if test='status != null and status != \"\"'>AND status = #{status}</if> " +
            "<if test='keyword != null and keyword != \"\"'>AND (english LIKE CONCAT('%',#{keyword},'%') OR chinese LIKE CONCAT('%',#{keyword},'%'))</if>" +
            "</script>")
    int countByCondition(@Param("status") String status, 
                       @Param("keyword") String keyword);

    @Update("<script>" +
            "UPDATE dictionary SET status = 'Y' WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int batchActivate(@Param("ids") List<Integer> ids);

    @Update("<script>" +
            "UPDATE dictionary SET status = 'N' WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int batchDeactivate(@Param("ids") List<Integer> ids);
}

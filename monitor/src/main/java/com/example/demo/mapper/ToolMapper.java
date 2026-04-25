package com.example.demo.mapper;

import com.example.demo.entity.Tool;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ToolMapper {
    @Insert("INSERT INTO tool(name, java_class, java_method, description, parameters_json, required_parameters, comments) " +
            "VALUES(#{name}, #{javaClass}, #{javaMethod}, #{description}, #{parametersJson}, #{requiredParameters}, #{comments})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Tool tool);

    @Update("UPDATE tool SET name=#{name}, java_class=#{javaClass}, java_method=#{javaMethod}, " +
            "description=#{description}, parameters_json=#{parametersJson}, required_parameters=#{requiredParameters}, " +
            "comments=#{comments}, is_delete=#{isDelete} WHERE id=#{id}")
    int update(Tool tool);

    @Update("UPDATE tool SET is_delete=1 WHERE id=#{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tool WHERE id=#{id} AND is_delete=0")
    Tool selectById(Integer id);

    @Select("SELECT * FROM tool WHERE is_delete=0 " +
            "AND (#{keyword} IS NULL OR " +
            "name LIKE CONCAT('%',#{keyword},'%') OR " +
            "java_class LIKE CONCAT('%',#{keyword},'%') OR " +
            "java_method LIKE CONCAT('%',#{keyword},'%') OR " +
            "description LIKE CONCAT('%',#{keyword},'%') OR " +
            "comments LIKE CONCAT('%',#{keyword},'%')) " +
            "LIMIT #{offset}, #{pageSize}")
    List<Tool> selectAll(@Param("keyword") String keyword, 
                        @Param("offset") int offset,
                        @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM tool WHERE is_delete=0 " +
            "AND (#{keyword} IS NULL OR " +
            "name LIKE CONCAT('%',#{keyword},'%') OR " +
            "java_class LIKE CONCAT('%',#{keyword},'%') OR " +
            "java_method LIKE CONCAT('%',#{keyword},'%') OR " +
            "description LIKE CONCAT('%',#{keyword},'%') OR " +
            "comments LIKE CONCAT('%',#{keyword},'%'))")
    int countAll(@Param("keyword") String keyword);
}

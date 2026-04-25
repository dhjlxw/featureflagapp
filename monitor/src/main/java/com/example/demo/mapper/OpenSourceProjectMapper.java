package com.example.demo.mapper;

import com.example.demo.entity.OpenSourceProject;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface OpenSourceProjectMapper {
    @Select("<script>" +
            "SELECT * FROM open_source_project " +
            "<where>" +
            "  is_delete = 0" +
            "  <if test='type != null and type != \"\"'>" +
            "    AND type = #{type}" +
            "  </if>" +
            "  <if test='search != null and search != \"\"'>" +
            "    AND (project_name LIKE CONCAT('%',#{search},'%') OR " +
            "    description LIKE CONCAT('%',#{search},'%') OR " +
            "    project_license LIKE CONCAT('%',#{search},'%') OR " +
            "    model_license LIKE CONCAT('%',#{search},'%') OR " +
            "    project_description LIKE CONCAT('%',#{search},'%') OR " +
            "    effect_description LIKE CONCAT('%',#{search},'%') OR " +
            "    remarks LIKE CONCAT('%',#{search},'%'))" +
            "  </if>" +
            "</where>" +
            "ORDER BY ${sort.replace(',',' ')} " +
            "LIMIT #{size} OFFSET #{offset}" +
            "</script>")
    List<OpenSourceProject> findAll(@Param("search") String search,
                                  @Param("type") String type,
                                  @Param("offset") int offset,
                                  @Param("size") int size,
                                  @Param("sort") String sort);

    @Select("SELECT * FROM open_source_project WHERE id = #{id} AND is_delete = 0")
    OpenSourceProject findById(@Param("id") Integer id);

    @Insert("INSERT INTO open_source_project(project_name, project_url, project_license, model_url, model_license, " +
            "description, related_projects, project_description, project_limitations, deployment_status, " +
            "operation_status, effect_description, remarks, upload_files, created_by, updated_by, is_delete, type) " +
            "VALUES(#{projectName}, #{projectUrl}, #{projectLicense}, #{modelUrl}, #{modelLicense}, " +
            "#{description}, #{relatedProjects}, #{projectDescription}, #{projectLimitations}, #{deploymentStatus}, " +
            "#{operationStatus}, #{effectDescription}, #{remarks}, #{uploadFiles}, #{createdBy}, #{updatedBy}, #{isDelete}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OpenSourceProject project);

    @Update("UPDATE open_source_project SET " +
            "project_name=#{projectName}, project_url=#{projectUrl}, project_license=#{projectLicense}, " +
            "model_url=#{modelUrl}, model_license=#{modelLicense}, description=#{description}, " +
            "related_projects=#{relatedProjects}, project_description=#{projectDescription}, " +
            "project_limitations=#{projectLimitations}, deployment_status=#{deploymentStatus}, " +
            "operation_status=#{operationStatus}, effect_description=#{effectDescription}, " +
            "remarks=#{remarks}, upload_files=#{uploadFiles}, type=#{type}, updated_by=#{updatedBy}, updated_time=CURRENT_TIMESTAMP " +
            "WHERE id=#{id} AND is_delete = 0")
    int update(OpenSourceProject project);

    @Update("UPDATE open_source_project SET is_delete = 1, updated_by='DINGHAIJIANG', updated_time=CURRENT_TIMESTAMP WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    @Select("<script>" +
            "SELECT COUNT(*) FROM open_source_project " +
            "<where>" +
            "  is_delete = 0" +
            "  <if test='type != null and type != \"\"'>" +
            "    AND type = #{type}" +
            "  </if>" +
            "  <if test='search != null and search != \"\"'>" +
            "    AND (project_name LIKE CONCAT('%',#{search},'%') OR " +
            "    description LIKE CONCAT('%',#{search},'%') OR " +
            "    project_license LIKE CONCAT('%',#{search},'%') OR " +
            "    model_license LIKE CONCAT('%',#{search},'%') OR " +
            "    project_description LIKE CONCAT('%',#{search},'%') OR " +
            "    effect_description LIKE CONCAT('%',#{search},'%') OR " +
            "    remarks LIKE CONCAT('%',#{search},'%'))" +
            "  </if>" +
            "</where>" +
            "</script>")
    int countAll(@Param("search") String search,
               @Param("type") String type);
    
    @Select("SELECT DISTINCT type FROM open_source_project WHERE type IS NOT NULL")
    List<String> findDistinctTypes();

    @Select("SELECT type, COUNT(*) as count FROM open_source_project WHERE is_delete = 0 GROUP BY type")
    List<Map<String, Object>> getTypeSummary();

    @Select("SELECT project_license as license, COUNT(*) as count FROM open_source_project WHERE is_delete = 0 GROUP BY project_license")
    List<Map<String, Object>> getLicenseSummary();

    @Select("SELECT DATE_FORMAT(created_time, '%Y%m') as month, COUNT(*) as count FROM open_source_project WHERE is_delete = 0 GROUP BY DATE_FORMAT(created_time, '%Y%m')")
    List<Map<String, Object>> getMonthlySummary();
}

package com.example.demo.mapper;

import com.example.demo.entity.ResumeProjectExperience;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResumeProjectExperienceMapper {
    @Insert("INSERT INTO resume_project_experience (company_name, position_name, work_location, start_date, end_date, " +
            "project_name, project_description, personal_responsibility, main_contribution, main_challenge, " +
            "tech_stack_name, server_type, programming_language, keywords, remarks, language) " +
            "VALUES (#{companyName}, #{positionName}, #{workLocation}, #{startDate}, #{endDate}, " +
            "#{projectName}, #{projectDescription}, #{personalResponsibility}, #{mainContribution}, #{mainChallenge}, " +
            "#{techStackName}, #{serverType}, #{programmingLanguage}, #{keywords}, #{remarks}, #{language})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ResumeProjectExperience experience);

    @Update("UPDATE resume_project_experience SET " +
            "company_name = #{companyName}, " +
            "position_name = #{positionName}, " +
            "work_location = #{workLocation}, " +
            "start_date = #{startDate}, " +
            "end_date = #{endDate}, " +
            "project_name = #{projectName}, " +
            "project_description = #{projectDescription}, " +
            "personal_responsibility = #{personalResponsibility}, " +
            "main_contribution = #{mainContribution}, " +
            "main_challenge = #{mainChallenge}, " +
            "tech_stack_name = #{techStackName}, " +
            "server_type = #{serverType}, " +
            "programming_language = #{programmingLanguage}, " +
            "keywords = #{keywords}, " +
            "remarks = #{remarks}, " +
            "language = #{language} " +
            "WHERE id = #{id}")
    int update(ResumeProjectExperience experience);

    @Update("UPDATE resume_project_experience SET is_delete = 1, updated_time = CURRENT_TIMESTAMP WHERE id = #{id}")
    int softDeleteById(Integer id);

    @Select("SELECT * FROM resume_project_experience WHERE id = #{id} AND is_delete = 0")
    ResumeProjectExperience selectById(Integer id);

    @Select("SELECT * FROM resume_project_experience WHERE is_delete = 0")
    List<ResumeProjectExperience> selectAll();
    @Select("SELECT * FROM resume_project_experience WHERE is_delete = 0 AND (#{language} IS NULL OR language = #{language})")
    List<ResumeProjectExperience> selectByLanguage(@Param("language") String language);
}

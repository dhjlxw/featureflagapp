package com.example.demo.mapper;

import com.example.demo.entity.InterviewRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InterviewRecordMapper {
    @Insert("INSERT INTO interview_records (company_name, company_location, position_title, position_requirements, " +
            "resume_match, interview_detail, feedback, notes, company_intro) VALUES (#{companyName}, #{companyLocation}, " +
            "#{positionTitle}, #{positionRequirements}, #{resumeMatch}, #{interviewDetail}, #{feedback}, #{notes}, #{companyIntro})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(InterviewRecord record);

    @Update("UPDATE interview_records SET company_name=#{companyName}, company_location=#{companyLocation}, " +
            "position_title=#{positionTitle}, position_requirements=#{positionRequirements}, " +
            "resume_match=#{resumeMatch}, interview_detail=#{interviewDetail}, feedback=#{feedback}, " +
            "notes=#{notes}, company_intro=#{companyIntro} WHERE id=#{id}")
    int update(InterviewRecord record);

    @Update("UPDATE interview_records SET is_delete=1 WHERE id=#{id}")
    int delete(Integer id);

    @Select("SELECT * FROM interview_records WHERE id=#{id} AND is_delete=0")
    InterviewRecord getById(Integer id);

    @Select("SELECT * FROM interview_records WHERE is_delete=0")
    List<InterviewRecord> getAll();
}

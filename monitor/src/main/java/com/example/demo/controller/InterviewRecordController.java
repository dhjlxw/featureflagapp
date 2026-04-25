package com.example.demo.controller;

import com.example.demo.entity.InterviewRecord;
import com.example.demo.mapper.InterviewRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview-records")
public class InterviewRecordController {
    @Autowired
    private InterviewRecordMapper interviewRecordMapper;

    @PostMapping
    public InterviewRecord create(@RequestBody InterviewRecord record) {
        interviewRecordMapper.insert(record);
        return record;
    }

    @PutMapping("/{id}")
    public InterviewRecord update(@PathVariable Integer id, @RequestBody InterviewRecord record) {
        record.setId(id);
        interviewRecordMapper.update(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        interviewRecordMapper.delete(id);
    }

    @GetMapping("/{id}")
    public InterviewRecord getById(@PathVariable Integer id) {
        return interviewRecordMapper.getById(id);
    }

    @GetMapping
    public List<InterviewRecord> getAll() {
        return interviewRecordMapper.getAll();
    }
}

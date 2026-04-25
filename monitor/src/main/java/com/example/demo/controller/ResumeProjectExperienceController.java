package com.example.demo.controller;

import com.example.demo.entity.ResumeProjectExperience;
import com.example.demo.mapper.ResumeProjectExperienceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resume-projects")
public class ResumeProjectExperienceController {

    @Autowired
    private ResumeProjectExperienceMapper resumeProjectExperienceMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ResumeProjectExperience experience) {
        resumeProjectExperienceMapper.insert(experience);
        // Get the saved entity with generated ID
        ResumeProjectExperience savedExperience = resumeProjectExperienceMapper.selectById(experience.getId());
        return ResponseEntity.ok(savedExperience);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ResumeProjectExperience experience) {
        experience.setId(id);
        int result = resumeProjectExperienceMapper.update(experience);
        if (result == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(experience);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Integer id) {
        int result = resumeProjectExperienceMapper.softDeleteById(id);
        if (result == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        ResumeProjectExperience experience = resumeProjectExperienceMapper.selectById(id);
        if (experience == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<ResumeProjectExperience>> getAll(
            @RequestParam(required = false) String language) {
        List<ResumeProjectExperience> experiences = resumeProjectExperienceMapper.selectByLanguage(language);
        return ResponseEntity.ok(experiences);
    }
}

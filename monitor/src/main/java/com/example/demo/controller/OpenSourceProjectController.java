package com.example.demo.controller;

import com.example.demo.entity.OpenSourceProject;
import com.example.demo.mapper.OpenSourceProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/open-source-projects")
public class OpenSourceProjectController {
    @Autowired
    private OpenSourceProjectMapper mapper;

    @GetMapping("/types")
    public List<String> getDistinctTypes() {
        return mapper.findDistinctTypes();
    }

    @GetMapping("/summary/type")
    public List<Map<String, Object>> getTypeSummary() {
        return mapper.getTypeSummary();
    }

    @GetMapping("/summary/license")
    public List<Map<String, Object>> getLicenseSummary() {
        return mapper.getLicenseSummary();
    }

    @GetMapping("/summary/monthly")
    public List<Map<String, Object>> getMonthlySummary() {
        return mapper.getMonthlySummary();
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProjects(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        
        int offset = (page - 1) * size;
        List<OpenSourceProject> projects = mapper.findAll(search, type, offset, size, sort);
        int total = mapper.countAll(search, type);
        
        Map<String, Object> response = new HashMap<>();
        response.put("content", projects);
        response.put("totalElements", total);
        response.put("totalPages", (int) Math.ceil((double) total / size));
        response.put("page", page);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public OpenSourceProject getProjectById(@PathVariable Integer id) {
        return mapper.findById(id);
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("请选择要上传的文件");
        }
        
        try {
            // 获取配置的上传目录
            String uploadDir = this.uploadDir + "/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 生成唯一文件名
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;
            
            // 保存文件
            file.transferTo(new File(filePath));
            
            // 返回相对路径
            return ResponseEntity.ok("/upload/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                .body("文件上传失败: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OpenSourceProject project) {
        project.setCreatedBy("DINGHAIJIANG");
        project.setUpdatedBy("DINGHAIJIANG");
        project.setIsDelete(0);
        
        mapper.insert(project);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                  @RequestBody OpenSourceProject project,
                                  @RequestParam(value = "files", required = false) MultipartFile[] files) {
        project.setId(id);
        project.setUpdatedBy("DINGHAIJIANG");
        
        if (files != null && files.length > 0) {
            String filePaths = Arrays.stream(files)
                .map(file -> {
                    try {
                        return saveFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException("文件保存失败", e);
                    }
                })
                .collect(Collectors.joining(","));
            project.setUploadFiles(filePaths);
        }
        
        mapper.update(project);
        return ResponseEntity.ok(project);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String uploadDir = this.uploadDir + "/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + fileName;
        file.transferTo(new File(filePath));
        return "/upload/" + fileName;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        try {
            mapper.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("删除失败: " + e.getMessage());
        }
    }
}

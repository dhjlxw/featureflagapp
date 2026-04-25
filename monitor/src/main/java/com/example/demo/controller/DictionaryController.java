package com.example.demo.controller;

import com.example.demo.entity.Dictionary;
import com.example.demo.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    private final DictionaryMapper dictionaryMapper;

    public DictionaryController(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }

    @GetMapping("/by-english/{english}")
    public Map<String, String> getByEnglish(@PathVariable String english) {
        Dictionary dict = dictionaryMapper.findByEnglish(english);
        Map<String, String> result = new HashMap<>();
        if (dict != null) {
            result.put("chinese", dict.getChinese());
        }
        return result;
    }

    @GetMapping
    public List<Dictionary> getAll() {
        return dictionaryMapper.findAll();
    }

    @GetMapping("/{id}")
    public Dictionary getById(@PathVariable Integer id) {
        return dictionaryMapper.findById(id);
    }

    @PostMapping
    public int create(@RequestBody Dictionary dictionary) {
        return dictionaryMapper.insert(dictionary);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody Dictionary dictionary) {
        dictionary.setId(id);
        return dictionaryMapper.update(dictionary);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return dictionaryMapper.delete(id);
    }

    @PostMapping("/batch-update-status")
    public Map<String, Object> batchUpdateStatus(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> activeIds = request.get("active");
        List<Integer> deactiveIds = request.get("deactive");
        
        int activated = 0;
        int deactivated = 0;
        
        if (activeIds != null && !activeIds.isEmpty()) {
            activated = dictionaryMapper.batchActivate(activeIds);
        }
        
        if (deactiveIds != null && !deactiveIds.isEmpty()) {
            deactivated = dictionaryMapper.batchDeactivate(deactiveIds);
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("activated", activated);
        result.put("deactivated", deactivated);
        return result;
    }

    @GetMapping("/search")
    public Map<String, Object> searchByPage(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        int offset = (page - 1) * size;
        List<Dictionary> data = dictionaryMapper.searchByPage(status, keyword, offset, size);
        int total = dictionaryMapper.countByCondition(status, keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        result.put("total", total);
        result.put("currentPage", page);
        result.put("pageSize", size);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        
        return result;
    }
}

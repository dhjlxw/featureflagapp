package com.example.demo.controller;

import com.example.demo.entity.Tool;
import com.example.demo.mapper.ToolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tool")
public class ToolController {
    @Autowired
    private ToolMapper toolMapper;

    @PostMapping
    public int create(@RequestBody Tool tool) {
        return toolMapper.insert(tool);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody Tool tool) {
        tool.setId(id);
        return toolMapper.update(tool);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return toolMapper.deleteById(id);
    }

    @GetMapping("/{id}")
    public Tool getById(@PathVariable Integer id) {
        return toolMapper.selectById(id);
    }

    @GetMapping
    public Map<String, Object> getAll(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        
        int offset = (page - 1) * pageSize;
        List<Tool> tools = toolMapper.selectAll(keyword, offset, pageSize);
        int total = toolMapper.countAll(keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("data", tools);
        result.put("total", total);
        result.put("page", page);
        result.put("pageSize", pageSize);
        return result;
    }
}

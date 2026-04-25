package com.example.demo.util;

import com.example.demo.entity.OpenSourceProject;
import com.example.demo.mapper.OpenSourceProjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OpenSourceProjectMapper projectMapper;

    public DataInitializer(OpenSourceProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        // 初始化逻辑已移除，改为手动插入数据
    }
}

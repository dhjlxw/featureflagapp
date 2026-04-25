package com.example.demo.tool.spec;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ToolReturn {
    String value();
}

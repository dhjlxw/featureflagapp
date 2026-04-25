package com.example.demo.tool.spec;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ToolParameter {

    String name();

    boolean required();

    String description();

   
    
    
}

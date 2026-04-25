package com.example.demo.tool.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.example.demo.ai.AIUtil;
import com.example.demo.tool.spec.ToolDescription;
import com.example.demo.tool.spec.ToolParameter;
import com.example.demo.tool.spec.ToolReturn;

public class FileTools {

    @ToolDescription("to copy a file to another file")
    @ToolReturn("SUCESS or FAIL")
    public static String copyFile(
            @ToolParameter(name = "srcPath", required = true, description = "the file path to be read") String srcPath,
            @ToolParameter(name = "dstPath", required = true, description = "the file path to be written") String dstPath)
            throws Exception {
        System.out.println("COPY:" + srcPath + " to " + dstPath);
        new File(dstPath).getParentFile().mkdirs();
        try (InputStream in = new FileInputStream(srcPath); OutputStream out = new FileOutputStream(dstPath)) {
            AIUtil.copyIO(in, out);
        }

        return "SUCCESS";
    }

    @ToolDescription("to read string from a specified path")
    @ToolReturn("the file content")
    public static String readFile(
            @ToolParameter(name = "path", required = true, description = "the file path to be read") String path)
            throws Exception {
        System.out.println("READ:" + path);
        String ret = AIUtil.read(path);

        return ret;
    }

    @ToolDescription("to write string into a specified path")
    @ToolReturn("suceess or not")
    public static boolean writeFile(
            @ToolParameter(name = "path", required = true, description = "the file path to be written") String path,
            @ToolParameter(name = "content", required = true, description = "the content to be written") String content)
            throws Exception {
        System.out.println("WRITE:" + path + " with content:" + content);

        new File(path).getParentFile().mkdirs();
        AIUtil.writeToFile(content, new File(path));
        boolean ret = true;

        return ret;
    }
}

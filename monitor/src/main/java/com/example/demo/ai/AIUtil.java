package com.example.demo.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class AIUtil {
    public static String AI_MODEL = "/run/dhj/Qwen2___5-14B-Instruct/";

    public static void copyIO(InputStream in, OutputStream out) throws Exception {
        int c = -1;
        byte[] b = new byte[10240];
        int n = 0;
        while ((c = in.read(b)) != -1) {
            out.write(b, 0, c);
            n++;
            if (n >= 30) {
                out.flush();
                n = 0;
            }
        }
    }

    public static void writeToFile(String str, File file) throws Exception {
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(str.getBytes());
        }
    }

    public static String[] addArch(String[] originalArray) {
        // 创建一个长度比原数组大 2 的新数组
        String[] newArray = new String[originalArray.length + 2];

        // 将 "arch" 和 "-arm64" 插入到新数组的前两个位置
        newArray[0] = "arch";
        newArray[1] = "-arm64";

        // 将原数组的元素复制到新数组中从索引 2 开始的位置
        System.arraycopy(originalArray, 0, newArray, 2, originalArray.length);

        return newArray;
    }

    public static String read(String path) throws Exception {
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[(int) f.length()];
        in.read(b, 0, (int) f.length());
        in.close();
        return new String(b);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean empty(String s) {
        return s == null || s.trim().equals("");
    }

    public static <T extends Object> T first(Collection<T> col) {
        if (col == null || col.isEmpty()) {
            return null;
        }
        return col.iterator().next();
    }

    public static int runCommand(String[] cmd, Map<String, String> env) {
        int ret = -1;
        try {
            if ("arm64".equals(System.getProperty("arch", "arm64"))) {
                cmd = addArch(cmd);
            }
            System.out.println("RCMD:" + Arrays.toString(cmd));
            // 创建一个进程构建器，用于执行命令
            ProcessBuilder pb = new ProcessBuilder(cmd);
            if (env != null) {
                pb.environment().putAll(env);
            }

            pb.redirectErrorStream(true);
            // 启动进程
            Process process = pb.start();

            // 获取标准输出流
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // 读取标准输出并打印到控制台
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // 等待进程执行结束并获取返回值
            int exitCode = process.waitFor();
            System.out.println("命令执行结束，返回值: " + exitCode);
            ret = exitCode;

        } catch (IOException | InterruptedException e) {
            // 处理可能的异常
            e.printStackTrace();
        }
        return ret;
    }
}

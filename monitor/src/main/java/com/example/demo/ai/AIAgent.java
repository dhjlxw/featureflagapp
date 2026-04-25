package com.example.demo.ai;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.ai.beans.request.FunctionTool;
import com.example.demo.ai.beans.request.Message;
import com.example.demo.ai.beans.request.OpenAIToolChoiceRequest;
import com.example.demo.ai.beans.request.Parameter;
import com.example.demo.ai.beans.request.ToolFunction;
import com.example.demo.ai.beans.response.Choice;
import com.example.demo.ai.beans.response.OpenAIResponse;
import com.example.demo.ai.beans.response.ToolChoice;
import com.example.demo.tool.impl.FileTools;
import com.example.demo.tool.impl.TerminateTools;
import com.example.demo.tool.spec.ToolDescription;
import com.example.demo.tool.spec.ToolParameter;
import com.example.demo.tool.spec.ToolReturn;

@Component
public class AIAgent {
    Map<String, Method> toolMethod = new HashMap<>();
    Map<String, FunctionTool> tools = getAllTools(toolMethod);

    public void process(String question) {
        List<Message> messages = new LinkedList<>();
        while (true) {
            Message current = new Message("user",
                    String.format(
                            "用户初始问题：%s,提供以下这些tools，请根据历史记录，请给出当前应该调用哪个以及参数，如果当前无工具可调用或无法继续，请直接返回工具Terminate.terminate以告知我处理结束无须下一步了",
                            question));
            if (messages.isEmpty()) {
                messages.add(current);
            }

            OpenAIToolChoiceRequest req = buildRequest(question, messages);
            String request = JsonUtil.toPrettyString(req);
            System.out.println(request);
            String response = MyAI.getResponse(request);
            System.out.println(response);
            OpenAIResponse resp = JsonUtil.toObject(response, OpenAIResponse.class);
            Choice choice = resp.getChoices().iterator().next();
            String content = choice.getMessage().getContent();
            System.out.println(content);
            content = content.substring(0, content.lastIndexOf("}") + 1);
            int idx = content.indexOf("{\"nam");
            if (idx != -1) {
                content = content.substring(idx);
            }
            ToolChoice tc = null;
            try {
                tc = JsonUtil.toObject(content, ToolChoice.class);
            } catch (Exception e) {
                tc=new ToolChoice();
                tc.setName(getNameFrom(content)); 
                 Map<String,String> paras = getParametersFrom(content);
                 tc.setParameters(paras);
            }
            System.out.println(tc.getName());
            System.out.println(tc.getParameters());
            FunctionTool tool = tools.get(tc.getName());
            System.out.println("TOOL:" + JsonUtil.toPrettyString(tool));
            System.out.println(tool.getFunction().getDescription());
            if ("TerminateTools.terminate".equals(tc.getName())) {
                Message last = new Message("system", "调用结束");
                messages.add(last);
                System.out.println("调用结束");
                break;
            }
            String toolResult = "";
            Exception exception = null;
            try {
                toolResult = invokeTool(tc.getName(), tc.getParameters());
            } catch (Exception e) {
                exception = e;
                e.printStackTrace();

            }
            if (exception == null) {
                Message resultMessage = new Message("user", String.format("工具调用%s.返回结果：%s", tc.getName(), toolResult));
                messages.add(resultMessage);
            } else {
                Message resultMessage = new Message("user",
                        String.format("工具调用%s, 失败：%s", tc.getName(), exception.getMessage()));
                messages.add(resultMessage);

            }
        }

    }

    private Map<String,String> getParametersFrom(String content) {
        Map<String,String> map=new LinkedHashMap<>();
        String c=content.substring(content.indexOf("parameters\": {")+"parameters\": {".length());
        String[] arr=c.split("\n+");
        for(String a:arr){
            a=a.trim();
            if(a.contains("\": \"")&&a.startsWith("\"")){
                String name=a.substring(1,a.indexOf("\""));
                String value=a.substring(a.indexOf("\": \"")+"\": \"".length());
                value=value.substring(0,value.lastIndexOf("\""));
                map.put(name, value);
            }
        }
        return map;
       
    }

    private String getNameFrom(String content) {
        String c=content.substring(content.indexOf("\"name\": \""+"\"name\": \"".length()));
        c=c.substring(0,c.indexOf("\","));
        return c;
    }

    private String invokeTool(String name, Map<String, String> parameters) throws Exception {
        Method m = toolMethod.get(name);
        Object[] args = parameters.values().toArray(new Object[0]);
        Object ret = m.invoke(null, args);

        return String.valueOf(ret);
    }

    public OpenAIToolChoiceRequest buildRequest(String question, List<Message> messages) {

        OpenAIToolChoiceRequest req = new OpenAIToolChoiceRequest();
        req.setMessages(messages);
        req.setModel(AIUtil.AI_MODEL);
        req.setToolChoice("auto");
        req.setTools(new ArrayList<FunctionTool>(tools.values()));
        return req;
    }

    private Map<String, FunctionTool> getAllTools(Map<String, Method> toolMethodMap) {
        Map<String, FunctionTool> map = new LinkedHashMap<>();
        for (Class clazz : new Class[] { FileTools.class, TerminateTools.class }) {

            map.putAll(getToolFunction(clazz, toolMethodMap));
        }
        return map;
    }

    public Map<String, FunctionTool> getToolFunction(Class clazz, Map<String, Method> toolMethodMap) {
        Map<String, FunctionTool> map = new LinkedHashMap<>();
        Method[] ms = clazz.getMethods();
        if (ms != null) {
            for (Method m : ms) {

                if (m.isAnnotationPresent(ToolDescription.class)) {

                    ToolFunction tf = getToolFunction(clazz, m);
                    FunctionTool ft = new FunctionTool("function", tf);
                    toolMethodMap.put(tf.getName(), m);
                    map.put(tf.getName(), ft);
                }
            }
        }
        return map;

    }

    private ToolFunction getToolFunction(Class clazz, Method m) {
        // Get basic method info
        String methodName = clazz.getSimpleName() + "." + m.getName();

        // Get description from annotation or use method name
        ToolDescription descAnnotation = m.getAnnotation(ToolDescription.class);
        String description = descAnnotation != null ? descAnnotation.value() : methodName; // Empty annotation case

        // Process parameters
        Map<String, Parameter> parameters = new HashMap<>();
        List<String> requiredParams = new ArrayList<>();

        for (java.lang.reflect.Parameter param : m.getParameters()) {
            String paramName = param.getName();
            String paramType = param.getType().getSimpleName();

            // Get parameter description from annotation if available
            ToolParameter paramAnnotation = param.getAnnotation(ToolParameter.class);
            String paramDesc = paramAnnotation != null ? "" : paramName;

            parameters.put(paramName, new Parameter(paramType, paramDesc, null));
            requiredParams.add(paramName);
        }

        // Process return type
        ToolReturn returnAnnotation = m.getAnnotation(ToolReturn.class);
        String returnDesc = returnAnnotation != null ? returnAnnotation.value() : "void";
        Parameter returnParam = new Parameter(m.getReturnType().getSimpleName(), returnDesc, null);

        return new ToolFunction(methodName, description, parameters, requiredParams, returnParam);
    }

    public static void main(String[] args) {
        String question = "请将/Users/dinghaijiang/ding/ai/java-myapp/pom.xml复制到/Users/dinghaijiang/ding/ai/java-myapp/pom.txt中";
        question = "请将java学习的主要路径搞一个大纲出来，写入到/Users/dinghaijiang/ding/ai/java-myapp/pom.txt";
        new AIAgent().process(
                question);
    }
}

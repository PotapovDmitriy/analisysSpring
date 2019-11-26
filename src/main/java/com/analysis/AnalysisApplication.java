package com.analysis;

import com.analysis.modules.FileAnalysis;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.Map;

public class AnalysisApplication {

    private static Map<String, FileAnalysis> modulesMap;

    private static String path;

    private String file_extention;

    private static FileAnalysis analizer;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AnalysisApplication analys = (AnalysisApplication) ctx.getBean("AnalysisApplication");
        path = "C:\\Users\\Dmitriy\\Desktop\\Delivery-web";
        analizer =  modulesMap.get(analys.review(path));
        analizer.analysis("1", path);

    }
    public AnalysisApplication(Map<String, FileAnalysis> analizers){
        this.modulesMap = analizers;
    }

    public AnalysisApplication() {
    }

    public String review (String path){
        return "directory";
    }

}

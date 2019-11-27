package com.analysis;

import com.analysis.modules.FileAnalysis;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class AnalysisApplication {

    private static Map<String, FileAnalysis> modulesMap;

    private static String path;

    private String file_extention;


    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        String[] numbers = {"1", "2", "3"};
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AnalysisApplication analys = (AnalysisApplication) ctx.getBean("AnalysisApplication");
        while (true) {
            System.out.println("Please enter the full path to the file");
            Scanner console = new Scanner(System.in);

            String name = console.nextLine();
            if(name.isEmpty()){
                System.out.println("empty or error path, try again");
                continue;
            }
            try{
                File file = new File(name);
                if(!file.exists()){
                    System.out.println("File not found");
                    continue;
                }
                FileAnalysis currentModules = analys.review(file);
                currentModules.getInformationAboutFunctions();
                String number = console.nextLine();
                while (!Arrays.asList(numbers).contains(number)) {
                    System.out.println("error number, try again");
                    number = console.nextLine();
                }
                currentModules.analysis(number, file.getPath());
                System.out.println("You can type another path, if you want");
            }
            catch (Exception ex){
                System.out.println("Error path, try again");
                continue;
            }
        }

    }

    public AnalysisApplication(Map<String, FileAnalysis> map) {
        this.modulesMap = map;
    }

    public AnalysisApplication() {
    }

    public FileAnalysis review(File file) {
        FileAnalysis currentModule = null;
        for (String s : modulesMap.keySet()) {
            if (modulesMap.get(s).checkType(file)) {
                currentModule = modulesMap.get(s);
            }
        }
        return currentModule;

    }

}

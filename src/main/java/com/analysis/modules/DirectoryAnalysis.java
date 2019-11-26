package com.analysis.modules;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectoryAnalysis implements FileAnalysis {
    @Override
    public void analysis(String methodNumber, String path) {

        File directory = new File(path);
        if (!checkType(directory)) {
            System.out.println("WOW, it's not a directory!  \nTry again");
            return;
        }
        if(methodNumber.equals("1")){
            allFileInTheDirectory(directory);
        }
        else if (methodNumber.equals( "2")) {
            totalSizeOfTheFile(directory);
        }
        else {
            counterTypesOfFiles(directory);
        }

    }

    @Override
    public boolean checkType(File file) {
        return file.isDirectory();
    }

    public DirectoryAnalysis(){}

    public boolean isEmpty(File directory) {
        return directory.listFiles().length == 0;
    }

    void allFileInTheDirectory(File directory) {
        System.out.println("Directory " + directory.getName() + " contains:");
        try {
            int i = 0;
            if(isEmpty(directory)){
                System.out.println("This directory empty");
                return;
            }
            for (File file : directory.listFiles()) {
                i++;
                System.out.println(i + ") " + file.getName());
            }
        } catch (NullPointerException ex) {
            System.out.println("nothing :D");
        }
    }

    public void totalSizeOfTheFile(File directory) {
        int totalSize = 0;
        try {
            if (directory.listFiles().length == 0) {
                System.out.println("This directory empty");
                return;
            }
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    totalSize += file.length();
                }
            }
            System.out.println("Directory " + directory.getName() + " have total size: " + Integer.toString(totalSize) + " bytes");
        } catch (NullPointerException ex) {
            System.out.println("Empty");
        }
    }

    public void counterTypesOfFiles(File directory) {
        if (isEmpty(directory)) {
            System.out.println("This directory empty");
            return;
        }
        System.out.println("Directory " + directory.getName() + " contains:");
        String type = null;
        int index = 0;
        Map<String, Integer> counter = new HashMap<>();
        for(File file : directory.listFiles()){
            index = file.getName().lastIndexOf('.');
            if(index == -1 ){
                type = "directory";
            }
            else{
                type = file.getName().substring(index);
            }

            if (!counter.containsKey(type)){
                counter.put(type, 1);
            }
            else {
                counter.put(type, counter.get(type) + 1);
            }

        }
        counter.forEach((k, v) -> System.out.println(k + ": " + v));


    }
}

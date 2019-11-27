package com.analysis.modules;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;

public class TextFileAnalysis implements FileAnalysis {

    private File file;

    @Override
    public void analysis(String methodNumber, String path) throws IOException {
        File file = new File(path);
        if (!checkType(file)) {
            System.out.println("WOW, it's not a .txt file!  \nTry again");
            return;
        }
        if(methodNumber.equals("1")){
            getCountOfLine(file);
        }
        else if (methodNumber.equals( "2")) {
            getFrequencyOfOccurrence(file);
        }
        else {
            getCountOfWord(file);
        }
    }


    @Override
    public boolean checkType(File file) {
        int index = file.getName().indexOf('.');
        return index != -1 && file.getName().substring(index).equals(".txt");
    }
    @Override
    public void getInformationAboutFunctions() {
        System.out.println("It's text file, you can use this function:\n" +
                "1: counting and displaying the number of lines\n" +
                "2: output frequency of occurrence of each character \n" +
                "3: counting and displaying the number of word\n" +
                "type one of this number" );
    }

    private void getCountOfLine(File file) {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(file.getPath()));
        } catch (IOException e) {
            System.out.println("file not found");
        }
        long linesCount = lines.count();
        System.out.println("Count of lines in " + file.getName() + ": " +  linesCount);
        lines.close();
    }

    private void getCountOfWord(File file) {
        Long wordsCount = null;
        try {
            wordsCount = Files.lines(Paths.get(String.valueOf(file)))
                    .flatMap(str -> Stream.of(str.split("[ ,.!?\r\n]")))
                    .filter(s -> s.length() > 0).count();
        } catch (IOException e) {
            System.out.println("file not found");
        }
        System.out.println("Count of word in " + file.getName() + ": " + wordsCount);
    }

    private void getFrequencyOfOccurrence (File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        Map<Byte, Integer> map = new TreeMap<Byte, Integer>();

        while(true) {
            try {
                if (!(inputStream.available() > 0)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte data = (byte) inputStream.read();

            if(map.containsKey(data)) {
                map.put(data, map.get(data) + 1);
            } else {
                map.put(data, 1);
            }
        }

        for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
            System.out.println((char)(int)pair.getKey() + " " + pair.getValue());
        }

        inputStream.close();
    }
}

package com.analysis.modules;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;

public interface FileAnalysis {

    void analysis(String methodNumber, String path) throws IOException, InvalidDataException, UnsupportedTagException;

    boolean checkType(File file);

    void getInformationAboutFunctions();
}

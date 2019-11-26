package com.analysis.modules;

import java.io.File;

public interface FileAnalysis {
    public void analysis (String methodNumber, String path);
    boolean checkType (File file );
}

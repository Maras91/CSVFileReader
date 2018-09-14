package com.csv.file;

import java.nio.file.Path;
import java.util.List;


public class FileList {
    private final List<Path> fileList;

    public FileList(List<Path> fileList) {
        this.fileList = fileList;
    }

    public List<Path> getFileList() {
        return fileList;
    }
}


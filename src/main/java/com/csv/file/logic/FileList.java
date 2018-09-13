package com.csv.file.logic;

import com.csv.Properties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class FileList {

    private List<Path> fileList;

    public FileList() throws IOException{
        fileList = Files.walk(Paths.get(Properties.fileWithListOfScanDirectories))
                .filter(p -> p.toString().endsWith("."+Properties.extension.toString())).collect(Collectors.toList());

    }

    public List<Path> getFileList() {
        return fileList;
    }
}


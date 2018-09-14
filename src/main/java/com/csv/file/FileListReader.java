package com.csv.file;

import com.csv.Properties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileListReader {
    public FileList readFileList(String path) throws IOException {
        return new FileList(Files.walk(Paths.get(path))
                .filter(p -> p.toString().endsWith("."+Properties.extension.toString().toLowerCase())).collect(Collectors.toList()));
    }
}

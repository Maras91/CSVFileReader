package com.csv.old;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewFileList {


    private ArrayList<File> fileList;

    public NewFileList() {
        fileList = new ArrayList<File>();
        fileList.addAll(FileUtils.listFiles(new File("C:\\Users\\A701378\\IdeaProjects\\CSVFileReader\\src\\main\\resources\\zadanie\\1zadanie"),
                new SuffixFileFilter("csv"), TrueFileFilter.INSTANCE));
    }

    public ArrayList<File> getFileList() {
        return fileList;
    }

}

package com.csv;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileList {

    private ArrayList<File> fileList;

    public FileList(){
        fileList = folderListToFileList(listOfFolders());
    }
    public ArrayList<String> listOfFolders(/*Properties.fileWithListOfScanDirectories do wszystkiego*/) {
        ArrayList<String> folderList = new ArrayList<>();
        try {
            Files.lines(Paths.get(Properties.fileWithListOfScanDirectories)).forEach(line -> folderList.add(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folderList;
    }
    public ArrayList<File> folderListToFileList(ArrayList<String> listOfFolders) {
        //ArrayList<String> listOfFolders -> ArrayList<File>
        ArrayList<File> fileList = new ArrayList<>();
        listOfFolders.forEach(directory ->
                fileList.addAll(FileUtils.listFiles(new File(directory), new SuffixFileFilter(Properties.extension.toString()), TrueFileFilter.INSTANCE))
        );
        return fileList;
    }

    public ArrayList<File> getFileList() {
        return fileList;
    }
}


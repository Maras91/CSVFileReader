package com.csv;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateListOfFile {

    private ArrayList<File> fileList;

    public CreateListOfFile(){
        fileList = new ArrayList<File>();
        fileList.addAll(folderListToFileList(listOfFolders()));
    }
    public ArrayList<String> listOfFolders() {
        ArrayList<String> folderList = new ArrayList<String>();
        File file = new File(Properties.fileWithListOfScanDirectories);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            folderList.add(sc.nextLine());
        }
        return folderList;
    }
    public ArrayList<File> folderListToFileList(ArrayList<String> listOfFolders) {
        ArrayList<File> fileList = new ArrayList<File>();
        listOfFolders.forEach(directory ->
                fileList.addAll(FileUtils.listFiles(new File(directory), new SuffixFileFilter("csv"), TrueFileFilter.INSTANCE))
        );
        return fileList;
    }

    public ArrayList<File> getFileList() {
        return fileList;
    }
}


package com.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileList {
    private static final String FilesPathName = "C:\\Marek\\1zadanie\\pliki.txt";
    private  ArrayList<String> fileList;

    public FileList() {
        fileList = new ArrayList<String>();
        File file = new File(FilesPathName);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            fileList.add(sc.nextLine());
        }
    }
    public ArrayList<String> getFileList(){
        return this.fileList;
    }
}

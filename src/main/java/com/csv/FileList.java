package com.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileList {
    private  ArrayList<String> fileList;

    public FileList() {
        fileList = new ArrayList<String>();
        File file = new File("C:\\Marek\\1zadanie\\pliki.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
         //   System.out.println(sc.nextLine());
            fileList.add(sc.nextLine());
        }
    }
    public ArrayList<String> getFileList(){
        return this.fileList;
    }
}

package com.csv;

import java.io.IOException;

public class Main {
    public static void main(String[] argv) {
//        try {
//            FilesReader filesReader = new FilesReader(10);
//            System.out.println(filesReader.getOutputMessages());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        NewFileList dupa = new NewFileList();
        System.out.println(dupa.getFileList().size());
    }
}

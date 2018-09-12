package com.csv;

import com.csv.enums.DataType;

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
//    public FileList(){
//        fileList = folderListToFileList(listOfFolders());
//    }
//    public ArrayList<String> listOfFolders(/*Properties.fileWithListOfScanDirectories do wszystkiego*/) {
//        ArrayList<String> folderList = new ArrayList<>();
//        try {
//            Files.lines(Paths.get(Properties.fileWithListOfScanDirectories)).forEach(line -> folderList.add(line));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return folderList;
//    }
//    public ArrayList<CSVFile> folderListToFileList(ArrayList<String> listOfFolders) {
//        //ArrayList<String> listOfFolders -> ArrayList<CSVFile>
//        ArrayList<CSVFile> fileList = new ArrayList<>();
//        listOfFolders.forEach(directory ->
//                fileList.addAll(FileUtils.listFiles(new CSVFile(directory), new SuffixFileFilter(Properties.extension.toString()), TrueFileFilter.INSTANCE))
//        );
//        return fileList;
//    }
//
    public List<Path> getFileList() {
        return fileList;
    }
}


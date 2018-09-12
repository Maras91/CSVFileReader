package com.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class FileToRabbitMQMessagesConverter {

//    private ArrayList<RMQPackage> outputMessages;


    public FileToRabbitMQMessagesConverter() {
//        FileList fileList = null;
//        try {
//            fileList = new FileList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        outputMessages = new ArrayList<>();
//            outputMessages.addAll(FilesReader(Properties.rabbitMQMessageSize, fileList.getFileList()));
//    }
//    //spelnia 3 rzeczy a powinna 1(3 inne metody/klasy)
//    // 1 zwracanie prwidlowych typów dla tabeli
//    // 2 kowersja na JSONy
//    // 3 dzielenie na pakiety do rabbitMQ
//    public List<RMQPackage> FilesReader(int messageSize, ArrayList<CSVFile> fileList) {
//        ArrayList<JSONObject> jsonList = new ArrayList<>();
//        outputMessages = new ArrayList<>();
//        Map<String, Object> columns = new TreeMap<>();
//        String separator;
//        if (Properties.extension == Extension.csv) {
//            separator = ",";
//        } else {
//            separator = "\\s+";
//        }
//        fileList.forEach(file -> {
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(file));
//                String line;
//
//                if ((line = br.readLine()) != null) {
//                    List<String> columnsName = Arrays.asList((line.split(separator)));
//                    int countOfMessageLines = 0;
//                    while ((line = br.readLine()) != null) {
//                        List<String> values = Arrays.asList((line.split(separator)));
//                        for (int i = 0; i < columnsName.size(); i++) {
//                            columns.put(columnsName.get(i), FindType.findType(values.get(i)));
//                        }
//                        JSONObject json = new JSONObject(columns);
//                        jsonList.add(json);
//                        countOfMessageLines++;
//                        if (countOfMessageLines >= messageSize) {
//                            countOfMessageLines = 0;
//                            outputMessages.add(new RMQPackage(file.getName(), jsonList));
//                            jsonList.clear();
//                        }
//                    }
//                    if (!jsonList.isEmpty()) {
//                        outputMessages.add(new RMQPackage(file.getName(), jsonList));
//                        jsonList.clear();
//                    }
//                }
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        changeNameFileToComplete(fileList);
//        return outputMessages;
    }

//    public ArrayList<RMQPackage> getOutputMessages() {
//        return outputMessages;
//    }

    private static void changeNameFileToComplete(ArrayList<File> files) {
        files.forEach(oldFile -> {
            File newFile = new File(oldFile.getParent(), oldFile.getName() + ".complete");
            try {
                Files.move(oldFile.toPath(), newFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

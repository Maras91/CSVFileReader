package com.csv;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class convertFileToRabbitMQMessages {

    private ArrayList<Message> outputMessages;


    public convertFileToRabbitMQMessages() {
        CreateListOfFile fileList = new CreateListOfFile();
        try {
            outputMessages = new ArrayList<>();
            outputMessages.addAll(FilesReader(Properties.rabbitMQMessageSize,fileList.getFileList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Message> FilesReader(int messageSize , ArrayList<File> fileList) throws IOException {
        ArrayList<JSONObject> jsonList = new ArrayList<>();
        outputMessages = new ArrayList<>();
        Map<String,Object> columns = new TreeMap<>();
        String separator;
        if (Properties.extension==Extension.csv) {
            separator=",";
        } else {
            separator="\\s+";
        }

        fileList.forEach(file -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                if ((line = br.readLine()) != null) {
                    List<String> columnsName = Arrays.asList((line.split(separator)));
                    int countOfMessageLines=0;
                    while ((line = br.readLine()) != null) {
                        List<String> values = Arrays.asList((line.split(separator)));
                        for (int i=0; i<columnsName.size(); i++){
                            columns.put(columnsName.get(i), FindType.findType(values.get(i)));
                        }
                        JSONObject json = new JSONObject(columns);
                     //   System.out.println(json);
                        jsonList.add(json);
                        countOfMessageLines++;
                        if (countOfMessageLines>=messageSize) {
                            countOfMessageLines=0;
                            outputMessages.add(new Message(file.getName(),jsonList));
                            jsonList.clear();
                        }
                    }
                    if (!jsonList.isEmpty()){
                        outputMessages.add(new Message(file.getName(),jsonList));
                        jsonList.clear();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return outputMessages;
    }
    public ArrayList<Message> getOutputMessages() {
        return outputMessages;
    }
}

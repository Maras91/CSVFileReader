package com.csv.old;

import com.csv.FindType;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class FilesReader {

    private List<ArrayList<JSONObject>> outputMessages;

    public FilesReader(int messageSize) throws IOException {
        ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
        outputMessages =  new ArrayList<ArrayList<JSONObject>>();
                FileList fileList = new FileList();
        Map<String,Object> columns = new TreeMap<String,Object>();

        fileList.getFileList().forEach(file -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                if ((line = br.readLine()) != null) {
                    List<String> columnsName = Arrays.asList((line.split(",")));
                    int countOfMessageLines=0;
                    while ((line = br.readLine()) != null) {
                        List<String> values = Arrays.asList((line.split(",")));
                        for (int i=0; i<columnsName.size(); i++){
                            columns.put(columnsName.get(i), FindType.findType(values.get(i)));
                        }
                        JSONObject json = new JSONObject(columns);
                        System.out.println(json);
                        jsonList.add(json);
                        countOfMessageLines++;
                        if (countOfMessageLines>=messageSize) {
                            countOfMessageLines=0;
                            outputMessages.add(new ArrayList<JSONObject>(jsonList));
                            jsonList.clear();
                        }
                    }
                    if (!jsonList.isEmpty()){
                        outputMessages.add(new ArrayList<JSONObject>(jsonList));
                        jsonList.clear();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public List<ArrayList<JSONObject>> getOutputMessages(){
        return this.outputMessages;
    }

}

package com.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class FilesReader {

    public static void main(String[] args) throws IOException {

        FileList fileList = new FileList();
        Map<String,Object> columns = new TreeMap<String,Object>();

        fileList.getFileList().stream().forEach(file -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                if ((line = br.readLine()) != null) {
                    List<String> columnsName = Arrays.asList((line.split(",")));

                    while ((line = br.readLine()) != null) {
                        List<String> values = Arrays.asList((line.split(",")));
                        for (int i=0; i<columnsName.size(); i++){
                            columns.put(columnsName.get(i), FindType.findType(values.get(i)));
                        }
                        JSONObject json = new JSONObject(columns);
                        System.out.println(json);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println();
    }

}

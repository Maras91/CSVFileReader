package com.csv.converters;

import com.csv.Properties;
import com.csv.file.CSVFile;
import com.csv.file.Row;
import com.csv.rabbitMQ.RMQDataPackage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONConverter {

    public List<RMQDataPackage> convertToJSON(List<CSVFile> listOfCSVFiles ,int messageSize) {
        List<RMQDataPackage> outPutPackage = new ArrayList<>();
        List<JSONObject> jsonList = new ArrayList<>();
        Map<String, Object> newLine = new HashMap<>();

        listOfCSVFiles.forEach(file ->
        {
            int countOfMessageLines = 0;
            for (Row line :file.getData()) {
                line.getFields().forEach(field -> newLine.put(field.getColumnName(), field.getDataType().convertData(field.getData())));
                JSONObject json = new JSONObject(newLine);
                jsonList.add(json);
                countOfMessageLines++;
                if (countOfMessageLines >= messageSize) {
                    countOfMessageLines = 0;
                    outPutPackage.add(new RMQDataPackage(file.getId(), new ArrayList<>(jsonList)));
                    jsonList.clear();
                }
            }
            if (!jsonList.isEmpty()) {
                outPutPackage.add(new RMQDataPackage(file.getId(), new ArrayList<>(jsonList)));
                jsonList.clear();
            }
        });
        return outPutPackage;
    }

}

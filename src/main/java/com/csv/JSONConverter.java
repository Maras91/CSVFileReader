package com.csv;

import com.csv.enums.DataType;
import com.csv.file.logic.Row;
import com.csv.rabbitMQ.RMQDataPackage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONConverter {

    private List<RMQDataPackage> outPutPackage;

    public JSONConverter() {
        this.outPutPackage = new ArrayList<>();
    }

    public void convertToJSON() throws IOException {

        List<JSONObject> jsonList = new ArrayList<>();
        DataTypConverter listOfFiles = new DataTypConverter();
        Map<String, Object> newLine = new HashMap<>();

        listOfFiles.getListOfFiles().forEach(file ->
        {
            int countOfMessageLines = 0;
            for (Row line :file.getData()) {
                line.getFields().forEach(field -> {
                    newLine.put(field.getColumnName(), field.getData());
                });
                JSONObject json = new JSONObject(newLine);
                jsonList.add(json);
                countOfMessageLines++;
                if (countOfMessageLines >= Properties.rabbitMQMessageSize) {
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
    }
    public List<RMQDataPackage> getOutPutPackage() {
        return outPutPackage;
    }

}

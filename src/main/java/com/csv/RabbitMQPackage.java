package com.csv;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RabbitMQPackage {

    private List<JSONObject> jsonList;

    RabbitMQPackage() throws IOException {
        DataTypConverter dataTypConverter = new DataTypConverter();
        jsonList = new ArrayList<>();
        dataTypConverter.getListOfLines().forEach(line -> {
            JSONObject json = new JSONObject(line);
            jsonList.add(json);
        });
    }

    public List<JSONObject> getJsonList() {
        return jsonList;
    }

}

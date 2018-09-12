package com.csv.rabbitMQ;

import org.json.JSONObject;

import java.util.List;

public class RMQDataPackage {

    private String NameFile;
    private List<JSONObject> data;

    public RMQDataPackage(String nameFile, List<JSONObject> data) {
        NameFile = nameFile;
        this.data = data;
    }

    public String getNameFile() {
        return NameFile;
    }

    public List<JSONObject> getData() {
        return data;
    }

}

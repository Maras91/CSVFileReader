package com.csv;

import org.json.JSONObject;

import java.util.ArrayList;

public class Message {
    private String id;

    private ArrayList<JSONObject> data;

    Message(String id,ArrayList<JSONObject> data){
        this.id=id;
        this.data= new ArrayList<>();
        this.data = (ArrayList<JSONObject>)data.clone();
    }
    public String getId() {
        return id;
    }

    public ArrayList<JSONObject> getData() {
        return data;
    }
}
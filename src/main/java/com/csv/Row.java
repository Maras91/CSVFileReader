package com.csv;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private Map<String,Object> fields;

    Row() {
        fields = new HashMap<>();
    }

    public Map<String, Object> getFields() {
        return fields;
    }
    public void putField (String string,Object object) {
        fields.put(string,object);
    }
}

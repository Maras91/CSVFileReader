package com.csv.file;

import com.csv.file.Field;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Field> fields;

    Row() {
        fields = new ArrayList<>();
    }

    public List<Field> getFields() {
        return fields;
    }
    public void addField(Field field) {
        fields.add(field);
    }
}

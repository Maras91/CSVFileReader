package com.csv;

import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private String id;
    private List<Row> data;

    ReadFile(String id) {
        this.id=id;
        data = new ArrayList<>();
    }

    public void addRow () {
        data.add(new Row());
    }

    public String getId() {
        return id;
    }

    public Row getRow(int index) {
        return data.get(index);
    }

    public List<Row> getData() {
        return data;
    }
}

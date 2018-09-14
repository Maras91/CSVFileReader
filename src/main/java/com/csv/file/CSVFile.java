package com.csv.file;

import java.util.ArrayList;
import java.util.List;

public class CSVFile {

    private String id;
    private List<Row> data;

    public CSVFile(String id) {
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

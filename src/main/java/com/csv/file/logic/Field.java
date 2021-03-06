package com.csv.file.logic;

import com.csv.enums.DataType;

public class Field {
    private String data;
    private DataType dataType;
    private String columnName;

    public Field(String data, DataType dataType, String columnName) {
        this.data = data;
        this.dataType = dataType;
        this.columnName = columnName;
    }

    public String getData() {
        return data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public String getColumnName() {
        return columnName;
    }
}

package com.csv.enums;

public enum DataType implements DataConverter {
    INTEGER {
        @Override
        public Object convertData(String data) {
            return Integer.parseInt(data);
        }
    },
    DOUBLE {
        @Override
        public Object convertData(String data) {
            return Double.parseDouble(data);
        }
    },
    BOOL {
        @Override
        public Object convertData(String data) {
            return Boolean.parseBoolean(data);
        }
    },
    STRING {
        @Override
        public Object convertData(String data) {
            return data;
        }
    };
}

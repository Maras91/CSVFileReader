package com.csv.enums;

public enum DataType implements DataConverter {
    LONG {
        @Override
        public Object convertData(String data) {
            return Long.valueOf(data);
        }
    },
    DOUBLE {
        @Override
        public Object convertData(String data) {
            return Double.valueOf(data);
        }
    },
    BOOL {
        @Override
        public Object convertData(String data) {
            return Boolean.valueOf(data);
        }
    },
    STRING {
        @Override
        public Object convertData(String data) {
            return data;
        }
    };
}

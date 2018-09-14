package com.csv.enums;

public enum Extension implements ExtensionSeparator {
    CSV {
        @Override
        public String getSeparator() {
            return ",";
        }
    },

    TSV {
        @Override
        public String getSeparator() {
            return "\\s+";
        }
    };


}

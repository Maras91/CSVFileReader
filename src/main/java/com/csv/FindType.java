package com.csv;

import java.io.IOException;


public class FindType {

    public static Object findType(String variable) throws IOException {
        if (variable.toLowerCase().equals("false") || variable.toLowerCase().equals("true")) {
            return Boolean.parseBoolean(variable);
        }

        try {
            if (!Float.isInfinite(Float.parseFloat(variable)))
                return Float.parseFloat(variable);
        } catch (NumberFormatException e) {

        }
        try {
            Double.parseDouble(variable);
            return Double.parseDouble(variable);
        } catch (NumberFormatException e) {
            return variable;
        }
    }
}

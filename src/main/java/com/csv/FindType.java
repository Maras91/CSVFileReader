package com.csv;

import java.io.IOException;


public class FindType {

    private static final String variable = "4313456346435642.342";

    public static void main(String[] args) throws IOException {
        if (variable.toLowerCase().equals("false") || variable.toLowerCase().equals("true")) {
            System.out.println("bool");
        }

        try {
            if(!Float.isInfinite(Float.parseFloat(variable)))
                System.out.println("float");
        } catch (NumberFormatException e){

        }
        try {
            Double.parseDouble(variable);
            System.out.println("double");
        } catch (NumberFormatException e){

        }
        System.out.println("String");


    }
}

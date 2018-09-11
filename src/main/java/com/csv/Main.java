package com.csv;

import java.io.IOException;


public class Main {
    public static void main(String[] argv) throws IOException {

        RabbitMQProducer.startScanning();
//        DataTypConverter fileList = new  DataTypConverter();
//        fileList.getFileList().toString();
//        while (true) {
//            try {
//                RabbitMQProducer.startScanning();
//                Thread.sleep(Properties.scanningFrequencyInSeconds*1000);
//            } catch (InterruptedException | IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

}

package com.csv;

import java.io.IOException;


public class Main {
    public static void main(String[] argv) throws IOException {

        while (true) {
            try {
                RabbitMQProducer.startScanning();
                Thread.sleep(Properties.scanningFrequencyInSeconds*1000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.csv;

import com.csv.rabbitMQ.RabbitMQProducer;

import java.io.IOException;


public class Main {
    public static void main(String[] argv) throws IOException {

        RabbitMQProducer.startScanning();

    }

}

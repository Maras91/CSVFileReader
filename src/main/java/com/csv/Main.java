package com.csv;

import com.csv.rabbitMQ.RabbitMQProducer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] argv) throws IOException {
    //    RabbitMQProducer.startScanning();
//        Client client = new Client();
//        client.startConnection("127.0.0.1", 5555);
//        String response = client.sendMessage("hello server");
//        String response2 = client.sendMessage(".");
//        System.out.println(response);
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
                RabbitMQProducer.startScanning();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

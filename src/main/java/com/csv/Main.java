package com.csv;

import com.csv.network.connection.SendPackage;
import java.io.IOException;


public class Main {
    public static void main(String[] argv) throws IOException {
    //    RabbitMQProducer.startScanning();
        SendPackage.sendToServer("127.0.0.1", 5555);

//        while (true) {
//            try {
//                TimeUnit.SECONDS.sleep(5);
//                RabbitMQProducer.startScanning();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

}

package com.csv.network;

import com.csv.network.Client;
import com.csv.rabbitMQ.RMQDataPackage;

import java.util.List;

public class PackageSender {

    public void sendToServer(String ip, int port, List<RMQDataPackage> data) {

            Client client = new Client();
            client.startConnection(ip, port);
            String message;
            for (RMQDataPackage pack : data) {
                message = pack.getNameFile();
                message +=("&&&");
                message += pack.getData().toString();
                client.sendMessage(message);
            }
            client.sendMessage("end sending");

    }

}

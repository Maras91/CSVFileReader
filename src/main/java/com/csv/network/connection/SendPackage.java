package com.csv.network.connection;

import com.csv.converters.JSONConverter;
import com.csv.rabbitMQ.RMQDataPackage;
import org.json.JSONObject;

import java.io.IOException;

public class SendPackage {

    public static void sendToServer(String ip, int port) {
        try {
            JSONConverter data = new JSONConverter();
            data.convertToJSON();

            Client client = new Client();
            client.startConnection(ip, port);
            String message;
            for (RMQDataPackage pack : data.getOutPutPackage()) {
                message = pack.getNameFile();
                message +=("&&&");
                message += pack.getData().toString();
                client.sendMessage(message);
            }
            String end = client.sendMessage(".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

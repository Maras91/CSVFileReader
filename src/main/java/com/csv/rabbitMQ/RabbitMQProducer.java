package com.csv.rabbitMQ;

import com.csv.converters.JSONConverter;
import com.csv.Properties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {



    public static void startScanning() throws java.io.IOException {

     //   AddExtensionComplete messages = new AddExtensionComplete();
     //   DataTypConverter listOfFiles= new DataTypConverter();
        JSONConverter data = new JSONConverter();
        data.convertToJSON();

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Properties.rabbitMQHost);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            for (RMQDataPackage pack: data.getOutPutPackage()) {
                for (JSONObject json: pack.getData()) {
                    channel.queueDeclare(pack.getNameFile(), false, false, false, null);
                    channel.basicPublish("", pack.getNameFile(), null, json.toString().getBytes());
                    System.out.println(" [x] Sent '" + pack.getNameFile() + "':'" + json.toString() + "'");
                }
            }
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}

package com.csv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {



    public static void startScanning() throws java.io.IOException {

     //   FileToRabbitMQMessagesConverter messages = new FileToRabbitMQMessagesConverter();
        DataTypConverter listOfFiles= new DataTypConverter();
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Properties.rabbitMQHost);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            for (ReadFile file : listOfFiles.getListOfFiles()) {
                for (Row row:file.getData()) {
                    channel.queueDeclare(file.getId(), false, false, false, null);
                    channel.basicPublish("", file.getId(), null, row.getFields().toString().getBytes());
                    System.out.println(" [x] Sent '" + file.getId() + "':'" + row.getFields().toString() + "'");
                }
            }
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}

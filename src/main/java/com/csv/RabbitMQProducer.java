package com.csv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {



    public static void startScanning() throws java.io.IOException {

     //   FileToRabbitMQMessagesConverter messages = new FileToRabbitMQMessagesConverter();
        RabbitMQPackage messages= new RabbitMQPackage();
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Properties.rabbitMQHost);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            for (JSONObject message : messages.getJsonList()) {
                channel.queueDeclare("ddd.tsv",false,false,false,null);
                channel.basicPublish("", "ddd.tsv", null, message.toString().getBytes());
                System.out.println(" [x] Sent '" + "ddd.tsv" + "':'" + message + "'");
            }
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}

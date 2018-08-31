package com.csv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {



    public static void startScanning() throws java.io.IOException {

        FileToRabbitMQMessagesConverter messages = new FileToRabbitMQMessagesConverter();

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Properties.rabbitMQHost);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            for (Message message : messages.getOutputMessages()) {
                channel.queueDeclare(message.getId(),false,false,false,null);
                channel.basicPublish("", message.getId(), null, message.getData().toString().getBytes());
                System.out.println(" [x] Sent '" + message.getId() + "':'" + message.getData() + "'");
            }
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}

package com.csv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {

    static final public String EXCHANGE_NAME = "message";

    public static void main(String[] argv) throws java.io.IOException {

        convertFileToRabbitMQMessages messages = new convertFileToRabbitMQMessages();

        System.out.println();
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Properties.rabbitMQHost);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");


            for (Message message : messages.getOutputMessages()) {
                channel.basicPublish(EXCHANGE_NAME, message.getId(), null, message.getData().toString().getBytes());
                System.out.println(" [x] Sent '" + message.getId() + "':'" + message.getData() + "'");
            }

            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}

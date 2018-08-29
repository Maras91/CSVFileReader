package com.csv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws java.io.IOException {

        CreateRabbitMQMessages messages = new CreateRabbitMQMessages();

        System.out.println();
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Properties.rabbitMQHost);
            Connection connection = null;
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (ArrayList<JSONObject> message :messages.getOutputMessages()) {

                channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes() );
                System.out.println(" [x] Sent '" + message + "'");
            }
            channel.close();
            connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}

package com.csv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class RabbitMQProducer {



    public static void startScanning() throws java.io.IOException {

        ConvertFileToRabbitMQMessages messages = new ConvertFileToRabbitMQMessages();

        System.out.println();
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
//            changeNameFileToComplete(new CreateListOfFile().getFileList());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
//    private static void changeNameFileToComplete(ArrayList<File> files) {
//        files.forEach(oldFile -> {
//            File newFile = new File(oldFile.getParent(), oldFile.getName()+".complete");
//            try {
//                Files.move(oldFile.toPath(), newFile.toPath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}

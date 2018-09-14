package com.csv;

import com.csv.converters.DataTypConverter;
import com.csv.converters.JSONConverter;
import com.csv.file.CSVFile;
import com.csv.file.FileList;
import com.csv.file.FileListReader;
import com.csv.network.connection.PackageSender;
import com.csv.rabbitMQ.RMQDataPackage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] argv) throws IOException {
        FileListReader fileListReader = new FileListReader();
        DataTypConverter dataTypConverter = new DataTypConverter();
        JSONConverter jsonConverter = new JSONConverter();
        PackageSender packageSender = new PackageSender();
    //    RabbitMQProducer.startScanning();
    //    PackageSender.sendToServer("127.0.0.1", 5555);
    //    System.out.println("cos");
        while (true) {
            try {
                FileList fileList = fileListReader.readFileList(Properties.fileWithListOfScanDirectories);
                List<CSVFile> csvList = dataTypConverter.converterDataType(fileList);
                List<RMQDataPackage> listOFPackages = jsonConverter.convertToJSON(csvList);
                packageSender.sendToServer("127.0.0.1", 5555,listOFPackages);
                TimeUnit.SECONDS.sleep(Properties.scanningFrequencyInSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.csv;

import com.csv.converters.DataTypeConverter;
import com.csv.converters.JSONConverter;
import com.csv.file.CSVFile;
import com.csv.file.FileList;
import com.csv.file.FileListReader;
import com.csv.network.PackageSender;
import com.csv.rabbitMQ.RMQDataPackage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CSVFileProcessor {
    @Autowired
    private FileListReader fileListReader;

    @Autowired
    private DataTypeConverter dataTypeConverter;

    @Autowired
    private JSONConverter jsonConverter;

    @Autowired
    private PackageSender packageSender;

    public void processCSVFiles() {
        while (true) {
            try {
                FileList fileList = fileListReader.readFileList(Properties.fileWithListOfScanDirectories);
                List<CSVFile> csvList = dataTypeConverter.converterDataType(fileList);
                List<RMQDataPackage> listOFPackages = jsonConverter.convertToJSON(csvList,Properties.rabbitMQMessageSize);
                packageSender.sendToServer("127.0.0.1", 5555, listOFPackages);
                TimeUnit.SECONDS.sleep(Properties.scanningFrequencyInSeconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

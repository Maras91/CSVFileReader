package com.csv.config;

import com.csv.file.FileListReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {
    @Bean
    public FileListReader getFileListReader() {
        return new FileListReader();
    }

}
